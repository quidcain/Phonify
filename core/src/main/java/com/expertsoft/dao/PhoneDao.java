package com.expertsoft.dao;

import com.expertsoft.model.Phone;

import java.util.List;

public interface PhoneDao {
    Phone get(long id);
    void save(Phone phone);
    void delete(long id);
    List<Phone> findAll();
}
