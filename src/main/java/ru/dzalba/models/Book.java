package ru.dzalba.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "The book title must not be empty")
    @Size(min=2, max=100, message = "The book title must be between 2 and 100 characters long")
    private String title;
    @NotEmpty(message = "Author must not be empty")
    @Size(min=2, max=100, message = "Author name must be between 2 and 100 characters long")
    private String author;
    private int year;

    public Book(){}

    public Book(int book_id, String title, String author, int year) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }


}
