package com.warehouse.server.service.implementation;

import com.warehouse.server.controller.ProductController;
import com.warehouse.server.dto.OrderDto;
import com.warehouse.server.dto.ProductDto;
import com.warehouse.server.model.ProductEntity;
import com.warehouse.server.repositories.ProductRepository;
import com.warehouse.server.service.Mapper;
import com.warehouse.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{

    private final static Logger logger = Logger.getLogger(ProductController.class.getName());

    private final ProductRepository products;

    private final Mapper<ProductDto, ProductEntity> mapperProductService;

    @Autowired
    public ProductServiceImpl(ProductRepository products,
                              @Qualifier("mapperProductServiceImpl") Mapper<ProductDto, ProductEntity> mapperProductService) {
        this.products = products;
        this.mapperProductService = mapperProductService;
    }

    @Override
    public void saveProduct(ProductDto productEntity){
        //ProductEntity productEntity = mapperProductService.mapToEntity(productDto);
        ProductEntity product = new ProductEntity(null, productEntity.getProductNumber(),
                productEntity.getProductName(), productEntity.getPrice(), productEntity.getWeight(),
                null);
        try {
            products.save(product);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe + "\n " + product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            products.deleteById(id);
        }catch(NullPointerException npe){
            logger.warning("No repository: " + npe);
        }
    }

    @Override
    public List<ProductDto> getProducts(Long orderId){
        List<ProductEntity> productEntityList = products.findAll();
        List<ProductDto> productDtoList = new ArrayList<>(); // список продуктов по заказу
        for (ProductEntity productEntity:productEntityList) {
            productDtoList.add(mapperProductService.mapToDto(productEntity));
        }
        //удаляем из списка продуктов все продукты не связанные с запрашиваемым заказом для вывода
        List<ProductDto> forRemove = new ArrayList<>(); //массив с продуктами, отставленными для удаления
        for (ProductDto productDto:productDtoList) {
            AtomicInteger counter = new AtomicInteger();
            for (OrderDto orderDto:productDto.getOrderDtoList()) {
                if(orderDto.getId().equals(orderId)){ //ищем совпадения запрашиваемого заказа и связей
                    counter.getAndIncrement();
                }
            }
            if(counter.get()==0){ //если нужных связей нет - откладываем на удаление
                forRemove.add(productDto);
            }
        }
        for (ProductDto forRemoveProductDto:forRemove) { //удаляем лишние продукты из списка по заказу
            productDtoList.remove(forRemoveProductDto);
        }
        //не возвращяем зависимости
        for (ProductDto productDto:productDtoList) {
            List<OrderDto> orderDtoIdList = new ArrayList<>();
            productDto.setOrderDtoList(orderDtoIdList);
        }
        return productDtoList;
    }

}
