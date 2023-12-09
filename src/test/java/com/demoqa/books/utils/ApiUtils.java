package com.demoqa.books.utils;

import com.demoqa.books.base.TestEnvironment;
import com.demoqa.books.pojos.Book;
import com.demoqa.books.pojos.User;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public abstract class ApiUtils {

    static private final RequestSpecification reqSpec =
            given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .baseUri(TestEnvironment.API_BASE_URL);

    private static final Logger LOG = LogManager.getLogger(ApiUtils.class);


    public static synchronized User createUser(String username, String password){
        User user;
        try {
            String json = "{"
                    + "\n\t\"userName\":\"" + username + "\","
                    + "\n\t\"password\":\"" + password + "\""
                    + "\n}";
            user =
                    given()
                            .spec(reqSpec)
                            .body(json)
                    .when()
                            .post("/Account/v1/User")
                    .then()
                            .statusCode(201)
                            .body("username", is(username))
                            .extract().jsonPath().getObject("", User.class);
            LOG.info("The user was created with the API request");
        } catch (AssertionError e){
            //In case if user exists
            user = new User();
            user.setUsername(username);
            LOG.info("The user was created prior to the scenario execution");
        }
        user.setPassword(password);
        return user;
    }

    public static synchronized User updateUserToken(User user){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        JsonPath jp =
                given()
                    .spec(reqSpec)
                    .body(user)
                .when()
                    .post("/Account/v1/GenerateToken")
                .then()
                    .statusCode(200)
                        .body("result",is("User authorized successfully."))
                        .body("status",is("Success"))
                        .extract().jsonPath();
        user.setToken(jp.getString("token"));
        return user;
    }

    public static synchronized User loginUser(User user){
        String token = updateUserToken(user).getToken();
        user.setToken(token);
        JsonPath jp =
                given()
                        .spec(reqSpec)
                        .body(user)
                .when()
                        .post("/Account/v1/Login")
                .then()
                        .statusCode(200)
                        .body("username",is(user.getUsername()))
                        .body("password",is(user.getPassword()))
                        .extract().jsonPath();
        user.setToken(jp.getString("token"));
        user.setUserId(jp.getString("userId"));
        return user;
    }

    public static synchronized void deleteUser(String username, String password){
        User user = loginUser(new User(username,password));
        given()
                .spec(reqSpec)
                .header("Authorization","Bearer " + user.getToken())
                .pathParam("id",user.getUserId())
        .when()
                .delete("/Account/v1/User/{id}")
        .then()
                .statusCode(204);
    }

    public static synchronized void addBookToUser(User user, List<Book> books){

        User usr = loginUser(user);
        String json = serializeBooksToAdd(usr,books);

        given()
                .spec(reqSpec)
                .header("Authorization","Bearer " + usr.getToken())
                .body(json)
        .when()
                .post("/BookStore/v1/Books")
        .then()
                .statusCode(201);
    }

    public static synchronized String serializeBooksToAdd(User user, List<Book> books){
        StringBuilder sb = new StringBuilder();
        sb.append("{").append("\n\t\"userId\" : \"").append(user.getUserId()).append("\",");
        sb.append("\n\t").append("\"collectionOfIsbns\" : [");

        for(Book book : books){
            sb.append("\n\t\t{");
            sb.append("\n\t\t\t\"isbn\" : \"").append(book.getIsbn()).append("\"");
            sb.append("\n\t\t}");
            if (books.indexOf(book) == books.size()-1){
                break;
            }
            sb.append("\n\t\t,");
        }
        sb.append("\n\t]");
        sb.append("\n}");
        return sb.toString();
    }

    public static List<Book> getAllBookstoreBooks(){
        return
                given()
                        .spec(reqSpec)
                .when()
                        .get("/BookStore/v1/Books")
                .then()
                        .statusCode(200)
                        .extract().jsonPath().getList("books",Book.class);
    }
}

//Copyright (C) 2023  Dmitry Shcherbakov

