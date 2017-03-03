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
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext.xml")
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
        //Phone actualPhone = phoneDao.get(phone.getId());
        Phone actualPhone = null;
        List<Phone> list = phoneDao.findAll();
        for(Phone phone : list)
            if (phone.equals(originalPhone))
                actualPhone = phone;
        assertEquals(actualPhone, phoneDao.get(actualPhone.getId()));
        phoneDao.delete(actualPhone.getId());
        phoneDao.get(actualPhone.getId());
    }

    @Test
    public void findAllTest() {
        List<Phone> expectedList = Arrays.asList(
            new Phone("iPhone", "black",
                4, BigDecimal.valueOf(800)),
            new Phone("Nokia", "white",
                1, BigDecimal.valueOf(75)),
            new Phone("Motorolla", "vinous",
                2, BigDecimal.valueOf(150))
        );
        for(Phone phone : expectedList)
            phoneDao.save(phone);
        List<Phone> actualList = phoneDao.findAll();
        assertEquals(expectedList, actualList);
        for(Phone phone : actualList)
            phoneDao.delete(phone.getId());
    }

}
