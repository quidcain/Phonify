package com.expertsoft.controller;

import com.expertsoft.model.Phone;
import com.expertsoft.service.PhoneService;
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
public class ProductDetailsControllerTest {
    private MockMvc mockMvc;
    private Phone phone;

    @Mock
    private PhoneService phoneService;

    @Before
    public void init() {
        phone = createPhone();
        ProductDetailsController controller = new ProductDetailsController(phoneService);
        when(phoneService.get(anyLong()))
                .thenReturn(phone);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProductDetailsPage() throws Exception {
        mockMvc.perform(get("/productDetails/{phoneId}", 1))
                .andExpect(view().name("productDetails"))
                .andExpect(model().attributeExists("phone"))
                .andExpect(model().attribute("phone", phone));
    }

    private Phone createPhone() {
        Phone phone = new Phone();
        phone.setModel("iPhone");
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        return phone;
    }
}
