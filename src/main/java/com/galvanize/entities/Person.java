package com.galvanize.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Person {

    private Long id;

    private String name;

    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    Date birthDate;

    public Person() {
    }

    public Person(String name, Date dob, String email) {
        this.name = name;
        this.birthDate = dob;
        this.email = email;
    }

    public Person(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(Long id, String name, String email, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Person(String name, String email, java.sql.Date birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    // This is required because when the string has the age field, Jackson won't know what to do with it.  Adding the empty set method fakes it out.
    @JsonIgnore
    public void setAge(int age){
        //Method won't be called by the Jackson parser
    }

    public int getAge() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.birthDate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        LocalDate ll = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(ll, now);
        return diff.getYears();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
