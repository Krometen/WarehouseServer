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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
    public void saveOrder(String date, String address){
        List<OrderEntity> allOrders = orders.findAll();
        long counter = allOrders.size()+1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate localDate = LocalDate.parse(date, formatter);
        OrderEntity order = new OrderEntity(localDate, address, counter, false);
        try {
            orders.save(order);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public void deleteOrder(long number) {
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
            String result = String.format("UPDATE order_entity SET is_deleted = true WHERE order_number = %s ;", number);
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
