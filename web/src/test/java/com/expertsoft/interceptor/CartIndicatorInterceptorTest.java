package com.expertsoft.interceptor;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"/dispatcher-servlet.xml", "/applicationContext.xml"})
public class CartIndicatorInterceptorTest {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void testInterceptor() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/");
        request.setMethod("GET");
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(request);
        HandlerInterceptor[] interceptors = handlerExecutionChain.getInterceptors();
        for(HandlerInterceptor interceptor : interceptors){
            interceptor.preHandle(request, response, handlerExecutionChain.getHandler());
        }
        assertNotNull(request.getAttribute("itemsQuantity"));
        assertNotNull(request.getAttribute("subtotal"));
        ModelAndView mav = handlerAdapter.handle(request, response, handlerExecutionChain.getHandler());
        for(HandlerInterceptor interceptor : interceptors){
            interceptor.postHandle(request, response, handlerExecutionChain.getHandler(), mav);
        }
        assertEquals(200, response.getStatus());
    }

}
