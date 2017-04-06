package com.expertsoft.controller;


import com.expertsoft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailsController {
    private PhoneService phoneService;

    @Autowired
    public ProductDetailsController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/productDetails/{phoneId}")
    public String productDetails(@PathVariable long phoneId, Model model) {
        model.addAttribute(phoneService.get(phoneId));
        return "productDetails";
    }

}
