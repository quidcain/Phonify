package com.expertsoft.service;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
    private PhoneDao phoneDao;

    @Autowired
    public PhoneServiceImpl(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public Phone get(long id) {
        return phoneDao.get(id);
    }

    @Override
    public void save(Phone phone) {
        phoneDao.save(phone);
    }

    @Override
    public void delete(long id) {
        phoneDao.delete(id);
    }

    @Override
    public List<Phone> findAll() {
        return phoneDao.findAll();
    }
}
