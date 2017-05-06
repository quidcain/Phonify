package com.expertsoft.controller;

import com.expertsoft.model.Order;
import com.expertsoft.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsControllerTest {
    private MockMvc mockMvc;
    private Order order;

    @Mock
    private OrderService orderService;

    @Before
    public void init() {
        order = createOrder();
        OrderDetailsController controller = new OrderDetailsController(orderService);
        when(orderService.get(anyLong()))
                .thenReturn(order);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProductDetailsPage() throws Exception {
        mockMvc.perform(get("/orderDetails/{orderId}", 1))
                .andExpect(view().name("orderDetails"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attribute("order", order));
    }

    private Order createOrder() {
        Order order = new Order();
        order.setSubtotal(BigDecimal.ONE);
        order.setDeliveryPrice(BigDecimal.ONE);
        order.setFirstName("John");
        order.setLastName("Doe");
        order.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        order.setContactPhoneNo("1-800-354-0387");
        return order;
    }
}
