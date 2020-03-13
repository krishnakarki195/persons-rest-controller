package com.galvanize.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.sql.Date;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonRestControllerTest {

    @Autowired
    MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void helloRegistration(){
        PersonRestController hrc = new PersonRestController();
        Person p = hrc.PersonRegistration("Krishna", Date.valueOf(LocalDate.of(1962, 11, 16)),"rob.wing@galvanize.com");
        assertEquals(p.getAge(), 57);
    }

//    //Method to create a date in the past for testing
//    private Date getTestDob(int years){
//        LocalDate ld = LocalDate.now();
//        ld.minusYears(1l);
//
//        Calendar ci = Calendar.getInstance();
//        ci.add(Calendar.YEAR, -years);
//        return ci.getTime();
//    }

    @Test
    void helloRegGetReturnsPerson1() throws Exception {
        String url = "/hello?name=Krishna&birthDate=11/16/1962&email=krishna.karki@xome.com";
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("krishna.karki@xome.com")));
                //.andExpect(jsonPath("$.id").value(57));
    }

    @Test
    void helloRegGetReturnsPerson2() throws Exception {
        String sPerson = mapper.writeValueAsString(new Person("Krishna", "krishna.karki@xome.com", Date.valueOf(LocalDate.of(1962, 11, 16))));
        mvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON).content(sPerson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("krishna.karki@xome.com")));
    }

    @Test
    void createPersonTest() throws Exception {
        String body = "{\"name\":\"Krishna\",\"email\":\"rob.wing@galvanize.com\",\"birthDate\":\"11/16/1962\"}";
        mvc.perform(post("/persons").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andDo(print());
    }

    @Test
    void getById() throws Exception {
        mvc.perform(patch("/persons/10?email=krishna.karki195@hotmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("krishna.karki195@hotmail.com"))
                .andDo(print());
    }

    @Test
    void testNewLocalDate() throws JsonProcessingException {
        Person person = new Person("Krishna", "krishna.karki@xome.com", Date.valueOf(LocalDate.of(1962, 11, 16)));
        //De-serialize the Person object into a Json String
        String sPerson = mapper.writeValueAsString(person);
        System.out.println(sPerson);

        //Serialize the String back to a Person object
        Person person1 = mapper.readValue(sPerson, Person.class);
        System.out.println(person1);

    }

    @Test
    void helloRegPostReturnsPerson() throws Exception {
        String json = "{\"name\":\"Krishna\",\"birthDate\":\"1962/11/16\",\"email\":\"krishna.karki@xome.com\"}";
        mvc.perform(post("/persons")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("krishna.karki@xome.com")))
                .andExpect(jsonPath("$.name").value("Krishna"));
    }

}
