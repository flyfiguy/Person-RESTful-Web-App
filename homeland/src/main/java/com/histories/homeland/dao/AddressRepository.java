package com.histories.homeland.dao;

import com.histories.homeland.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
