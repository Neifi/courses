package com.example.courses.domain.vo;

import com.example.courses.domain.exceptions.InvalidEmailException;

public interface DomainEmailValidator {
    void validate(String email) throws InvalidEmailException;
}
