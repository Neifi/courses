package com.example.courses.domain.entities;

import com.example.courses.domain.vo.Email;

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
}
