package com.expertsoft.dao;

import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext-test.xml")
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void orderDaoShouldNotBeNull() {
        assertNotNull(orderDao);
    }

    @Before
    public void preparePhonesTable() {
        Phone phone = new Phone();
        phone.setModel("iPhone");
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        phoneDao.save(phone);
    }

    @After
    public void resetTable() {
        for (Order order : orderDao.findAll())
            orderDao.delete(order.getId());
        for (Phone phone : phoneDao.findAll())
            phoneDao.delete(phone.getId());
    }


    @Test
    public void saveAndGetTest() {
        Order order = new Order(new ArrayList<>(), BigDecimal.ONE, BigDecimal.ONE,
                "John", "Doe",
                "1234 Main Street Anytown, USA 123456",
                "1-800-354-0387");
        for (Phone phone : phoneDao.findAll())
            order.getOrderItems().add(new OrderItem(phoneDao.get(phone.getId()),
                    order, 1));
        orderDao.save(order);
        List<Order> list = orderDao.findAll();
        assertEquals(1, list.size());
        assertEquals(order.getSubtotal(), orderDao.get(0L).getSubtotal());
        orderDao.delete(0L);
        assertEquals(null, orderDao.get(0L));
    }

    @Test
    public void findAllTest() {
        List<Order> list = Arrays.asList(
                new Order(new ArrayList<>(), BigDecimal.ONE, BigDecimal.ONE,
                        "John", "Doe",
                        "1234 Main Street Anytown, USA 123456",
                        "1-800-354-0387"),
                new Order(new ArrayList<>(), BigDecimal.ONE, BigDecimal.ONE,
                        "Mary", "Lee",
                        "1234 Main Street Anytown, USA 123456",
                        "1-800-354-0387"),
                new Order(new ArrayList<>(), BigDecimal.ONE, BigDecimal.ONE,
                        "Irving", "Blake",
                        "1234 Main Street Anytown, USA 123456",
                        "1-800-354-0387")
        );
        for(Order order : list) {
            for(Phone phone : phoneDao.findAll())
                order.getOrderItems().add(new OrderItem(phoneDao.get(phone.getId()),
                        order, 1));
            orderDao.save(order);
        }
        List<Order> foundList = orderDao.findAll();
        assertEquals(list.size(), foundList.size());
    }
}
