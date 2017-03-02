package com.expertsoft.controller;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Phone;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by stoat on 2/28/17.
 */
public class ProductsControllerTest {
    @Test
    public void testProductsPage() throws Exception {
        List<Phone> expectedPhones = createPhoneList(5);
        PhoneDao mockDao = mock(PhoneDao.class);
        ProductsController controller = new ProductsController(mockDao);
        when(mockDao.findAll())
                .thenReturn(expectedPhones);

        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
            .andExpect(view().name("products"))
            .andExpect(model().attributeExists("phoneList"))
            .andExpect(model().attribute("phoneList", hasItems(expectedPhones.toArray())));
    }



    private List<Phone> createPhoneList(int count) {
        List<Phone> phones = new ArrayList<Phone>();
        for (int i=0; i < count; i++) {
            phones.add(new Phone(i,
                    "iPhone" + i, "black", 4, BigDecimal.valueOf(800)));
        }
        return phones;
    }

}
