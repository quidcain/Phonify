package com.expertsoft.controller;

import com.expertsoft.model.CartItem;
import com.expertsoft.model.Phone;
import com.expertsoft.service.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

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
    private List<CartItem> expectedCartItems;
    private CartController controller;

    @Mock
    private CartService cartService;

    @Before
    public void init() {
        expectedCartItems = createCartItemList();
        controller = new CartController(cartService);
        when(cartService.getCart().getCartItems())
                .thenReturn(expectedCartItems);
    }

    @Test
    public void testCartPage() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/cart.jsp"))
                .build();
        mockMvc.perform(get("/cart"))
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cartItemList"))
                .andExpect(model().attribute("cartItemList", hasItems(expectedCartItems.toArray())));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
        doNothing().when(cartService).deleteCartItem(anyLong());
        mockMvc.perform(post("/cart/deleteCartItem/{phoneId}", 1))
                .andExpect(redirectedUrl("/cart"));
        verify(cartService, times(1)).deleteCartItem(anyLong());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc = standaloneSetup(controller)
                .build();
        mockMvc.perform(post("/cart/updateCartItems")
                .param("items[1].quantity", "1")
                .param("items[2].quantity", "2"))
                .andExpect(redirectedUrl("/cart"));
        mockMvc.perform(post("/cart/updateCartItems")
                .param("items[1].quantity", "-3")
                .param("items[2].quantity", "800"))
                .andExpect(flash().attributeExists("errorMessage_1"))
                .andExpect(flash().attribute("errorMessage_1", "Value must be from 1 to 99!"))
                .andExpect(flash().attributeExists("errorMessage_2"))
                .andExpect(flash().attribute("errorMessage_2", "Value must be from 1 to 99!"))
                .andExpect(redirectedUrl("/cart"));
        mockMvc.perform(post("/cart/updateCartItems")
                .param("items[1].quantity", "3")
                .param("items[2].quantity", "a"))
                .andExpect(flash().attributeExists("errorMessage_2"))
                .andExpect(flash().attribute("errorMessage_2", "Value must be from 1 to 99!"))
                .andExpect(redirectedUrl("/cart"));

    }

    private List<CartItem> createCartItemList() {
        List<CartItem> cartItems = new ArrayList<>(2);
        CartItem cartItem = new CartItem();

        Phone phone = new Phone();
        phone.setId(1);
        phone.setModel("iPhone");
        phone.setColor("black");
        phone.setDisplaySize(4);

        cartItem.setPhone(phone);
        cartItem.setQuantity(1);
        cartItems.add(cartItem);

        cartItem = new CartItem();
        phone = new Phone();
        phone.setId(2);
        phone.setModel("Motorolla Moto X");
        phone.setColor("black");
        phone.setDisplaySize(4);

        cartItem.setPhone(phone);
        cartItem.setQuantity(2);
        cartItems.add(cartItem);

        return cartItems;
    }
}
