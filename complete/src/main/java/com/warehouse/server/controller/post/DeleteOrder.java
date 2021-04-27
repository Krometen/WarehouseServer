package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.Order;
import com.warehouse.server.repos.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrdersRepo orders;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

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
            props.setProperty("password","1231");
            props.setProperty("ssl","false");
            conn = DriverManager.getConnection(url, props);

            statement = conn.createStatement();
            String result = String.format("UPDATE order_data SET deleted = true WHERE order_number = %s ;", number);
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

        List<Order> allOrders = orders.findAllByOrderByOrderNumberDesc();
        logger.info("Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).orderNumber);
        return "Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).orderNumber;
    }
}