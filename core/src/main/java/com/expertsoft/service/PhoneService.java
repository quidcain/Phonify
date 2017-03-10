package com.expertsoft.service;

import com.expertsoft.model.Phone;

import java.util.List;

public interface PhoneService {
    Phone get(long id);
    void save(Phone phone);
    void delete(long id);
    List<Phone> findAll();
}
