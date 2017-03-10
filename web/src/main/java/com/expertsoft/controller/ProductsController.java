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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Controller
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
    public ResponseEntity<AddToCartResponse> addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest, BindingResult result){
        AddToCartResponse addToCartResponse = new AddToCartResponse();
        if (result.hasErrors()) {
            return new ResponseEntity<>(addToCartResponse, HttpStatus.BAD_REQUEST);
        }
        orderService.addOrderItem(addToCartRequest.getId(), addToCartRequest.getQuantity());
        addToCartResponse.setItemsQuantity(orderService.getItemsQuantity());
        addToCartResponse.setSubtotal(orderService.getSubtotal());
        return new ResponseEntity<>(addToCartResponse, HttpStatus.OK);
    }

    private static class AddToCartRequest {
        private long id;

        @Max(99)
        @Min(1)
        @NotNull
        private String quantity;

        public AddToCartRequest() {

        }

        public AddToCartRequest(long id, String quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

    private static class AddToCartResponse {
        private long itemsQuantity;
        private String subtotal;

        public AddToCartResponse() {

        }

        public AddToCartResponse(long itemsQuantity, String subtotal) {
            this.itemsQuantity = itemsQuantity;
            this.subtotal = subtotal;
        }

        public long getItemsQuantity() {
            return itemsQuantity;
        }

        public void setItemsQuantity(long itemsQuantity) {
            this.itemsQuantity = itemsQuantity;
        }

        public String getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal;
        }

    }
}

