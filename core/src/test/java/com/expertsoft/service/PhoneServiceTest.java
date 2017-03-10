package com.expertsoft.service;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Phone;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class PhoneServiceTest {
    private PhoneService phoneService;
    private PhoneDaoSpy phoneDaoSpy;
    private Phone phone;

    @Before
    public void init() {
        phone = new Phone();
        phoneDaoSpy = new PhoneDaoSpy();
        phoneService = new PhoneServiceImpl(phoneDaoSpy);
    }

    @Test
    public void findAllTest() {
        phoneService.findAll();
        assertEquals(1, phoneDaoSpy.getFindAllCallCount());
    }

    @Test
    public void getTest() {
        phoneService.get(0);
        assertEquals(1, phoneDaoSpy.getGetCallCount());
    }

    @Test
    public void saveTest() {
        phoneService.save(phone);
        assertEquals(1, phoneDaoSpy.getSaveCallCount());
    }

    @Test
    public void deleteTest() {
        phoneService.delete(0);
        assertEquals(1, phoneDaoSpy.getDeleteCallCount());
    }

    private class PhoneDaoSpy implements PhoneDao {
        private int getCallCount;
        private int saveCallCount;
        private int deleteCallCount;
        private int findAllCallCount;

        public int getGetCallCount() {
            return getCallCount;
        }

        public int getSaveCallCount() {
            return saveCallCount;
        }

        public int getDeleteCallCount() {
            return deleteCallCount;
        }

        public int getFindAllCallCount() {
            return findAllCallCount;
        }

        @Override
        public Phone get(long id) {
            getCallCount++;
            return null;
        }

        @Override
        public void save(Phone phone) {
            saveCallCount++;
        }

        @Override
        public void delete(long id) {
            deleteCallCount++;
        }

        @Override
        public List<Phone> findAll() {
            findAllCallCount++;
            return null;
        }
    }
}
