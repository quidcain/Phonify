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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class OrderListControllerTest {
    private MockMvc mockMvc;
    private List<Order> expectedOrders;

    @Mock
    private OrderService orderService;

    @Before
    public void init() {
        expectedOrders = createOrderList(5);
        OrderListController controller = new OrderListController(orderService);
        when(orderService.findAll())
                .thenReturn(expectedOrders);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProductListPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("orderList"))
                .andExpect(model().attributeExists("orderList"))
                .andExpect(model().attribute("orderList", hasItems(expectedOrders.toArray())));
    }

    private List<Order> createOrderList(int count) {
        List<Order> orders = new ArrayList<>();
        for (int i=0; i < count; i++) {
            Order order = new Order();
            order.setSubtotal(BigDecimal.ONE);
            order.setDeliveryPrice(BigDecimal.ONE);
            order.setFirstName("John" + i);
            order.setLastName("Doe");
            order.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
            order.setContactPhoneNo("1-800-354-0387");
            orders.add(order);
            when(orderService.get((long)i))
                    .thenReturn(orders.get(i));
        }
        return orders;
    }
}
