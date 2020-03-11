package com.galvanize.repository;

import com.galvanize.entities.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    List<Person> people = new ArrayList<>();

    Long nextId = 10L;

    {
        people.add(new Person(nextId++, "Mark", "mark.hendrix@galvanize.com"));
        people.add(new Person(nextId++, "Mike", "mike.hudson@galvanize.com"));
        people.add(new Person(nextId++, "Michael", "michael.wing@galvanize.com", LocalDate.of(1962, 11, 16)));
    }

    public Person save(Person person){
        person.setId(nextId++);
        people.add(person);
        return  person;
    }

    public List<Person> findAll() {
        return people;
    }

    public Person findById(Long id) {
        for (Person p : people){
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Person updateEmail(Long id, String email) {
        Person p = findById(id);
        if(p != null){
            p.setEmail(email);
        }else{
            throw new RuntimeException("Person not found");
        }
        return p;
    }

    public boolean delete(Long id) {
        Person p = findById(id);
        if(p != null){
            people.remove(p);
            return true;
        }else{
            return false;
        }
    }

}
