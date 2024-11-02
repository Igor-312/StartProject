package db;

import model.Book;
import model.Genre;
import model.Reader;
import model.Role;
import utils.MyArrayList;
import utils.MyList;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 02-11-2024
 */
/*

 */
public class DB {

    private final MyList<Reader> readers;
    private final MyList<Book> books;
    private int readerId;
    private int bookId;

    public DB() {
        this.readers = initReaders();
        this.books = initBooks();
    }

    private MyList<Reader> initReaders() {
        MyList<Reader> list = new MyArrayList<>();
        // Главные администраторы
        list.add(new Reader(1, "Bogdan", "bogdan@example.com", "123", Role.ADMIN));
        list.add(new Reader(2, "Igor", "igor@example.com", "123", Role.ADMIN));
        list.add(new Reader(3, "Elena", "elena@example.com", "123", Role.ADMIN));
        list.add(new Reader(4, "Svitlana", "svitlana@example.com", "123", Role.ADMIN));

        // зарегистрированные пользователи библиотеки
        list.add(new Reader(5, "admin", "admin@example.com", "123", Role.ADMIN));
        list.add(new Reader(6, "Ivan", "ivan@example.com", "123", Role.READER));
        list.add(new Reader(7, "Maria", "maria@example.com", "123", Role.READER));
        readerId = list.size();

        return list;
    }

    private MyList<Book> initBooks() {
        MyList<Book> list = new MyArrayList<>();

        list.add(new Book(1, "Капитанская дочка", "Пушкин А.С.", 1836, Book.createGenres(Genre.HISTORICAL, Genre.CLASSIC)));
        list.add(new Book(2, "Война и мир", "Толстой Л.Н.", 1869, Book.createGenres(Genre.HISTORICAL, Genre.PHILOSOPHICAL_FICTION)));
        list.add(new Book(3, "Преступление и наказание", "Достоевский Ф.М.", 1866, Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC)));
        list.add(new Book(4, "Герой нашего времени", "Лермонтов М.Ю.", 1840, Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC)));
        list.add(new Book(5, "Мастер и Маргарита", "Булгаков М.А.", 1967, Book.createGenres(Genre.MYSTERY, Genre.PHILOSOPHICAL_FICTION)));

// Книги на английском
        list.add(new Book(6, "To Kill a Mockingbird", "Harper Lee", 1960, Book.createGenres(Genre.CLASSIC, Genre.DRAMA)));
        list.add(new Book(7, "Pride and Prejudice", "Jane Austen", 1813, Book.createGenres(Genre.ROMANCE, Genre.CLASSIC)));
        list.add(new Book(8, "1984", "George Orwell", 1949, Book.createGenres(Genre.DYSTOPIAN, Genre.SCIENCE_FICTION)));
        list.add(new Book(9, "The Great Gatsby", "F. Scott Fitzgerald", 1925, Book.createGenres(Genre.CLASSIC, Genre.DRAMA)));
        list.add(new Book(10, "Moby-Dick", "Herman Melville", 1851, Book.createGenres(Genre.ADVENTURE, Genre.CLASSIC)));

// Книги на немецком
        list.add(new Book(11, "Faust", "Johann Wolfgang von Goethe", 1808, Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION)));
        list.add(new Book(12, "Die Verwandlung", "Franz Kafka", 1915, Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION)));
        list.add(new Book(13, "Der Steppenwolf", "Hermann Hesse", 1927, Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.DRAMA)));
        list.add(new Book(14, "Die Leiden des jungen Werther", "Johann Wolfgang von Goethe", 1774, Book.createGenres(Genre.ROMANCE, Genre.DRAMA)));
        list.add(new Book(15, "Im Westen nichts Neues", "Erich Maria Remarque", 1929, Book.createGenres(Genre.HISTORICAL, Genre.DRAMA)));

// Книги по Java
        list.add(new Book(16, "Effective Java", "Joshua Bloch", 2008, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(17, "Java: The Complete Reference", "Herbert Schildt", 2018, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(18, "Head First Java", "Kathy Sierra, Bert Bates", 2003, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(19, "Java Concurrency in Practice", "Brian Goetz", 2006, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(20, "Thinking in Java", "Bruce Eckel", 2006, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));

// Книги по Python
        list.add(new Book(21, "Learning Python", "Mark Lutz", 2013, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(22, "Python Crash Course", "Eric Matthes", 2019, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(23, "Fluent Python", "Luciano Ramalho", 2015, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(24, "Automate the Boring Stuff with Python", "Al Sweigart", 2015, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));
        list.add(new Book(25, "Python for Data Analysis", "Wes McKinney", 2017, Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL)));

        bookId = list.size();

        return list;
    }


    public MyList<Reader> getReaders() {
        return readers;
    }

    public MyList<Book> getBooks() {
        return books;
    }

    public int getReaderId() {
        return readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
