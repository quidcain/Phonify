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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext.xml")
public class JdbcOrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void orderDaoShouldNotBeNull() {
        assertNotNull(orderDao);
    }

    @Test
    public void phoneDaoShouldNotBeNull() {
        assertNotNull(phoneDao);
    }

    @Before
    public void preparePhonesTable() {
        Phone phone = new Phone();
        phone.setModel("iPhone");
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        phoneDao.save(phone);
        phone = new Phone();
        phone.setModel("Samsung galaxy SIII");
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        phoneDao.save(phone);
    }

    @After
    public void clearPhonesTable() {
        for(Phone phone : phoneDao.findAll())
            phoneDao.delete(phone.getId());
    }

    @Test
    public void saveAndGetTest() {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setSubtotal(BigDecimal.ONE);
        order.setDeliveryPrice(BigDecimal.ONE);
        order.setFirstName("John");
        order.setLastName("Doe");
        order.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        order.setContactPhoneNo("1-800-354-0387");
        order.setStatus(Order.Status.AWAITING);
        addAllPhones(order);
        orderDao.save(order);
        assertEquals(order, orderDao.get(order.getId()));
        orderDao.delete(order.getId());
    }

    @Test
    public void findAllTest() {
        List<Order> list = new ArrayList<>(3);
        addOrderToListByName(list, "John", "Doe");
        addOrderToListByName(list, "Mary", "Lee");
        addOrderToListByName(list, "Irving", "Blake");
        for(Order order : list) {
            addAllPhones(order);
            orderDao.save(order);
        }
        List<Order> foundList = orderDao.findAll();
        assertEquals(list.size(), foundList.size());
        for(Order order : list)
            orderDao.delete(order.getId());
        assertEquals(0, orderDao.findAll().size());
    }

    private void addOrderToListByName(List<Order> list, String firstName, String lastName) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setSubtotal(BigDecimal.ONE);
        order.setDeliveryPrice(BigDecimal.ONE);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        order.setContactPhoneNo("1-800-354-0387");
        order.setAdditionalInfo("Lorem ipsum");
        order.setStatus(Order.Status.AWAITING);
        list.add(order);
    }

    private void addAllPhones(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        for(Phone phone : phoneDao.findAll()) {
            OrderItem item = new OrderItem();
            item.setPhone(phoneDao.get(phone.getId()));
            item.setOrder(order);
            item.setQuantity(1);
            orderItems.add(item);
        }
    }
}
