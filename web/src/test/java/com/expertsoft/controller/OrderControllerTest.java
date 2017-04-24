package com.expertsoft.controller;

import com.expertsoft.model.Cart;
import com.expertsoft.service.CartService;
import com.expertsoft.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    private MockMvc mockMvc;
    private OrderController controller;
    private Cart cart = new Cart();

    @Mock
    private CartService cartService;

    @Mock
    private OrderService orderService;


    @Before
    public void init() {
        controller = new OrderController(cartService, orderService);
        when(cartService.getCart())
                .thenReturn(cart);
    }

    @Test
    public void orderPageTest() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/order.jsp"))
                .build();
        mockMvc.perform(get("/order"))
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attribute("cart", cart));
    }

    @Test
    public void orderConfirmTest() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/order.jsp"))
                .build();
        mockMvc.perform(post("/order")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("deliveryAddress", "1234 Main Street Anytown")
                .param("contactPhoneNo", "123456"))
                .andExpect(view().name("orderConfirmation"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attribute("cart", cart));
        mockMvc.perform(post("/order")
                .param("firstName", "")
                .param("lastName", "Doe")
                .param("deliveryAddress", "1234 Main Street Anytown")
                .param("contactPhoneNo", "123456"))
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attribute("cart", cart));
    }

}
