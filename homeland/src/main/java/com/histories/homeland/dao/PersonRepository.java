package com.histories.homeland.dao;

import com.histories.homeland.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /*
     * Find a person based on last name.
     * Note: Look into adding pagination
     * */
    Person findByFirstName(@RequestParam("firstName") String firstName);

    /*
    * Find a person based on first and last name.
    * Note: Look into adding pagination
     * */
    Person findByLastName(@RequestParam("lastName") String lastName);


    /*
     * Find a person based on first and last name.
     * This will return all matches of first name and last name.
     * */
    @Transactional
    @Modifying
    @Query("select p from Person p where p.firstName=:firstName and p.lastName=:lastName")
    List<Person> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
