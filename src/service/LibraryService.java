package service;

import model.Book;
import model.Reader;
import repository.ReaderRepository;
import utils.MyList;
import utils.Validator;

public interface LibraryService {
    //Добавить книгу в библиотеку
    void addBook(String title, String author, int year);
    //READ
    //Получить список всех книг
    MyList<Book> getAllBooks();
    //Получить список книг по названию
    MyList<Book> searchBooksByTitle(String title);
    //Получить список книг по автору
    MyList<Book> searchBooksByAuthor(String author);
    //Взять книгу из библиотеки
    boolean borrowBook(String bookTitle, String readerName);
    //Вернуть книгу в библиотеку
    boolean returnBook(String bookTitle, String readerName);

    //Получить список всех доступных книг
    MyList<Book> getAllAvailableBooks();

    //Получить список всех занятых книг
    MyList<Book> getAllBorrowedBooks();

    //Зарегистрировать нового читателя
    void registerReader(String name, String email, String password, String roleStr);
    //Авторизировать читателя по имени
    Reader authenticateReader(String name);
    //Получить список занятых книг, взятых читателем
    MyList<Book> getBooksBorrowedByReader(String readerName);
    //Редактировать информацию о книге,только ADMIN
    boolean editBook(String oldTitle, String newTitle, String newAuthor, Reader admin);
    //Узнать кто взял книгу
    Reader getBookBorrower(String bookTitle);
    //Сортировка книга по названию
    void sortBooksByTitle();
    //Сортировка книг по автору
    void  sortBooksByAuthor();

    Reader getReaderByName(String name);

    MyList<Book> getBooksByName(String title);

    ReaderRepository getReaderRepository();
    Security getSecurity();
    Validator getValidator();
}
