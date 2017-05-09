package com.expertsoft.controller;


import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoginControllerTest {


    @Test
    public void testProductListPage() throws Exception {
        LoginController controller = new LoginController();
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/login.jsp"))
                .build();
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
        mockMvc.perform(get("/login")
                .param("error", ""))
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
        mockMvc.perform(get("/login")
                .param("logout", ""))
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("logout"));
    }


}
