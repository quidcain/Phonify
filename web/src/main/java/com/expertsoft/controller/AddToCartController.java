package com.expertsoft.controller;

import com.expertsoft.controller.addtocart.AddToCartErrorResponse;
import com.expertsoft.controller.addtocart.AddToCartRequest;
import com.expertsoft.controller.addtocart.AddToCartSuccessResponse;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class AddToCartController {
    private OrderService orderService;

    @Autowired
    public AddToCartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @RequestMapping(value = "/addToCart")
    public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest, BindingResult result){
        if (result.hasErrors()) {
            AddToCartErrorResponse response = new AddToCartErrorResponse();
            response.setMessage("Value must be from 1 to 99!"); //TODO: use binding result
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        AddToCartSuccessResponse response = new AddToCartSuccessResponse();
        orderService.addOrderItem(addToCartRequest.getId(), Long.parseLong(addToCartRequest.getQuantity()));
        response.setItemsQuantity(orderService.getItemsQuantity());
        response.setSubtotal(orderService.getSubtotal());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
