package com.expertsoft.controller;

import com.expertsoft.controller.form.OrderDetailsForm;
import com.expertsoft.model.Cart;
import com.expertsoft.model.Order;
import com.expertsoft.security.IdCryptUtils;
import com.expertsoft.service.CartService;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    private CartService cartService;
    private OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String order(Model model) {
        model.addAttribute(cartService.getCart());
        model.addAttribute(new OrderDetailsForm());
        return "order";
    }

    @PostMapping
    public String orderConfirm(@ModelAttribute @Valid OrderDetailsForm orderDetailsForm, BindingResult result, Model model, HttpServletRequest req) {
        Cart cart = cartService.getCart();
        model.addAttribute(cart);
        if (result.hasErrors()) {
            return "order";
        }
        cart.setFirstName(orderDetailsForm.getFirstName());
        cart.setLastName(orderDetailsForm.getLastName());
        cart.setDeliveryAddress(orderDetailsForm.getDeliveryAddress());
        cart.setContactPhoneNo(orderDetailsForm.getContactPhoneNo());
        cart.setAdditionalInfo(orderDetailsForm.getAdditionalInfo());

        Order order = orderService.save(cart);
        req.getSession().invalidate();

        return "redirect:/order/" + IdCryptUtils.encrypt(order.getId());
    }

    @GetMapping("/{orderId}")
    public String orderConfirmation(@PathVariable String orderId, Model model) throws OrderNotFoundException {
        Order order = null;
        try {
            order = orderService.get(IdCryptUtils.decrypt(orderId));
        } catch (IllegalArgumentException e) {
        }
        if (order == null)
            throw new OrderNotFoundException();
        model.addAttribute(order);
        return "orderConfirmation";
    }
}
