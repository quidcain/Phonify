package com.expertsoft.interceptor;


import com.expertsoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartIndicatorInterceptor extends HandlerInterceptorAdapter {
    private OrderService orderService;

    @Autowired
    public CartIndicatorInterceptor(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("itemsQuantity", orderService.getItemsQuantity());
        request.setAttribute("subtotal", orderService.getSubtotal());
        return true;
    }
}
