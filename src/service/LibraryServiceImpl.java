package service;

import model.Book;
import model.Reader;
import model.Role;
import repository.BookRepository;
import repository.ReaderRepository;
import utils.MyArrayList;
import utils.MyList;

import java.time.LocalDate;

// Service библиотеки
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public LibraryServiceImpl(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    @Override
    public void addBook(String title, String author) {
        bookRepository.addBook(title, author);
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> searchBooksByTitle(String title) {
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public MyList<Book> searchBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public boolean borrowBook(String bookTitle, String readerName) {
        Book book = findBookByTitle(bookTitle);
        Reader reader = readerRepository.getReaderByName(readerName);
        if (book != null && book.isAvailable() && reader != null) {
            book.setAvailable(false);
            book.setBorrowedDate(LocalDate.now());
            reader.addBook(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(String bookTitle, String readerName) {
        Reader reader = readerRepository.getReaderByName(readerName);
        if (reader != null) {
            MyList<Book> borrowedBooks = reader.getBorrowedBooks();
            for (Book book : borrowedBooks) {
                if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                    book.setAvailable(true);
                    book.setBorrowedDate(null);
                    reader.removeBook(book);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public MyList<Book> getAllAvailableBooks() {
        return bookRepository.getAllAvailableBooks();
    }

    @Override
    public MyList<Book> getAllBorrowedBooks() {
        MyList<Book> allBooks = bookRepository.getAllBooks();
        MyList<Book> borrowedBooks = new MyArrayList<>();
        for (Book book : allBooks) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Override
    public void registerReader(String name, String email, String roleStr) {
        Role role;
        try {
            role = Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            role = Role.READER;
        }
        readerRepository.addReader(name, email, role);
    }

    @Override
    public Reader authenticateReader(String name) {
        return readerRepository.getReaderByName(name);
    }

    @Override
    public MyList<Book> getBooksBorrowedByReader(String readerName) {
        Reader reader = readerRepository.getReaderByName(readerName);
        if (reader != null) {
            return reader.getBorrowedBooks();
        }
        return null;
    }

    @Override
    public boolean editBook(String oldTitle, String newTitle, String newAuthor, Reader admin) {
        if (admin.getRole() != Role.ADMIN) {
            return false;
        }
        Book book = findBookByTitle(oldTitle);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            return true;
        }
        return false;
    }

    @Override
    public Reader getBookBorrower(String bookTitle) {
        MyList<Reader> allReaders = readerRepository.getAllReaders();
        for (Reader reader : allReaders) {
            MyList<Book> borrowedBooks = reader.getBorrowedBooks();
            for (Book book : borrowedBooks) {
                if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                    return reader;
                }
            }
        }
        return null;
    }

    @Override
    public void sortBooksByTitle() {
        bookRepository.sortByTitle();
    }

    @Override
    public void sortBooksByAuthor() {
        bookRepository.sortByAuthor();
    }

    /**
     * Вспомогательный метод для поиска книги по названию
     */
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