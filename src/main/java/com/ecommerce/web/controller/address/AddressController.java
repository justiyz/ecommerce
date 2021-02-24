package com.ecommerce.web.controller.address;

import com.ecommerce.data.model.Address;
import com.ecommerce.service.address.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> saveAddress(@RequestBody Address address){
        addressService.saveAddress(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAddresses(){
        List<Address> addresses = addressService.findAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody Address address){
        addressService.saveAddress(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable Integer id){
        Address address = addressService.findAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);

    }


}
