package com.expertsoft.controller;

import com.expertsoft.controller.form.ErrorMessageResponse;
import com.expertsoft.controller.form.SpecificItemForm;
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
    public ResponseEntity<?> addToCart(@Valid @RequestBody SpecificItemForm specificItemForm, BindingResult result){
        if (result.hasErrors()) {
            ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
            errorMessageResponse.setMessage(result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
        }
        orderService.addOrderItem(specificItemForm.getId(), Long.parseLong(specificItemForm.getQuantity()));
        return new ResponseEntity<>(orderService.getCartIndicator(), HttpStatus.OK);
    }

}
