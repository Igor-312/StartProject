package service;

import model.Book;
import model.Reader;
import utils.MyList;

/**
 * Интерфейс сервисного слоя библиотеки
 */
public interface LibraryService {

    /**
     * Добавить книгу в библиотеку
     */
    void addBook(String title, String author);

    /**
     * Получить список всех книг
     */
    MyList<Book> getAllBooks();

    /**
     * Поиск книг по названию (частичное совпадение)
     */
    MyList<Book> searchBooksByTitle(String title);

    /**
     * Поиск книг по автору (частичное совпадение)
     */
    MyList<Book> searchBooksByAuthor(String author);

    /**
     * Взять книгу из библиотеки
     */
    boolean borrowBook(String bookTitle, String readerName);

    /**
     * Вернуть книгу в библиотеку
     */
    boolean returnBook(String bookTitle, String readerName);

    /**
     * Получить список всех доступных книг
     */
    MyList<Book> getAllAvailableBooks();

    /**
     * Получить список всех занятых книг
     */
    MyList<Book> getAllBorrowedBooks();

    /**
     * Зарегистрировать нового читателя
     */
    void registerReader(String name, String email, String roleStr);

    /**
     * Авторизовать читателя по имени
     */
    Reader authenticateReader(String name);

    /**
     * Получить список книг, взятых читателем
     */
    MyList<Book> getBooksBorrowedByReader(String readerName);

    /**
     * Редактировать информацию о книге (только для ADMIN)
     */
    boolean editBook(String oldTitle, String newTitle, String newAuthor, Reader admin);

    /**
     * Узнать, кто взял книгу
     */
    Reader getBookBorrower(String bookTitle);

    /**
     * Сортировка книг по названию
     */
    void sortBooksByTitle();

    /**
     * Сортировка книг по автору
     */
    void sortBooksByAuthor();
}
