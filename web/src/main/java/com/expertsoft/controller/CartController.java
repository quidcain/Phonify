package com.expertsoft.controller;

import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("itemsQuantity", orderService.getItemsQuantity());
        model.addAttribute("subtotal", orderService.getSubtotal());
        model.addAttribute(orderService.getOrderItems());
        return "cart";
    }

    @PostMapping("/deleteOrderItem/{phoneId}")
    public String delete(@PathVariable long phoneId, @RequestParam long quantity) {
        orderService.reduceOrderItem(phoneId, quantity);
        return "redirect:/cart";
    }


}
