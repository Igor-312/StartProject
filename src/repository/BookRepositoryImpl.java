package repository;

import db.DB;
import model.Book;
import model.Genre;
import utils.MyArrayList;
import utils.MyList;

/**
 * Реализация репозитория книг
 */
public class BookRepositoryImpl implements BookRepository {
    private final DB db;

    public BookRepositoryImpl(DB db) {
        this.db = db;
    }

    @Override
    public void addBook(String title, String author, int year, int pages, String language, MyList<Genre> genres) {
        // Создаем новую книгу и добавляем в список
        int id = db.getBookId() + 1;
        Book book = new Book(id, title, author, year, pages, language, genres); // Добавляем language
        db.setBookId(db.getBookId() + 1);
        db.getBooks().add(book);
    }

    @Override
    public void addBook(String title, String author, int year, MyList<Genre> genres) {
        // Добавляем книгу с пропуском параметров страниц и языка
        addBook(title, author, year, 0, "Unknown", genres);
    }

    @Override
    public void addBook(String title, String author, int year, int pages, MyList<Genre> genres) {
        // Добавляем книгу с пропуском параметра языка
        addBook(title, author, year, pages, "Unknown", genres);
    }

    @Override
    public MyList<Book> getAllBooks() {
        // Возвращаем список всех книг
        return db.getBooks();
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        // Создаем список для результатов
        MyList<Book> result = new MyArrayList<>();
        String lowerAuthor = author.toLowerCase().trim();
        for (Book book : getAllBooks()) {
            String bookAuthor = book.getAuthor().toLowerCase().trim();
            if (bookAuthor.contains(lowerAuthor)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        String lowerTitle = title.toLowerCase();
        for (Book book : getAllBooks()) {
            if (book.getTitle().toLowerCase().contains(lowerTitle)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getAllAvailableBooks() {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : getAllBooks()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        getAllBooks().remove(book);
    }

    @Override
    public void sortByTitle() {
        int n = getAllBooks().size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = getAllBooks().get(j);
                Book book2 = getAllBooks().get(j + 1);
                if (book1.getTitle().compareToIgnoreCase(book2.getTitle()) > 0) {
                    getAllBooks().set(j, book2);
                    getAllBooks().set(j + 1, book1);
                }
            }
        }
    }

    @Override
    public void sortByAuthor() {
        int n = getAllBooks().size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = getAllBooks().get(j);
                Book book2 = getAllBooks().get(j + 1);
                if (book1.getAuthor().compareToIgnoreCase(book2.getAuthor()) > 0) {
                    getAllBooks().set(j, book2);
                    getAllBooks().set(j + 1, book1);
                }
            }
        }
    }
}
