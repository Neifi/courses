package com.example.courses.domain.vo;

public class ModuleContentBuilder {
    private String text;
    private String url;

    public ModuleContentBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ModuleContentBuilder withMultimediaUrl(String url) {
        this.url = url;
        return this;
    }

    public ModuleContent build() {
        return new ModuleContent(this.text, this.url);
    }
}
