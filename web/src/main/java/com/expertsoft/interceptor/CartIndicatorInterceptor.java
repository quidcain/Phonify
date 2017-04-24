package com.expertsoft.interceptor;


import com.expertsoft.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartIndicatorInterceptor extends HandlerInterceptorAdapter {
    private CartService cartService;

    @Autowired
    public CartIndicatorInterceptor(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("cartIndicator", cartService.getCartIndicator());
        return true;
    }
}
