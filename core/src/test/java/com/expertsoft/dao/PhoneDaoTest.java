package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/coreContext.xml")
@Transactional
public class PhoneDaoTest {

    @Autowired
    private PhoneDao phoneDao;

    @Test
    public void phoneDaoShouldNotBeNull() {
        assertNotNull(phoneDao);
    }

    @Test
    public void saveAndGetTest() {
        Phone originalPhone = new Phone();
        originalPhone.setModel("iPhone");
        originalPhone.setColor("black");
        originalPhone.setDisplaySize(4);
        originalPhone.setPrice(BigDecimal.valueOf(800));
        phoneDao.save(originalPhone);
        Phone phoneFromDb = phoneDao.get(originalPhone.getId());
        assertEquals(originalPhone, phoneFromDb);
    }

    @Test
    public void findAllTest() {
        List<Phone> list = new ArrayList<>(3);
        addPhoneToListByModel(list, "iPhone");
        addPhoneToListByModel(list, "Nokia");
        addPhoneToListByModel(list, "Motorolla");
        for(Phone phone : list)
            phoneDao.save(phone);
        assertEquals(list.size(), phoneDao.findAll().size());
        for(Phone phone : list)
            phoneDao.delete(phone.getId());
        assertEquals(0, phoneDao.findAll().size());
    }

    private void addPhoneToListByModel(List<Phone> list, String phoneModel) {
        Phone phone = new Phone();
        phone.setModel(phoneModel);
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.valueOf(800));
        list.add(phone);
    }

}
