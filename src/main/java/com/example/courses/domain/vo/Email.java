package com.example.courses.domain.vo;

import java.util.Objects;

public record Email(String value) {
    public Email {
        DomainEmailValidator emailValidator = new ApacheDomainEmailValidator();
        emailValidator.validate(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;

        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
