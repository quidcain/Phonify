package com.expertsoft.dao;

import com.expertsoft.model.Phone;

import java.util.List;

public interface PhoneDao {
    Phone get(Long id);
    void save(Phone phone);
    void delete(Long id);
    List<Phone> findAll();
}
