package com.expertsoft.controller;

import com.expertsoft.controller.cart.QuantityWrapper;
import com.expertsoft.service.ItemsQuantityUnderflow;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CartController {
    private OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("itemsQuantity", orderService.getItemsQuantity());
        model.addAttribute("subtotal", orderService.getSubtotal());
        model.addAttribute(orderService.getOrderItems());
        return "cart";
    }

    @PostMapping("/deleteOrderItem/{phoneId}")
    public String delete(@PathVariable long phoneId, @Valid QuantityWrapper quantityWrapper, BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            model.addFlashAttribute("errorMessage_" + phoneId, "Value must be from 1 to 99!");
            return "redirect:/cart";
        }
        try {
            orderService.reduceOrderItem(phoneId, quantityWrapper.getQuantity());
        } catch (ItemsQuantityUnderflow e) {
            model.addFlashAttribute("errorMessage_" + phoneId, "Too few items!");
        }
        return "redirect:/cart";
    }
}
