package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Order;
import com.expertsoft.model.Phone;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


public class ProductsControllerTest {

    private MockMvc mockMvc;
    private List<Phone> expectedPhones;

    @Before
    public void init() {
        expectedPhones = createPhoneList(5);
        PhoneDao mockDao = mock(PhoneDao.class);
        Order order = new Order();
        ProductsController controller = new ProductsController(mockDao, order);
        when(mockDao.findAll())
                .thenReturn(expectedPhones);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProductsPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("phoneList"))
                .andExpect(model().attribute("phoneList", hasItems(expectedPhones.toArray())));
    }

    @Test
    public void addToCartTest() throws Exception {
        expectedPhones = createPhoneList(5);
        PhoneDao mockDao = mock(PhoneDao.class);
        Order order = new Order();
        ProductsController controller = new ProductsController(mockDao, order);
        when(mockDao.findAll())
                .thenReturn(expectedPhones);
        mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestInJson(0L, "2")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static List<Phone> createPhoneList(int count) {
        List<Phone> phones = new ArrayList<>();
        for (int i=0; i < count; i++) {
            phones.add(new Phone("iPhone" + i, "black", 4, BigDecimal.valueOf(800)));
        }
        return phones;
    }

    private String createRequestInJson(long phoneId, String quantity) {
        return "{ \"phoneId\": \"" + phoneId + "\", " +
                "\"quantity\":\"" + quantity + "\"}";
    }
}
