package com.expertsoft.controller;

import com.expertsoft.model.Cart;
import com.expertsoft.service.CartService;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class OrderController {
    private CartService cartService;
    private OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute(cartService.getCart());
        return "order";
    }

    @PostMapping("/order")
    public String orderConfirm(@ModelAttribute @Valid Cart cart, BindingResult result, Model model, HttpServletRequest req) {
        Cart proxyCart = cartService.getCart();
        cart.setCartItems(proxyCart.getCartItems());
        cart.setSubtotal(proxyCart.getSubtotal());
        cart.setDeliveryPrice(proxyCart.getDeliveryPrice());
        model.addAttribute(cart);
        if (result.hasErrors()) {
            return "order";
        }
        req.getSession().invalidate();
        orderService.save(cart);
        return "orderConfirmation";
    }
}
