package com.histories.homeland.service;

import com.histories.homeland.dao.AddressRepository;
import com.histories.homeland.dao.PersonRepository;
import com.histories.homeland.entity.Person;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    //Repository instance variables for Person and Address
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressRepository addressRepository){
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    /*
     * Service to find a person by their first name
     * */
    public Person findByFirstName(String firstName) {
        Person person;
        person = personRepository.findByFirstName(firstName);
        return person;
    }

    /*
     * Service to find a person by their last name
     * */
    public Person findByLastName(String lastName) {
        Person person;
        person = personRepository.findByLastName(lastName);
        return person;
    }

    /*
     * Service to find a person by their first and last name
     * */
    public Person findByFirstAndLastName(String firstName, String LastName) {
        List<Person> personList= personRepository.findByFirstAndLastName(firstName, LastName);
        Person person = new Person();

        Iterator<Person> itr = personList.iterator();

        //Should not be duplicate names, but if there are, return the first one.
        while(itr.hasNext()) {
            person = itr.next();

            break;
        }
        return person;
    }

    /*
     * Service to add a person and their address
     * */
    public void postPerson(Person personRequest) throws Exception{
        //Create a person instance with values passed in
        Person person = new Person(personRequest.getFirstName(), personRequest.getLastName(), personRequest.getAddress());

        //Question: should I be searching without regard to case??? Seems like case matters from biz requirements.
        List<Person> personList= personRepository.findByFirstAndLastName(personRequest.getFirstName(), personRequest.getLastName());

        if(personList.size() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists in database.", new Exception());
        }

        //Save Person to the database
        personRepository.save(person);
    }

    /*
     * Service to delete a person and their address
     * */
    public void deletePerson(String firstName, String LastName) {
        List<Person> personList= personRepository.findByFirstAndLastName(firstName, LastName);
        Iterator<Person> itr = personList.iterator();

        //Should not be duplicate names, but if there are, delete the first one and break.
        while(itr.hasNext()) {
            Person person = itr.next();
            Long personId = person.getId();
            personRepository.delete(person);
            break;
        }
    }

    /*
     * Service to update a person by their first name
     * */
    public void putPerson(Long id, Person personRequest) throws Exception{
        //Get person by Id
        Optional <Person> person = personRepository.findById(id);

        //If a person was not found, throw an exception
        if(!person.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist in database.", new Exception());
        }

        //Set person values if not null or blank
        if(!StringUtils.isEmpty(personRequest.getFirstName()))
            person.get().setFirstName(personRequest.getFirstName());
        if(!StringUtils.isEmpty(personRequest.getLastName()))
            person.get().setLastName(personRequest.getLastName());

        //Set address values if not null or blank
        if(personRequest.getAddress()!=null) {
            if(!StringUtils.isEmpty(personRequest.getAddress().getStreet()))
                person.get().getAddress().setStreet(personRequest.getAddress().getStreet());
            if(!StringUtils.isEmpty(personRequest.getAddress().getCity()))
                person.get().getAddress().setCity(personRequest.getAddress().getCity());
            if(!StringUtils.isEmpty(personRequest.getAddress().getState()))
                person.get().getAddress().setState(personRequest.getAddress().getState());
            if(!StringUtils.isEmpty(personRequest.getAddress().getZipCode()))
                person.get().getAddress().setZipCode(personRequest.getAddress().getZipCode());
            if(!StringUtils.isEmpty(personRequest.getAddress().getCountry()))
                person.get().getAddress().setCountry(personRequest.getAddress().getCountry());
        }

        //Save person to database
        personRepository.save(person.get());
    }

}
