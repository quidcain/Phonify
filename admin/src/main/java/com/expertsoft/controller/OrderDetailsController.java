package com.expertsoft.controller;

import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    private OrderService orderService;

    @Autowired
    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public String productDetails(@PathVariable long orderId, Model model) {
        model.addAttribute(orderService.get(orderId));
        return "orderDetails";
    }
}
