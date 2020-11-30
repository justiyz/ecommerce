package com.ecommerce.service.customer;

import com.ecommerce.data.model.Customer;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer (Customer customer);
    public Customer findByCustomerId (Integer id);
    public List<Customer> findAllCustomer ();
    public void deleteCustomerById (Integer id);
    public Customer updateCustomer(Customer customer);

}
