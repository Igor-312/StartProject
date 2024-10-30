package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {
    // Create - add
    void addBook(String title, String author);

    // READ
    // получить список всех книг
    MyList<Book> getAllBooks();

    // Получить список книг по автору
    MyList<Book> getBooksByAuthor(String author);


    // Получить список книг по названию
    MyList<Book> getBooksByTitle(String title);

    // Получить список свободных книг
    MyList<Book> getAllAvailableBooks();

    // Delete
    void deleteBook(Book book);
}
