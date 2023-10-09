package com.example.courses.domain.vo;

import com.example.courses.domain.exceptions.InvalidMultimediaURLException;

import java.net.MalformedURLException;
import java.net.URL;

 public class ModuleContent {
    private final String text;
    private final String multimediaURL;

    public ModuleContent(String text, String multimediaURL) {
        if (multimediaURL != null) {
            try {
                new URL(multimediaURL);
            } catch (MalformedURLException e) {
                throw new InvalidMultimediaURLException();
            }
        }
        this.text = text;
        this.multimediaURL = multimediaURL;
    }

    public static ModuleContentBuilder Builder() {
        return new ModuleContentBuilder();
    }

    public String text() {
        return this.text;
    }

    public String url() {
        return this.multimediaURL;
    }
}

