package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
