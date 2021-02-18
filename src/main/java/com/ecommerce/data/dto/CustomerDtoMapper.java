package com.ecommerce.data.dto;

import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomerDtoMapper {

    @Autowired
    CustomerDto customerDto;

    Customer customer;

    public CustomerDtoMapper(){
        customer = new Customer();
    }

    public CustomerDto setCustomerToCustomerDto(Customer customer){
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setContact(customer.getContact());
        return customerDto;
    }

    public List<CustomerDto> setCustomerToCustomerDto(List<Customer> customers){
        log.info("customers --> {}", customers);

        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer: customers){
            log.info("customers --> {}", setCustomerToCustomerDto(customer));
        }
        return customerDtos;
    }

    public Customer setCustomerDtoToCustomer(CustomerDto customerDto){
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setContact(customerDto.getContact());
        customer.setContact(customerDto.getContact());

        return customer;
    }





}
