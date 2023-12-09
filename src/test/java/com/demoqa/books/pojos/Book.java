package com.demoqa.books.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("author")
    private String author;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("pages")
    public int pages;
    @JsonProperty("description")
    public String description;
    @JsonProperty("website")
    public String website;
    @JsonIgnore private String bookId;
    @JsonIgnore public String pictureSrc;

    public Book(){};

    public Book(String isbn, String title, String subTitle, String author, String publisher, int totalPages, String description, String website) {
        setIsbn(isbn);
        setTitle(title);
        setSubTitle(subTitle);
        setAuthor(author);
        setPublisher(publisher);
        setPages(totalPages);
        setDescription(description);
        setWebsite(website);
    }

    public Book(String title, String author, String publisher, String pictureSrc, String bookId) {
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setPictureSrc(pictureSrc);
        setBookId(bookId);
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPictureSrc() {
        return pictureSrc;
    }

    public void setPictureSrc(String pictureSrc) {
        this.pictureSrc = pictureSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", totalPages=" + pages +
                ", pictureSrc='" + pictureSrc + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getPages() == book.getPages()
                && Objects.equals(getIsbn(), book.getIsbn())
                && Objects.equals(getTitle(), book.getTitle())
                && Objects.equals(getSubTitle(), book.getSubTitle())
                && Objects.equals(getAuthor(), book.getAuthor())
                && Objects.equals(getPublisher(), book.getPublisher())
                && Objects.equals(getDescription(), book.getDescription())
                && Objects.equals(getWebsite(), book.getWebsite());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getBookId(), getTitle(), getSubTitle(), getAuthor(), getPublisher(), getPages(), getPictureSrc(), getDescription(), getWebsite());
    }
}
