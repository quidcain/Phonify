package com.expertsoft.controller;

import com.expertsoft.model.Cart;
import com.expertsoft.model.Order;
import com.expertsoft.security.IdEncoder;
import com.expertsoft.service.CartService;
import com.expertsoft.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    private MockMvc mockMvc;
    private OrderController controller;
    private Cart cart = new Cart();
    private Order order = new Order();

    @Mock
    private CartService cartService;

    @Mock
    private OrderService orderService;

    private IdEncoder idEncoder = new IdEncoder("0123456789abcdefghijklmn");


    @Before
    public void init() {
        controller = new OrderController(cartService, orderService, idEncoder);
        when(cartService.getCart()).thenReturn(cart);
        when(orderService.save(any(Cart.class))).thenReturn(order);
        when(orderService.get(1)).thenReturn(order);
        order.setId(1);
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
                .andExpect(model().attributeExists("cart"))
                .andExpect(view().name("redirect:/order/" + idEncoder.encrypt(1L)));
        mockMvc.perform(post("/order")
                .param("firstName", "")
                .param("lastName", "Doe")
                .param("deliveryAddress", "1234 Main Street Anytown")
                .param("contactPhoneNo", "123456"))
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attributeExists("orderDetailsForm"));
    }

    @Test
    public void orderConfirmationTest() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
        mockMvc.perform(get("/order/" + idEncoder.encrypt(1L)))
                .andExpect(view().name("orderConfirmation"))
                .andExpect(model().attributeExists("order"));
        mockMvc.perform(get("/order/" + idEncoder.encrypt(2L)))
                .andExpect(status().is(404));
        mockMvc.perform(get("/order/2"))
                .andExpect(status().is(404));
    }

}
