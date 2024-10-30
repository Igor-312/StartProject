package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

/**
 * Реализация репозитория книг
 */
public class BookRepositoryImpl implements BookRepository {
    private final MyList<Book> books;

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
    }

    @Override
    public void addBook(String title, String author) {
        // Создаем новую книгу и добавляем в список
        Book book = new Book(title, author);
        books.add(book);
    }

    @Override
    public MyList<Book> getAllBooks() {
        // Возвращаем список всех книг
        return books;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        // Поиск книг по автору
        MyList<Book> result = new MyArrayList<>();
        String lowerAuthor = author.toLowerCase();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(lowerAuthor)) {
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
        for (Book book : books) {
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
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        // Удаление книги из списка
        books.remove(book);
    }

    @Override
    public void sortByTitle() {
        // Сортировка книг по названию с использованием пузырьковой сортировки
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = books.get(j);
                Book book2 = books.get(j + 1);
                if (book1.getTitle().compareToIgnoreCase(book2.getTitle()) > 0) {
                    // Меняем местами книги
                    books.set(j, book2);
                    books.set(j + 1, book1);
                }
            }
        }
    }

    @Override
    public void sortByAuthor() {
        // Сортировка книг по автору с использованием пузырьковой сортировки
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Book book1 = books.get(j);
                Book book2 = books.get(j + 1);
                if (book1.getAuthor().compareToIgnoreCase(book2.getAuthor()) > 0) {
                    // Меняем местами книги
                    books.set(j, book2);
                    books.set(j + 1, book1);
                }
            }
        }
    }
}
