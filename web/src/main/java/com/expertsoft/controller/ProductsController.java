package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


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
        return "products";
    }

    @ResponseBody
    @RequestMapping(value = "/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest, BindingResult result){
        AddToCartResponse addToCartResponse = new AddToCartResponse();
        if (result.hasErrors()) {
            return new ResponseEntity<>(addToCartResponse, HttpStatus.BAD_REQUEST);
        }
        Phone phone = phoneDao.get(addToCartRequest.getPhoneId());
        order.getOrderItems().add(new OrderItem(
                phone,
                Long.parseLong(addToCartRequest.getQuantity()),
                order)
        );
        long itemsQuantity = 0;
        order.setTotalPrice(order.getTotalPrice().add(
            phone.getPrice().multiply(BigDecimal.valueOf(Long.parseLong(addToCartRequest.getQuantity())))));
        for (OrderItem item : order.getOrderItems()) {
            itemsQuantity += item.getQuantity();
        }
        addToCartResponse.setItemsQuantity(itemsQuantity);
        addToCartResponse.setTotalPrice(order.getTotalPrice().toString());
        return new ResponseEntity<>(addToCartResponse, HttpStatus.OK);
    }

    private static class AddToCartRequest {
        private long phoneId;

        @Digits(integer = 2, fraction = 0)
        @NotNull
        private String quantity;

        public AddToCartRequest() {

        }

        public AddToCartRequest(long phoneId, String quantity) {
            this.phoneId = phoneId;
            this.quantity = quantity;
        }

        public long getPhoneId() {
            return phoneId;
        }

        public void setPhoneId(long phoneId) {
            this.phoneId = phoneId;
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
        private String totalPrice;

        public AddToCartResponse() {

        }

        public AddToCartResponse(long itemsQuantity, String totalPrice) {
            this.itemsQuantity = itemsQuantity;
            this.totalPrice = totalPrice;
        }

        public long getItemsQuantity() {
            return itemsQuantity;
        }

        public void setItemsQuantity(long itemsQuantity) {
            this.itemsQuantity = itemsQuantity;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

    }
}

