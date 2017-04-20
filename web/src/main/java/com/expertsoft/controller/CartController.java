package com.expertsoft.controller;

import com.expertsoft.controller.form.EntireItemsForm;
import com.expertsoft.controller.form.GenericQuantityForm;
import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private OrderService orderService;

    @Autowired
    public CartController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public String cart(Model model) {
        model.addAttribute(orderService.getOrderItems());
        return "cart";
    }

    @PostMapping("/deleteOrderItem/{phoneId}")
    public String deleteOrderItem(@PathVariable long phoneId) {
        orderService.deleteOrderItem(phoneId);
        return "redirect:/cart";
    }

    @PostMapping("/updateOrderItems")
    public String updateOrderItem(@Valid EntireItemsForm entireItemsForm, BindingResult result, RedirectAttributes model) {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                error.getDefaultMessage();
                String field = error.getField();
                int start = field.indexOf('[') + 1;
                int end = field.lastIndexOf(']');
                String phoneId = field.substring(start, end);
                model.addFlashAttribute("errorMessage_" + phoneId, error.getDefaultMessage());
                model.addFlashAttribute("quantity_" + phoneId, error.getRejectedValue());
            }
        } else {
            Map<Long, GenericQuantityForm> items = entireItemsForm.getItems();
            for (Map.Entry<Long, GenericQuantityForm> item : items.entrySet()) {
                long phoneId = item.getKey();
                long quantity = Long.parseLong(item.getValue().getQuantity());
                orderService.updateOrderItem(phoneId, quantity);
            }
        }
        return "redirect:/cart";
    }
}
