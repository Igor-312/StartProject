package service;

import model.Book;
import model.Reader;
import model.Role;
import repository.BookRepository;
import repository.ReaderRepository;
import utils.MyList;

// Реализация сервисного слоя библиотеки
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public LibraryServiceImpl(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    @Override
    public void addBook(String title, String author) {

    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> searchBooksByTitle(String title) {
        return null;
    }

    @Override
    public MyList<Book> searchBooksByAuthor(String author) {
        return null;
    }

    @Override
    public boolean borrowBook(String bookTitle, String readerName) {
        return false;
    }

    @Override
    public boolean returnBook(String bookTitle, String readerName) {
        return false;
    }

    @Override
    public MyList<Book> getAllBorrowedBooks() {
        return null;
    }

    @Override
    public void registerReader(String name, String email, String roleStr) {
        Role role = Role.valueOf(roleStr.toUpperCase()); // Преобразуем строку роли в тип Role
        readerRepository.addReader(name, email, role);
    }

    @Override
    public Reader authenticateReader(String name) {
        return readerRepository.getReaderByName(name);
    }

    @Override
    public MyList<Book> getBooksBorrowedByReader(String readerName) {
        return null;
    }

    @Override
    public boolean editBook(String oldTitle, String newTitle, String newAuthor, Reader admin) {
        return false;
    }

    @Override
    public Reader getBookBorrower(String bookTitle) {
        return null;
    }

    @Override
    public void sortBooksByTitle() {

    }

    @Override
    public void sortBooksByAuthor() {

    }

    @Override
    public MyList<Book> getAllAvailableBooks() {
        return bookRepository.getAllAvailableBooks();
    }


    //Вспомогательный метод для поиска книги по названию
    private Book findBookByTitle(String title) {
        MyList<Book> allBooks = bookRepository.getAllBooks();
        for (Book book : allBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}