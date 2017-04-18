package com.expertsoft.controller;

import com.expertsoft.service.OrderService;
import com.expertsoft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping({"/", "/productList"})
public class ProductListController {
    private PhoneService phoneService;
    private OrderService orderService;

    @Autowired
    public ProductListController(PhoneService phoneService, OrderService orderService) {
        this.phoneService = phoneService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String productList(Model model) {
        model.addAttribute("itemsQuantity", orderService.getItemsQuantity());
        model.addAttribute("subtotal", orderService.getSubtotal());
        model.addAttribute(phoneService.findAll());
        return "productList";
    }
}

