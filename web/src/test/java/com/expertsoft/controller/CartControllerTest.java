package com.expertsoft.controller;

import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import com.expertsoft.service.ItemsQuantityUnderflow;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
        doNothing().when(orderService).reduceOrderItem(anyLong(), eq(1L));
        doThrow(new ItemsQuantityUnderflow()).when(orderService).reduceOrderItem(anyLong(), eq(2L));
        mockMvc.perform(post("/deleteOrderItem/{phoneId}", 1)
                .param("quantity", "1"))
                .andExpect(redirectedUrl("/cart"));
        verify(orderService, times(1)).reduceOrderItem(anyLong(), anyLong());
        mockMvc.perform(post("/deleteOrderItem/{phoneId}", 1)
                .param("quantity", "ф"))
                .andExpect(flash().attributeExists("errorMessage_1"))
                .andExpect(flash().attribute("errorMessage_1", "Value must be from 1 to 99!"))
                .andExpect(redirectedUrl("/cart"));
        verify(orderService, times(1)).reduceOrderItem(anyLong(), anyLong());
        mockMvc.perform(post("/deleteOrderItem/{phoneId}", 1)
                .param("quantity", "2"))
                .andExpect(flash().attributeExists("errorMessage_1"))
                .andExpect(flash().attribute("errorMessage_1", "Too few items!"))
                .andExpect(redirectedUrl("/cart"));
        verify(orderService, times(2)).reduceOrderItem(anyLong(), anyLong());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
        mockMvc.perform(post("/updateOrderItems")
                .param("quantity_1", "1")
                .param("quantity_2", "2"))
                /*.andExpect(flash().attributeExists("errorMessage_1"))
                .andExpect(flash().attribute("errorMessage_1", "Too few items!"))*/
                .andExpect(redirectedUrl("/cart"));
        mockMvc.perform(post("/updateOrderItems")
                .param("quantity_1", "-3")
                .param("quantity_2", "800"))
                .andExpect(flash().attributeExists("errorMessage_1"))
                .andExpect(flash().attribute("errorMessage_1", "Value must be from 1 to 99!"))
                .andExpect(flash().attributeExists("errorMessage_2"))
                .andExpect(flash().attribute("errorMessage_2", "Value must be from 1 to 99!"))
                .andExpect(redirectedUrl("/cart"));
        mockMvc.perform(post("/updateOrderItems")
                .param("quantity_1", "3")
                .param("quantity_2", "a"))
                .andExpect(flash().attributeExists("errorMessage_2"))
                .andExpect(flash().attribute("errorMessage_2", "Value must be from 1 to 99!"))
                .andExpect(redirectedUrl("/cart"));

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
        orderItem.setQuantity(1);
        orderItems.add(orderItem);

        orderItem = new OrderItem();
        phone = new Phone();
        phone.setId(2);
        phone.setModel("Motorolla Moto X");
        phone.setColor("black");
        phone.setDisplaySize(4);

        orderItem.setPhone(phone);
        orderItem.setQuantity(2);
        orderItems.add(orderItem);

        return orderItems;
    }
}
