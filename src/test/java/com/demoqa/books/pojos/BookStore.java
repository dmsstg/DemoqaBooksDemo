package com.demoqa.books.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookStore {
    @JsonProperty("books")
    private final List<Book> books = new ArrayList<>();
}
