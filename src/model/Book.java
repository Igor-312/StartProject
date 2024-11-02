package model;

import utils.MyArrayList;
import utils.MyList;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Objects;

/**
 * Класс, представляющий книгу в библиотеке.
 */
public class Book {

    private final int id;
    private String title; // Название книги
    private String author; // Автор книги
    private final int year;
    private boolean isAvailable; // Доступна ли книга для выдачи
    private final MyList<Genre> genres;
    private LocalDate borrowedDate; // Дата, когда книга была взята

    public Book(int id, String title, String author, int year, MyList<Genre> genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genres = genres;
        this.isAvailable = true;
    }

    public String getDescriptions () {
        StringBuilder sb = new StringBuilder();
        genres.forEach(genre -> sb.append(genre.getDescription()).append(","));
        return sb.substring(0, sb.length() - 1);
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
                ", genre = '" + Collections.singletonList(genres) + '\'' +
                ", isAvailable = " + isAvailable +
                ", borrowedDate = " + borrowedDate +
                '}';
    }

    // Геттеры и сеттеры

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


    public int getYear() {
        return year;
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

    public static MyList<Genre> createGenres(Genre... genres) {
        MyList<Genre> listGenre = new MyArrayList<>();
        for (Genre genre : genres) {
            listGenre.add(genre);
        }
        return listGenre;
    }
}
