package com.expertsoft.controller;

import com.expertsoft.model.Order;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute(orderService.getOrder());
        return "order";
    }

    @PostMapping("/order")
    public String orderConfirm(@ModelAttribute @Valid Order order, BindingResult result, Model model, HttpServletRequest req) {
        Order proxyOrder = orderService.getOrder();
        order.setOrderItems(proxyOrder.getOrderItems());
        order.setSubtotal(proxyOrder.getSubtotal());
        order.setDeliveryPrice(proxyOrder.getDeliveryPrice());
        model.addAttribute(order);
        if (result.hasErrors()) {
            return "order";
        }
        req.getSession().invalidate();
        orderService.save(order);
        return "orderConfirmation";
    }
}
