package com.expertsoft.service;

import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceTest {

    @Mock
    private PhoneDao phoneDao;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Test
    public void findAllTest() {
        List <Phone> list = new ArrayList<>();
        when(phoneDao.findAll()).thenReturn(list);
        phoneService.findAll();
        verify(phoneDao, times(1)).findAll();
    }

    @Test
    public void getTest() {
        Phone phone = new Phone();
        when(phoneDao.get(anyLong())).thenReturn(phone);
        phoneService.get(0);
        verify(phoneDao, times(1)).get(anyLong());
    }

    @Test
    public void saveTest() {
        doNothing().when(phoneDao).save(any(Phone.class));
        phoneService.save(new Phone());
        verify(phoneDao, times(1)).save(any(Phone.class));
    }

    @Test
    public void deleteTest() {
        doNothing().when(phoneDao).delete(anyLong());
        phoneService.delete(0);
        verify(phoneDao, times(1)).delete(anyLong());
    }

}
