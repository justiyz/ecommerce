package com.ecommerce.service.address;

import com.ecommerce.data.exceptions.AddressException;
import com.ecommerce.data.model.Address;
import com.ecommerce.data.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService = new AddressServiceImpl();

    Address address;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = new Address();
    }

    @Test
    void mockTheSaveAddressServiceMethod() throws AddressException {
        when(addressRepository.save(address)).thenReturn(address);
        addressService.saveAddress(address);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void mockTheFindAddressByIdMethod(){
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        addressService.findAddressById(1);
        verify(addressRepository, times(1));
    }

    @Test
    void mockTheUpdateAddressMethod(){
        when(addressRepository.save(address)).thenReturn(address);
        addressService.updateAddress(address);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void mockTheDeleteAddressByIdMethod(){
        doNothing().when(addressRepository).deleteById(1);
        addressService.deleteAddressById(1);
        verify(addressRepository, times(1)).deleteById(1);
    }

    @Test
    void mockTheFindAllAddressesMethod(){
        when(addressRepository.findAll()).thenReturn(List.of(address));
        addressService.findAllAddresses();
        verify(addressRepository, times(1));
    }























}