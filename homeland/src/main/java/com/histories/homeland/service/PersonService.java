package com.histories.homeland.service;

import com.histories.homeland.dao.AddressRepository;
import com.histories.homeland.dao.PersonRepository;
import com.histories.homeland.entity.Address;
import com.histories.homeland.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonService {

    //Repository instance variables for Person and Address
    private PersonRepository personRepository;
    private AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressRepository addressRepository){
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }


    public Person findByFirstName(String firstName) {
        Person person;
        person = personRepository.findByFirstName(firstName);
        return person;
    }


    public Person findByLastName(String lastName) {
        Person person;
        person = personRepository.findByLastName(lastName);
        return person;
    }

    public void postPerson(Person messageRequest) {
        Person person = new Person(messageRequest.getFirstName(), messageRequest.getLastName());
        personRepository.save(person);
        Long personId = person.getId();

        //Stub in address until I get that part done...
                                            //Long personId, String street, String city, String state, String zipCode, String country
        Address address = new Address(personId, "112 N 223 E", "Orem", "Utah", "84057", "United States of America");
        addressRepository.save(address);
        //personRepository.save(person);
    }


    //   public Person findByFirstAndLastName(String firstName, String LastName) {
 //       Optional<Person> person personRepository.findByFirstAndLastName(firstName, LastName);
//    }



}
