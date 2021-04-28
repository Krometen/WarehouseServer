package com.warehouse.server.controller;

import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.servicelayer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


//Создает заказ по дате и адресу
@RestController
public class OrderController {

    private final Environment env;

    private final OrderService orderService;

    private final static Logger logger = Logger.getLogger(OrderController.class.getName());

    @Autowired
    public OrderController(Environment env, OrderService orderService) {
        this.env = env;
        this.orderService = orderService;
    }

    //http://localhost:8081/postNewOrder?date=01.02.21&address=PUSHKIN+STREET
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/postNewOrder")
    public OrderEntity newOrder(@RequestParam(required = false) LocalDate date,
                                @RequestParam(required = false) String address) {
        List<OrderEntity> allOrders = orderService.getAllOrders();
        long counter = allOrders.size()+1;
        OrderEntity order = new OrderEntity(date, address, counter, false);
        try {
            orderService.saveOrder(order);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + order);
        }
        return order;
    }

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

        List<OrderEntity> allOrders = orderService.getAllOrders();
        logger.info("Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).getOrderNumber());
        return "Deleted order Number: "+allOrders.get((int) (allOrders.size() - number)).getOrderNumber();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value="/getOrders")
    public @ResponseBody
    List<OrderEntity> getOrdersJson() {
        List<OrderEntity> allOrders = orderService.getAllOrders();
        logger.info("Get orders");

        return allOrders;
    }
}