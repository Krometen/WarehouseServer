package com.warehouse.server.controller.post;

import com.warehouse.server.controller.get.GetOrders;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


//Удаляет товар по номеру
@RestController
public class DeleteProduct {
    @Autowired
    private ProductsRepo products;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    //http://localhost:8081/deleteProduct?number=11
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(required = false) long number){
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
            String result = String.format("UPDATE product_data SET deleted = true WHERE product_number = %s ;", number);
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

        List<ProductEntity> allProducts = products.findAllByOrderByProductNumberDesc();
        logger.info("Deleted product: "+allProducts.get((int) (allProducts.size() - number)).getProductName()+" №: "
                +allProducts.get((int) (allProducts.size() - number)).getProductNumber());
        return "Deleted product: "+allProducts.get((int) (allProducts.size() - number)).getProductName()+" №: "
                +allProducts.get((int) (allProducts.size() - number)).getProductNumber();
    }
}