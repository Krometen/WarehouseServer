package com.warehouse.server.service;

import com.warehouse.server.controller.ProductController;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    private final Environment env;

    private final ProductRepository products;

    @Autowired
    public ProductServiceImpl(Environment env, ProductRepository products) {
        this.env = env;
        this.products = products;
    }

    @Override
    public void saveProduct(String productNumber, String productName, double price, double weight){
        List<ProductEntity> allProducts = products.findAll();
        long id = allProducts.size()+1;
        ProductEntity product = new ProductEntity(id, productNumber, productName, price, weight, false);
        try {
            products.save(product);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + product);
        }
    }

    @Override
    public void deleteProduct(long id) {
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
            String result = String.format("UPDATE product_entity SET is_deleted = true WHERE id = %s ;", id);
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

//    @Override
//    public List<ProductEntity> getProducts(long id){
//        List<ProductEntity> arr = products.findAll();
//        arr.removeIf(s -> !(s.getId() == id));
//        return arr;
//    }

}
