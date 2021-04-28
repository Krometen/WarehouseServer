package com.warehouse.server.controller;

import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


//Создает товар по имени, цене, весу и номеру его заказа
@RestController
public class ProductController {

    @Autowired
    private Environment env;

    @Autowired
    private ProductRepository products;

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    //http://localhost:8081/postNewProduct?productName=phone&price=20000&weight=200&orderNumber=1
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/postNewProduct")
    public ProductEntity newProduct(@RequestParam(required = false) String productName,
                                    @RequestParam(required = false) double price,
                                    @RequestParam(required = false) double weight,
                                    @RequestParam(required = false) long orderNumber){
        List<ProductEntity> allProducts = products.findAll();
        long counter = allProducts.size()+1;
        ProductEntity product = new ProductEntity(counter, productName, price, weight, orderNumber, false);
        try {
            products.save(product);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + product);
        }
        logger.info("Created Product ("+productName+") for order number: "+orderNumber);
        return product;
    }

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
            props.setProperty("password", env.getProperty("spring.datasource.password"));
            props.setProperty("ssl","false");
            conn = DriverManager.getConnection(url, props);

            statement = conn.createStatement();
            String result = String.format("UPDATE product_entity SET is_deleted = true WHERE product_number = %s ;", number);
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

        List<ProductEntity> allProducts = products.findAll();
        logger.info("Deleted product: "+allProducts.get((int) (allProducts.size() - number)).getProductName()+" №: "
                +allProducts.get((int) (allProducts.size() - number)).getProductNumber());
        return "Deleted product: "+allProducts.get((int) (allProducts.size() - number)).getProductName()+" №: "
                +allProducts.get((int) (allProducts.size() - number)).getProductNumber();
    }

    //http://localhost:8081/getProducts?order=5
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/getProducts", method= RequestMethod.GET)
    public @ResponseBody
    List<ProductEntity> getProductsJson(@RequestParam(required = false) long order) {
        List<ProductEntity> arr = products.findAll();
        arr.removeIf(s -> !(s.getOrderNumber() == order));
        logger.info("Get products");
        return arr;
    }
}