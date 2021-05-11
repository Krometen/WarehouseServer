package com.warehouse.server.service;

import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService{

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orders;

    private final Environment env;

    private final MapperOrderService mapperOrderService;

    @Autowired
    public OrderServiceImpl(OrderRepository orders, Environment env, MapperOrderService mapperOrderService) {
        this.orders = orders;
        this.env = env;
        this.mapperOrderService = mapperOrderService;
    }

    @Override
    public void saveOrder(String orderNumber, LocalDate date, String address, List<ProductEntity> productEntityList){
        OrderEntity orderEntity = new OrderEntity(null, orderNumber, date, address, false, productEntityList);
        try {
            orders.save(orderEntity);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        Connection conn = null;
        Statement statement = null;
        try{
            String url = "jdbc:postgresql://localhost/warehouse";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password", env.getProperty("spring.datasource.password"));
            props.setProperty("ssl","false");
            conn = DriverManager.getConnection(url, props);
            statement = conn.createStatement();
            String result = String.format("UPDATE order_entity SET is_deleted = true WHERE id = %s ;", id);
            statement.executeUpdate(result);
        } catch(Exception se){
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally{
            //finally block used to close resources
            try{
                if(statement!=null) {
                    conn.close();
                }
            }catch(SQLException ignored){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
                logger.warning("SQLException: "+se);
            }//end finally try
        }//end try
    }

    @Override
    public List<OrderDto> getAllOrders(){
        List<OrderEntity> orderEntityList = orders.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity:orderEntityList) {
            orderDtoList.add(mapperOrderService.mapToOrderDto(orderEntity));
        }
        return orderDtoList;
    }

}
