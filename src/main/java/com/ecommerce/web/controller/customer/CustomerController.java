package com.ecommerce.web.controller.customer;

import com.ecommerce.data.model.Customer;
import com.ecommerce.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer (@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable Integer id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
//        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByCustomerId(@PathVariable Integer id){
        Customer customer = customerService.findByCustomerId(1);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
