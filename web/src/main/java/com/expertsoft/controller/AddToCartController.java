package com.expertsoft.controller;

import com.expertsoft.controller.form.ErrorMessageResponse;
import com.expertsoft.controller.form.AddToCartForm;
import com.expertsoft.model.CartIndicator;
import com.expertsoft.service.CartService;
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
    private CartService cartService;

    @Autowired
    public AddToCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @ResponseBody
    @RequestMapping(value = "/addToCart")
    public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartForm addToCartForm, BindingResult result){
        if (result.hasErrors()) {
            ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
            errorMessageResponse.setMessage(result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
        }
        cartService.addOrderItem(addToCartForm.getId(), Long.parseLong(addToCartForm.getQuantity()));
        CartIndicator cartIndicator = cartService.getCart().getCartIndicator();
        return new ResponseEntity<>(cartIndicator, HttpStatus.OK);
    }

}
