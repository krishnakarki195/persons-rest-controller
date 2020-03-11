package com.galvanize.controllers;

import com.galvanize.entities.Person;
import com.galvanize.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class PersonRestController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping("/persons/{pid}")
    public Person getPerson(@PathVariable Long pid){
        return personRepository.findById(pid);
    }

    @PatchMapping("/persons/{pid}")
    public Person updatePerson(@RequestParam String email, @PathVariable Long pid){
        return personRepository.updateEmail(pid, email);
    }

    @DeleteMapping("/persons/{pid}")
    public ResponseEntity deletePerson(@PathVariable Long pid){
        boolean deleted = personRepository.delete(pid);
        if(!deleted){
            return ResponseEntity.notFound()
                    .header("errorMsg", "Person id "+pid+" doesn't exist")
                    .build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

}
