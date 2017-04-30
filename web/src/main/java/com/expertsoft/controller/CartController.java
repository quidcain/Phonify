package com.expertsoft.controller;

import com.expertsoft.controller.form.QuantityForm;
import com.expertsoft.controller.form.UpdateCartItemsForm;
import com.expertsoft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
        model.addAttribute(cartService.getCart().getCartItems());
        model.addAttribute(new UpdateCartItemsForm());
        return "cart";
    }

    @PostMapping("/deleteCartItem/{phoneId}")
    public String deleteCartItem(@PathVariable long phoneId) {
        cartService.deleteCartItem(phoneId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCartItems(@ModelAttribute @Valid UpdateCartItemsForm updateCartItemsForm, BindingResult result, Model model) {
        model.addAttribute(cartService.getCart().getCartItems());
        if (!result.hasErrors()) {
            Map<Long, QuantityForm> formItems = updateCartItemsForm.getItems();
            Map<Long, Long> items = new HashMap<>();
            for (Map.Entry<Long, QuantityForm> item : formItems.entrySet()) {
                long phoneId = item.getKey();
                long quantity = Long.parseLong(item.getValue().getQuantity());
                items.put(phoneId, quantity);
            }
            cartService.updateCartItems(items);
        }
        return "cart";
    }
}
