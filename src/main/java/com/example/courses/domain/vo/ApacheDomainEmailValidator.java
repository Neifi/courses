package com.example.courses.domain.vo;

import com.example.courses.domain.exceptions.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class ApacheDomainEmailValidator implements DomainEmailValidator {
    @Override
    public void validate(String email) throws InvalidEmailException {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidEmailException();
        }
    }
}
