package com.histories.homeland;

import com.histories.homeland.dao.AddressRepository;
import com.histories.homeland.dao.PersonRepository;
import com.histories.homeland.entity.Address;
import com.histories.homeland.entity.Person;
import com.histories.homeland.service.PersonService;
import io.micrometer.common.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class HomelandApplicationTests {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Autowired
	private AddressRepository addressRepository;

	@Test
	@Transactional
	public void testCRUDServices() throws Exception {
		final String fname = "abcdefghijklmnopqrst";
		final String lname = "tsrqponmlkjihgfedcba";
		final String fnameTest = "John";
		final String cityTest = "Moab";

		//POST, GET: Create a test person & address, POST the person, GET the person by first and last name, verify person.
		Person person = new Person(fname, lname, new Address("789 South 1023 East", "Ogden", "Utah", "84401", "United States of America"));
		personService.postPerson(person);
		Person addedPerson = new Person();
		addedPerson = personService.findByFirstAndLastName(fname,lname);
		assertThat("GET person by first and last name service failed",  !StringUtils.isEmpty(addedPerson.getFirstName()) && !StringUtils.isEmpty(addedPerson.getLastName()));
		assertThat("POST person service failed.",  addedPerson.getId()!=null && addedPerson.getFirstName().equals(fname) && addedPerson.getLastName().equals(lname));

		//GET: Test get by first name
		addedPerson = personService.findByFirstName(fname); // This also tests the GET method
		assertThat("GET person by first name service failed.",  addedPerson.getId()!=null && addedPerson.getFirstName().equals(fname));

		//GET: Test get by last name
		addedPerson = personService.findByLastName(lname); // This also tests the GET method
		assertThat("GET person by last name service failed.",  addedPerson.getId()!=null && addedPerson.getLastName().equals(lname));

		//PUT, GET: Change some information to test put
		addedPerson.setFirstName(fnameTest);
		addedPerson.getAddress().setCity(cityTest);
		personService.putPerson(addedPerson.getId(),addedPerson);
		Person changedPerson = personService.findByFirstAndLastName(addedPerson.getFirstName(),addedPerson.getLastName());
		assertThat("Put person service failed on first name.", changedPerson.getFirstName().equals(fnameTest));
		assertThat("Put person service failed on city.", changedPerson.getAddress().getCity().equals(cityTest));

		//DELETE: Delete test person and verify
		personService.deletePerson(changedPerson.getFirstName(), changedPerson.getLastName());
		Person deletedPerson = personService.findByFirstAndLastName(fname, lname);
		assertThat("Test record was not deleted successfully", deletedPerson.getId()==null);
	}

}
