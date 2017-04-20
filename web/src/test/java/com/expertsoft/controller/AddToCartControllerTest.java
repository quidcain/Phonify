package com.expertsoft.controller;

import com.expertsoft.model.CartIndicator;
import com.expertsoft.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AddToCartControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Before
    public void init() {
        AddToCartController controller = new AddToCartController(orderService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void addToCartTest() throws Exception {
        CartIndicator cartIndicator = new CartIndicator();
        cartIndicator.setItemsQuantity(1);
        cartIndicator.setSubtotal(BigDecimal.ONE);
        when(orderService.getCartIndicator()).thenReturn(cartIndicator);
        mockMvc.perform(post("/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestInJson(0, "2")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(post("/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestInJson(0, "100")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mockMvc.perform(post("/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestInJson(0, "abc")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private String createRequestInJson(long phoneId, String quantity) {
        return "{ \"phoneId\": \"" + phoneId + "\", " +
                "\"quantity\":\"" + quantity + "\"}";
    }
}
