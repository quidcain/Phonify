package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext.xml")
@ActiveProfiles("test")
public class PhoneDaoTest {

    @Autowired
    private PhoneDao phoneDao;

    private void addPhoneToList(List<Phone> list, String phoneModel) {
        Phone phone = new Phone();
        phone.setModel(phoneModel);
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.valueOf(800));
        list.add(phone);
    }

    @Test
    public void phoneDaoShouldNotBeNull() {
        assertNotNull(phoneDao);
    }

    @After
    // TODO: remove, use transactional integration test
    public void resetPhonesTable() {
        for (Phone phone : phoneDao.findAll())
            phoneDao.delete(phone.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void saveAndGetTest() {
        Phone originalPhone = new Phone();
        originalPhone.setModel("iPhone");
        originalPhone.setColor("black");
        originalPhone.setDisplaySize(4);
        originalPhone.setPrice(BigDecimal.valueOf(800));
        phoneDao.save(originalPhone);
        List<Phone> list = phoneDao.findAll();
        assertEquals(1, list.size());
        for(Phone phone : phoneDao.findAll()) {
            assertEquals(originalPhone.getModel(), phoneDao.get(phone.getId()).getModel());
            phoneDao.delete(phone.getId());
            phoneDao.get(phone.getId());
        }
    }

    @Test
    public void findAllTest() {
        List<Phone> list = new ArrayList<>(3);
        addPhoneToList(list, "iPhone");
        addPhoneToList(list, "Nokia");
        addPhoneToList(list, "Motorolla");
        for(Phone phone : list)
            phoneDao.save(phone);
        List<Phone> foundList = phoneDao.findAll();
        assertEquals(list.size(), foundList.size());
    }
}
