package com.example.courses.domain.entities;

import com.example.courses.domain.vo.Email;

import java.util.Objects;

public class Creator {
    Email email;

    private Creator() {

    }

    private Creator(Email email) {
        this.email = email;
    }

    public static Creator with(Email email) {
        return new Creator(email);
    }

    public Email email() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creator creator)) return false;

        return Objects.equals(email, creator.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
