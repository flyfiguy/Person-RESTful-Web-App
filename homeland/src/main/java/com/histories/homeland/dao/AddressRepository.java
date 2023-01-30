package com.histories.homeland.dao;

import com.histories.homeland.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findByPersonId(@RequestParam("person_id") Long personId, Pageable pageable);
}
