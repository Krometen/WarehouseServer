package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


//Удаляет заказ по номеру
@RestController
public class DeleteOrder {
    @Autowired
    private OrderRepository orders;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    @Autowired
    private Environment env;

    //http://localhost:8081/deleteOrder?number=1
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/deleteOrder")
    public String deleteOrder(@RequestParam(required = false, defaultValue = "undefined") long number) {
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
            }//end finally try
        }//end try

        List<OrderEntity> allOrders = orders.findAll();
        logger.info("Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).getOrderNumber());
        return "Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).getOrderNumber();
    }
}