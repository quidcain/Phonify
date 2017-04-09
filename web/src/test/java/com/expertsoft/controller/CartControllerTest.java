package com.expertsoft.controller;

import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import com.expertsoft.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
    private MockMvc mockMvc;
    private List<OrderItem> expectedOrderItems;
    private CartController controller;

    @Mock
    private OrderService orderService;

    @Before
    public void init() {
        expectedOrderItems = createOrderItemList();
        controller = new CartController(orderService);
        when(orderService.getOrderItems())
                .thenReturn(expectedOrderItems);
        when(orderService.getItemsQuantity()).thenReturn(0L);
        when(orderService.getSubtotal()).thenReturn(BigDecimal.ZERO);
    }

    @Test
    public void testCartPage() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/cart.jsp"))
                .build();
        mockMvc.perform(get("/cart"))
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("itemsQuantity"))
                .andExpect(model().attribute("itemsQuantity", orderService.getItemsQuantity()))
                .andExpect(model().attributeExists("subtotal"))
                .andExpect(model().attribute("subtotal", orderService.getSubtotal()))
                .andExpect(model().attributeExists("orderItemList"))
                .andExpect(model().attribute("orderItemList", hasItems(expectedOrderItems.toArray())));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
        doNothing().when(orderService).reduceOrderItem(anyLong(), anyLong());
        mockMvc.perform(post("/deleteOrderItem/{phoneId}", 1)
                .param("quantity", "1"))
                .andExpect(redirectedUrl("/cart"));
        verify(orderService, times(1)).reduceOrderItem(anyLong(), anyLong());
        mockMvc.perform(post("/deleteOrderItem/{phoneId}", 1)
                .param("quantity", "ф"))
                .andExpect(redirectedUrl("/cart"));
        verify(orderService, times(1)).reduceOrderItem(anyLong(), anyLong());
    }

    private List<OrderItem> createOrderItemList() {
        List<OrderItem> orderItems = new ArrayList<>(2);
        OrderItem orderItem = new OrderItem();

        Phone phone = new Phone();
        phone.setId(1);
        phone.setModel("iPhone");
        phone.setColor("black");
        phone.setDisplaySize(4);

        orderItem.setPhone(phone);
        orderItems.add(orderItem);

        orderItem = new OrderItem();
        phone.setId(2);
        phone.setModel("Motorolla Moto X");

        orderItem.setPhone(phone);
        orderItems.add(orderItem);

        return orderItems;
    }
}
