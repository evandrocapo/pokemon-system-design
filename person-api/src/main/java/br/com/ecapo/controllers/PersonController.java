package br.com.ecapo.controllers;

import br.com.ecapo.services.PersonServices;
import br.com.ecapo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping( //mais novo
            produces= MediaType.APPLICATION_JSON_VALUE
    )
    public List<Person> findAll() {
        return service.findAll();
    }

    @RequestMapping( // para legado
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody() Person person) {
        return service.create(person);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE
    )
    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT, produces =
            MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@PathVariable("id") Long id, @RequestBody() Person newPerson) {
        return service.update(id, newPerson);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
