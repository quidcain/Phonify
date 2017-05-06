package com.expertsoft.controller;

import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/orderList"})
public class OrderListController {
    private OrderService orderService;

    @Autowired
    public OrderListController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String productList(Model model) {
        model.addAttribute(orderService.findAll());
        return "orderList";
    }
}
