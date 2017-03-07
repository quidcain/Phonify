package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext-test.xml")
public class PhoneDaoTest {

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void phoneDaoShouldNotBeNull() {
        assertNotNull(phoneDao);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void saveAndGetTest() {
        Phone originalPhone = new Phone("iPhone", "black", 4, BigDecimal.valueOf(800));
        phoneDao.save(originalPhone);
        List<Phone> list = phoneDao.findAll();
        assertEquals(1, list.size());
        assertEquals(originalPhone.getModel(), phoneDao.get(0L).getModel());
        phoneDao.delete(0L);
        phoneDao.get(0L);
    }

    @Test
    public void findAllTest() {
        List<Phone> list = Arrays.asList(
            new Phone("iPhone", "black",
                4, BigDecimal.valueOf(800)),
            new Phone("Nokia", "white",
                1, BigDecimal.valueOf(75)),
            new Phone("Motorolla", "vinous",
                2, BigDecimal.valueOf(150))
        );
        for(Phone phone : list)
            phoneDao.save(phone);
        List<Phone> foundList = phoneDao.findAll();
        assertEquals(list.size(), foundList.size());
    }

}
