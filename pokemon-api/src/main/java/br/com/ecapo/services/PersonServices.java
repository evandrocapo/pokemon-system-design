package br.com.ecapo.services;

import br.com.ecapo.exception.ResourceNotFoundException;
import br.com.ecapo.model.Person;
import br.com.ecapo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Find All Persons");
        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Person created %S" + person.toString());
        return repository.save(person);
    }

    public Person findById(Long id) {
        logger.info("Finding person by id " + id);

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
    }

    public Person update(Long id, Person newPerson){
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));

        person.setFirstName(newPerson.getFirstName());
        person.setLastName(newPerson.getLastName());
        person.setGender(newPerson.getGender());
        person.setAddress(newPerson.getAddress());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting person by id " + id);

        repository.deleteById(id);
    }

    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirtName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Address " + i);
        person.setGender("Gender " + i);

        return person;
    }
}
