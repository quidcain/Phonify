package com.expertsoft.controller;

import com.expertsoft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProductListController {
    private PhoneService phoneService;

    @Autowired
    public ProductListController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping({"/", "/productList"})
    public String productList(Model model) {
        model.addAttribute(phoneService.findAll());
        return "productList";
    }
}

