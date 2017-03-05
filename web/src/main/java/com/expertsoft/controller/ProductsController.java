package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public AddToCartResponse addToCart(@RequestBody @Valid AddToCardRequest addPhoneRequest){
        Phone phone = phoneDao.get(addPhoneRequest.getPhoneId());
        order.getOrderItems().add(new OrderItem(
                phone,
                addPhoneRequest.getQuantity(),
                order)
        );
        long itemsQuantity = 0;
        order.setTotalPrice(order.getTotalPrice().add(
            phone.getPrice().multiply(BigDecimal.valueOf(addPhoneRequest.getQuantity()))));
        for (OrderItem item : order.getOrderItems()) {
            itemsQuantity += item.getQuantity();
        }
        return new AddToCartResponse(itemsQuantity, order.getTotalPrice().toString());
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute(phoneDao.findAll());
        return "test";
    }

    private static class AddToCardRequest {
        private long phoneId;

        @Digits(integer = 100, fraction = 0)
        @NotNull
        private long quantity;

        public long getPhoneId() {
            return phoneId;
        }

        public void setPhoneId(long phoneId) {
            this.phoneId = phoneId;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }
    }

    private static class AddToCartResponse {
        private long itemsQuantity;
        private String totalPrice;

        public AddToCartResponse(long itemsQuantity) {
            this.itemsQuantity = itemsQuantity;
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

