package com.warehouse.server.service;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService{

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orders;

    private final Environment env;

    @Autowired
    public OrderServiceImpl(OrderRepository orders, Environment env) {
        this.orders = orders;
        this.env = env;
    }

    @Override
    public void saveOrder(String orderNumber, LocalDate date, String address){
        List<OrderEntity> allOrders = orders.findAll();
        long id = allOrders.size()+1;
        OrderEntity order = new OrderEntity(id, orderNumber, date, address, false);
        try {
            orders.save(order);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public void deleteOrder(long id) {
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
    public List<OrderEntity> getAllOrders(){
        return orders.findAll();
    }

}
