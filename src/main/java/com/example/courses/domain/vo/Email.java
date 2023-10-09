package com.example.courses.domain.vo;

public record Email(String value) {
    public Email {
        DomainEmailValidator emailValidator = new ApacheDomainEmailValidator();
        emailValidator.validate(value);
    }
}
