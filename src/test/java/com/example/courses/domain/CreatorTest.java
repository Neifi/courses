package com.example.courses.domain;

import com.example.courses.domain.entities.Creator;
import com.example.courses.domain.vo.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {

    @Test
    public void givenEmail_WhenCreateCreator_ThenItShouldBeCreated(){
        String value = "email@email.com";
        Creator creator = Creator.with(new Email(value));

        assertEquals(value,creator.email().value());
    }
}