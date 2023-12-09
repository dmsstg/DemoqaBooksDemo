package com.demoqa.books.base;

import com.demoqa.books.pojos.BookStore;
import com.demoqa.books.pojos.User;
import lombok.Data;

@Data
public class ScenarioContext {
    private User user;
    private final BookStore bookStore;
    public ScenarioContext(){
        user = new User();
        user.setUsername(TestEnvironment.VALID_USERNAME);
        user.setPassword(TestEnvironment.VALID_PASSWORD);
        bookStore = new BookStore();
    }
}
