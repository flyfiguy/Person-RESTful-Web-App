package com.histories.homeland.dao;

import com.histories.homeland.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstName(@RequestParam("firstName") String firstName);

    Person findByLastName(@RequestParam("lastName") String lastName);

    //@Query("select * from Person p where p.firstName=:firstName and p.lastName=:lastName limit 1")
    //Person findByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName);
}
