package com.expertsoft.controller;

import com.expertsoft.service.OrderService;
import com.expertsoft.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
// TODO: Use ecommerce naming. See spec (ProductListController)
public class ProductsController {
    private PhoneService phoneService;
    private OrderService orderService;

    @Autowired
    public ProductsController(PhoneService phoneService, OrderService orderService) {
        this.phoneService = phoneService;
        this.orderService = orderService;
    }

    @GetMapping({"/", "/products"})
    public String products(Model model) {
        model.addAttribute(phoneService.findAll());
        return "products";
    }

    @ResponseBody
    @RequestMapping(value = "/addToCart")
    public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest, BindingResult result){
        if (result.hasErrors()) {
            // TODO: return errors
            AddToCartErrorResponse response = new AddToCartErrorResponse();
            response.setMessage("Value must be from 1 to 99!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        AddToCartSuccessResponse response = new AddToCartSuccessResponse();
        orderService.addOrderItem(addToCartRequest.getId(), addToCartRequest.getQuantity());
        response.setItemsQuantity(orderService.getItemsQuantity());
        response.setSubtotal(orderService.getSubtotal());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

