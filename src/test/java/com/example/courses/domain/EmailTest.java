package com.example.courses.domain;

import com.example.courses.domain.exceptions.InvalidEmailException;
import com.example.courses.domain.vo.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "email",
            "",
            "email@mail",
            "email@mail..com",
            "email.com.@mail"})
    public void givenEmail_WhenIsInvalid_ThenExceptionShouldBeThrown(String value) {

        assertThrows(InvalidEmailException.class, () -> {
            new Email(value);
        });
    }

    @Test
    public void givenEmail_WhenIsValid_ThenEmailShouldBeCreated() {

        String value = "email@mail.com";
        Email email = new Email(value);

        assertEquals(value, email.value());
    }
}