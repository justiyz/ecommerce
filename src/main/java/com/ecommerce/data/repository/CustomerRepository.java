package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.CustomerException;
import com.ecommerce.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public default Customer saveCustomer (Customer customer) throws CustomerException {
       if (isValid(customer)){
           save(customer);
       }
        return customer;
    }

    private boolean isValid(Customer customer) throws CustomerException {
        if (!customerHasEmail(customer)) {
            throw new CustomerException("Email address can not be null");
        } if (!customerHasPassword(customer)) {
            throw new CustomerException(("Password is required"));
        } if (!customerHasFullName(customer)) {
            throw new CustomerException("Full names required");
        }
        return true;
    }

    private boolean customerHasEmail (Customer customer) {
        return customer.getEmail() != null;
    }

    private boolean customerHasPassword (Customer customer) {
        return customer.getPassword() != null;
    }

    private boolean customerHasFullName (Customer customer) {
        return customer.getFirstName() != null && customer.getLastName() != null;
    }

}
