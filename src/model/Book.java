package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Класс, представляющий книгу в библиотеке.
 */
public class Book {

    private int id;
    private String title; // Название книги
    private String author; // Автор книги
    private int year;
    private boolean isAvailable; // Доступна ли книга для выдачи
    private LocalDate borrowedDate; // Дата, когда книга была взята

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    // Переопределение метода equals для корректного сравнения объектов Book
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; // Исправлено для лучшей проверки
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    // Переопределение метода hashCode
    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    // Переопределение метода toString для удобного вывода информации о книге
    @Override
    public String toString() {
        return "Book{" +
                "id = '" + id + '\'' +
                "title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", isAvailable = " + isAvailable +
                ", borrowedDate = " + borrowedDate +
                '}';
    }

    // Геттеры и сеттеры
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Вычисляет количество дней, в течение которых книга находится у читателя.
     *
     * @return количество дней или 0, если книга не была взята.
     */
    public long daysBorrowed() {
        if (borrowedDate == null) return 0;
        return ChronoUnit.DAYS.between(borrowedDate, LocalDate.now());
    }
}
