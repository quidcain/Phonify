package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface PhoneDao {
    @Cacheable("phones")
    Phone get(long id);
    @CachePut(value = "phones")
    void save(Phone phone);
    @CacheEvict(value = "phones", allEntries = true)
    void delete(long id);
    @Cacheable(value = "phones", unless = "#result.size() == 0")
    List<Phone> findAll();
}
