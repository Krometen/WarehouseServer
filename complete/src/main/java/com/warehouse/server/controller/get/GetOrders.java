package com.warehouse.server.controller.get;

import com.warehouse.server.model.OrderData;
import com.warehouse.server.repos.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.logging.Logger;


//Возвращает JSON всех заказов.
@Controller
public class GetOrders {
    @Autowired
    private OrdersRepo orders;

    private final static Logger logger = Logger.getLogger(GetOrders.class.getName());

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/getOrders", method= RequestMethod.GET)
    public @ResponseBody
    List<OrderData> getOrdersJson() {
        List<OrderData> allOrders = orders.findAllByOrderByOrderNumberDesc();
        logger.info("Get orders");

        return allOrders;
    }
}