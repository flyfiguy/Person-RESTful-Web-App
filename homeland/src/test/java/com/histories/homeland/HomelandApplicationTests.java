package com.histories.homeland;

import com.histories.homeland.dao.AddressRepository;
import com.histories.homeland.dao.PersonRepository;
import com.histories.homeland.entity.Address;
import com.histories.homeland.entity.Person;
import com.histories.homeland.service.PersonService;
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
	public void testAddDelete() throws Exception {
		final String fname = "abcdefghijklmnopqrst";
		final String lname = "tsrqponmlkjihgfedcba";

		//Create a test person & address and post...do not have the persons id at this point since it is generated
		Person person = new Person(fname, lname/*, new Address("789 South 1023 East", "Ogden", "Utah", "84401", "United States of America")*/);
		personService.postPerson(person);

		//Find the person, add the person id to the foreign key in the address table. Save.
		person = personService.findByFirstAndLastName(fname,lname);
		//person.getAddress().setPersonId(person.getId());
		personRepository.save(person);

		//Verify the test person was added
		person = personService.findByFirstAndLastName(fname, lname);
		assertThat("Test record was not added to the database", person.getId()!=null);

		//Delete test person
		personService.deletePerson(fname, lname);

		//Verify test person was deleted
		person = personService.findByFirstAndLastName(fname, lname);
		assertThat("Test record was not deleted successfully", person.getId()==null);
	}

	@Test
	void contextLoads() {
	}

}
