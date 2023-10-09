package com.example.courses.domain;

import com.example.courses.domain.vo.ModuleContent;
import com.example.courses.domain.vo.ModuleContentBuilder;
import com.example.courses.domain.exceptions.InvalidMultimediaURLException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ModuleContentTest {

    @Test
    public void givenTextContent_WhenCreatingModuleContent_ItShouldBeCreated() {
        String someText = "some text";
        ModuleContent moduleContent = ModuleContent.Builder().withText(someText).build();

        assertEquals(someText, moduleContent.text());
    }

    @Test
    public void givenMultimediaContent_WhenCreatingModuleContent_ItShouldBeCreated() {
        String someText = "some text";
        String url = "https://example.com/1234.mp4";
        ModuleContent moduleContent = ModuleContent.Builder()
                .withText(someText)
                .withMultimediaUrl(url)
                .build();

        assertEquals(someText, moduleContent.text());
        assertEquals(url, moduleContent.url());
    }

    @ParameterizedTest
    @ValueSource(strings = {"https//example",""})
    public void givenInvalidMultimediaContent_WhenCreatingModuleContent_ExceptionShouldBeThrown(String url) {
        String someText = "some text";
        ModuleContentBuilder moduleContentBuilder = ModuleContent.Builder()
                .withText(someText)
                .withMultimediaUrl(url);

        assertThrows(InvalidMultimediaURLException.class, moduleContentBuilder::build);

    }
}