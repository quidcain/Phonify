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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProductListControllerTest {
    private MockMvc mockMvc;
    private List<Phone> expectedPhones;

    @Mock
    private PhoneService phoneService;

    @Before
    public void init() {
        expectedPhones = createPhoneList(5);
        ProductListController controller = new ProductListController(phoneService);
        when(phoneService.findAll())
                .thenReturn(expectedPhones);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProductListPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("phoneList"))
                .andExpect(model().attribute("phoneList", hasItems(expectedPhones.toArray())));
    }

    private List<Phone> createPhoneList(int count) {
        List<Phone> phones = new ArrayList<>();
        for (int i=0; i < count; i++) {
            Phone phone = new Phone();
            phone.setModel("iPhone" + i);
            phone.setColor("black");
            phone.setDisplaySize(4);
            phone.setPrice(BigDecimal.ONE);
            phones.add(phone);
            when(phoneService.get((long)i))
                    .thenReturn(phones.get(i));
        }
        return phones;
    }

}
