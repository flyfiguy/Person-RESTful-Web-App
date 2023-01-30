package com.histories.homeland.controller;

import com.histories.homeland.entity.Person;
import com.histories.homeland.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //Controller for rest API
@RequestMapping("/api") //To match the other data rest API's
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/firstName")
    public Person findByFirstName(@RequestParam String firstName) {
        return personService.findByFirstName(firstName);
    }
    @GetMapping("/person/lastName")
    public Person findByLastName(@RequestParam String lastName) {
        return personService.findByLastName(lastName);
    }

    @PostMapping("/add/person")
    public void postPerson(@RequestBody Person messageRequest) {
        personService.postPerson(messageRequest);
    }

}
