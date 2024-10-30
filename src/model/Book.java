package model;

import utils.MyList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Book {

    private String title; // Название
    private String author;
    private boolean isAvailable = true; // Свободна ли книга
    private LocalDate borrowedDate;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", isAvailable = " + isAvailable +
                ", borrowedDate = " + borrowedDate +
                '}';
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long daysBorrowed() {
        if (borrowedDate == null) return 0;
        return ChronoUnit.DAYS.between(borrowedDate, LocalDate.now());
    }

}


