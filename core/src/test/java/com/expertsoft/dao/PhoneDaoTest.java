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
        Phone phone = new Phone(8_432_142,
            "iPhone", "black", 4, BigDecimal.valueOf(800));
        phoneDao.save(phone);
        assertEquals(phone, phoneDao.get(8_432_142L));

        phoneDao.delete(8_432_142L);
        phoneDao.get(8_432_142L);
    }

    @Test
    public void findAllTest() {
        List<Phone> list = Arrays.asList(
            new Phone(8_432_142, "iPhone", "black",
                4, BigDecimal.valueOf(800)),
            new Phone(8_432_143, "Nokia", "white",
                1, BigDecimal.valueOf(75)),
            new Phone(8_432_144, "Motorolla", "vinous",
                2, BigDecimal.valueOf(150))
        );
        for(Phone phone : list)
            phoneDao.save(phone);

        assertEquals(list, phoneDao.findAll());

        for(Phone phone : list)
            phoneDao.delete(phone.getId());
    }

}
