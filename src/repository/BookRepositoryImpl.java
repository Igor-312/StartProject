package repository;

import db.DB;
import model.Book;
import utils.MyArrayList;
import utils.MyList;

/**
 * Реализация репозитория книг
 */
public class BookRepositoryImpl implements BookRepository {
    private final DB db;

    public BookRepositoryImpl(DB db) {
        this.db = new DB();
    }

    @Override
    public void addBook(String title, String author, int year) {
        // Создаем новую книгу и добавляем в список
        int id = db.getBookId() + 1;
        Book book = new Book(id, title, author, year);
        db.setBookId(db.getBookId() + 1);
        getAllBooks().add(book);
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
        // Приводим ввод пользователя к нижнему регистру и удаляем пробелы
        String lowerAuthor = author.toLowerCase().trim();
        for (Book book : getAllBooks()) {
            // Получаем имя автора книги, также приводим к нижнему регистру и удаляем пробелы
            String bookAuthor = book.getAuthor().toLowerCase().trim();
            // Сравниваем имена авторов
            if (bookAuthor.contains(lowerAuthor)) {
                result.add(book);
            }
        }
        return result;
    }


    @Override
    public MyList<Book> getBooksByTitle(String title) {
        // Поиск книг по названию
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
        // Получение списка доступных книг
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
        // Удаление книги из списка
        getAllBooks().remove(book);
    }

    @Override
    public void sortByTitle() {
        // Сортировка книг по названию с использованием пузырьковой сортировки
        int n = getAllBooks().size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = getAllBooks().get(j);
                Book book2 = getAllBooks().get(j + 1);
                if (book1.getTitle().compareToIgnoreCase(book2.getTitle()) > 0) {
                    // Меняем местами книги
                    getAllBooks().set(j, book2);
                    getAllBooks().set(j + 1, book1);
                }
            }
        }
    }

    @Override
    public void sortByAuthor() {
        // Сортировка книг по автору с использованием пузырьковой сортировки
        int n = getAllBooks().size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = getAllBooks().get(j);
                Book book2 = getAllBooks().get(j + 1);
                if (book1.getAuthor().compareToIgnoreCase(book2.getAuthor()) > 0) {
                    // Меняем местами книги
                    getAllBooks().set(j, book2);
                    getAllBooks().set(j + 1, book1);
                }
            }
        }
    }
}
