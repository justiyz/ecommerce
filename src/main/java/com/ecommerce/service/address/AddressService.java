package com.ecommerce.service.address;

import com.ecommerce.data.model.Address;

import java.util.List;

public interface AddressService {


    public Address saveAddress(Address address) ;

    public Address findAddressById(Integer id);

    public void deleteAddressById(Integer id);

    public List<Address> findAllAddresses();

    public Address updateAddress(Address address) ;
}
