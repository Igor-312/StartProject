package repository;

import model.Book;
import model.Genre;
import utils.MyList;

/**
 * Интерфейс репозитория для работы с книгами.
 */
public interface BookRepository {
    // Добавление книги
    void addBook(String title, String author, int year, MyList<Genre> genres);

    // Получение списка всех книг
    MyList<Book> getAllBooks();

    // Получение списка книг по автору
    MyList<Book> getBooksByAuthor(String author);

    // Получение списка книг по названию
    MyList<Book> getBooksByTitle(String title);

    // Получение списка доступных книг
    MyList<Book> getAllAvailableBooks();

    // Удаление книги
    void deleteBook(Book book);

    // Сортировка книг по названию
    void sortByTitle();

    // Сортировка книг по автору
    void sortByAuthor();
}
