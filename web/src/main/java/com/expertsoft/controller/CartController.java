package com.expertsoft.controller;

import com.expertsoft.controller.form.UpdateCartItemsForm;
import com.expertsoft.controller.form.QuantityForm;
import com.expertsoft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public String cart(Model model) {
        model.addAttribute(cartService.getCartItems());
        return "cart";
    }

    @PostMapping("/deleteCartItem/{phoneId}")
    public String deleteCartItem(@PathVariable long phoneId) {
        cartService.deleteCartItem(phoneId);
        return "redirect:/cart";
    }

    @PostMapping("/updateCartItems")
    public String updateCartItems(@Valid UpdateCartItemsForm updateCartItemsForm, BindingResult result, RedirectAttributes model) {
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
            Map<Long, QuantityForm> items = updateCartItemsForm.getItems();
            for (Map.Entry<Long, QuantityForm> item : items.entrySet()) {
                long phoneId = item.getKey();
                long quantity = Long.parseLong(item.getValue().getQuantity());
                cartService.updateCartItem(phoneId, quantity);
            }
        }
        return "redirect:/cart";
    }
}
