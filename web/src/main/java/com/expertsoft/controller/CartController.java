package com.expertsoft.controller;

import com.expertsoft.controller.cart.QuantityWrapper;
import com.expertsoft.model.OrderItem;
import com.expertsoft.service.ItemsQuantityUnderflow;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public String deleteOrderItem(@PathVariable long phoneId, @Valid QuantityWrapper quantityWrapper, BindingResult result, RedirectAttributes model) {
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

    @PostMapping("/updateOrderItems")
    public String updateOrderItem(@RequestParam Map<String, String> params, RedirectAttributes model) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            long phoneId = Long.parseLong(entry.getKey().split("_")[1]);
            try {
                long parsedQuantity = Long.parseLong(entry.getValue());
                if (parsedQuantity < 1 || parsedQuantity > 99)
                    throw new NumberFormatException();
                orderService.updateOrderItem(phoneId, parsedQuantity);
            } catch (NumberFormatException e ) {
                model.addFlashAttribute("errorMessage_" + phoneId, "Value must be from 1 to 99!");
                model.addFlashAttribute(entry.getKey(), entry.getValue());
            }
        }
        return "redirect:/cart";
    }
}
