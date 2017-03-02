package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ProductsController {

    private PhoneDao phoneDao;

    @Autowired
    public ProductsController(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute(phoneDao.findAll());
        return "products";
    }
}
