package com.warehouse.server.service;

import com.warehouse.server.controller.ProductController;
import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.OrderEntity;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    private final Environment env;

    private final ProductRepository products;

    private final MapperProductService mapperProductService;

    @Autowired
    public ProductServiceImpl(Environment env, ProductRepository products, MapperProductService mapperProductService) {
        this.env = env;
        this.products = products;
        this.mapperProductService = mapperProductService;
    }

    @Override
    public void saveProduct(String productNumber, String productName, double price, double weight){
        ProductEntity product = new ProductEntity(0, productNumber, productName, price, weight, false);
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

    @Override
    public List<ProductDto> getProducts(long orderId){
        List<ProductEntity> productEntityList = products.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductEntity productEntity:productEntityList) {
            productDtoList.add(mapperProductService.mapToProductDto(productEntity));
        }
        //удаляем из списка продуктов все продукты не связанные с запрашиваемым заказом для вывода
        AtomicInteger counter = new AtomicInteger();
        for (ProductDto productDto:productDtoList) {
            for (OrderEntity orderDto:productDto.getOrderDtoList()) {
                if(orderDto.getId() == orderId){
                    System.out.println("id"+orderDto.getId());
                    counter.getAndIncrement();
                }
            }
            System.out.println(counter.get());
            if(counter.get()==0){
                productDtoList.remove(productDto);
            }
        }
        //не возвращяем зависимости
        for (ProductDto productDto:productDtoList) {
            List<OrderEntity> orderEntityIdList = new ArrayList<>();
            productDto.setOrderDtoList(orderEntityIdList);
        }
        return productDtoList;
    }

}
