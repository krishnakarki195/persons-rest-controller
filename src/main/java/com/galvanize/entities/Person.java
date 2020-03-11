package com.galvanize.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    public Person() {
    }

    public Person(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(Long id, String name, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getAge(){
        if (this.birthDate != null) {
            return Period.between(this.birthDate, LocalDate.now()).getYears();
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
