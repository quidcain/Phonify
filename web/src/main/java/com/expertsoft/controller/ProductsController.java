package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class ProductsController {

    private PhoneDao phoneDao;
    private Order order;

    @Autowired
    public ProductsController(PhoneDao phoneDao, Order order) {
        this.phoneDao = phoneDao;
        this.order = order;
    }

    @GetMapping({"/", "/products"})
    public String products(Model model) {
        model.addAttribute(phoneDao.findAll());
        System.out.println(order);
        return "products";
    }

    @PostMapping("/add/{phoneId}")
    public String addToCart(@PathVariable long phoneId, @Valid OrderItem orderItem,
                            BindingResult result, RedirectAttributes model){
        if (result.hasErrors()) {
            System.out.println("error");
        } else {
            System.out.println("done");
        }
        orderItem.setPhone(phoneDao.get(phoneId));
        order.getOrderItems().add(orderItem);
        System.out.println(order.getOrderItems().size());
        return "redirect:/products";
    }
}
