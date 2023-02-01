package com.histories.homeland.controller;

import com.histories.homeland.entity.Person;
import com.histories.homeland.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //Controller for rest API
@RequestMapping("/api") //To match the other data rest API's
public class PersonController {
    private PersonService personService;

    /*
    * Constructor
    * */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /*
     * GET - Find a person using their first name
     * */
    @GetMapping(path="/person/firstName", produces = "application/json")
    public Person findByFirstName(@RequestParam String firstName) {
        return personService.findByFirstName(firstName);
    }

    /*
     * GET - Find a person using their last name
     * */
    @GetMapping(path="/person/lastName", produces = "application/json")
    public Person findByLastName(@RequestParam String lastName) {
        return personService.findByLastName(lastName);
    }

    /*
     * GET - Find a person using both first and last name
     * */
    @GetMapping(path="/person/fullName", produces = "application/json")
    public Person findByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return personService.findByFirstAndLastName(firstName,lastName);
    }

    /*
     * POST - Add a person and their address
     * */
    @PostMapping(path="/add/person", consumes = "application/json", produces = "application/json")
    public void postPerson(@RequestBody Person personRequest) throws Exception{
        personService.postPerson(personRequest);
    }

    /*
     * DELETE - Delete a person and their address
     * */
    @DeleteMapping(path="/delete/person", produces = "application/json")
    public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        personService.deletePerson(firstName, lastName);
    }

    /*
     * PUT - Update a person and their address
     * */
    @PutMapping(path="/update/person/{id}", consumes = "application/json", produces = "application/json")
    public void putPerson(@PathVariable(value="id") Long id, @RequestBody Person personRequest) throws Exception {
        personService.putPerson(id, personRequest);
    }
}
