
import model.Book;
import model.Reader;
import model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepositoryImpl;
import repository.ReaderRepositoryImpl;
import service.LibraryService;
import service.LibraryServiceImpl;
import utils.MyList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты
 */
public class LibraryServiceTest {
    private LibraryService libraryService;
    private Reader reader1;
    private Reader reader2;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        // Инициализируем репозиториев и сервиса
        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        ReaderRepositoryImpl readerRepository = new ReaderRepositoryImpl();
        libraryService = new LibraryServiceImpl(bookRepository, readerRepository);

        // Регистрация тестовых пользователей
        libraryService.registerReader("Alice", "alice@example.com", "123", "READER");
        libraryService.registerReader("Bob", "bob@example.com", "123", "READER");

        reader1 = libraryService.authenticateReader("Alice");
        reader2 = libraryService.authenticateReader("Bob");

        // Добавление книг в библиотеку
        libraryService.addBook("Effective Java", "Joshua Bloch");
        libraryService.addBook("Clean Code", "Robert C. Martin");
        libraryService.addBook("Java Concurrency in Practice", "Brian Goetz");

        // Получаем ссылки на книги для тестирования
        book1 = libraryService.searchBooksByTitle("Effective Java").get(0);
        book2 = libraryService.searchBooksByTitle("Clean Code").get(0);
        book3 = libraryService.searchBooksByTitle("Java Concurrency in Practice").get(0);
    }

    @Test
    public void testAddBook() {
        // Проверка, что книги добавлены корректно
        assertEquals(3, libraryService.getAllBooks().size());
    }

    @Test
    public void testBorrowBookSuccess() {
        // Проверка успешного взятия книги
        boolean result = libraryService.borrowBook(book1.getTitle(), reader1.getName());
        assertTrue(result);
        Book borrowedBook = libraryService.searchBooksByTitle(book1.getTitle()).get(0);
        assertFalse(borrowedBook.isAvailable());
        assertEquals(LocalDate.now(), borrowedBook.getBorrowedDate());
    }

    @Test
    public void testBorrowBookAlreadyBorrowed() {
        // Проверка взятия книги, которая уже занята
        libraryService.borrowBook(book1.getTitle(), reader1.getName());
        boolean result = libraryService.borrowBook(book1.getTitle(), reader2.getName());
        assertFalse(result);
    }

    @Test
    public void testReturnBookSuccess() {
        // Проверка успешного возврата книги
        libraryService.borrowBook(book2.getTitle(), reader1.getName());
        boolean result = libraryService.returnBook(book2.getTitle(), reader1.getName());
        assertTrue(result);
        Book returnedBook = libraryService.searchBooksByTitle(book2.getTitle()).get(0);
        assertTrue(returnedBook.isAvailable());
        assertNull(returnedBook.getBorrowedDate());
    }

    @Test
    public void testReturnBookNotBorrowedByUser() {
        // Проверка возврата книги, которая не была взята этим пользователем
        libraryService.borrowBook(book3.getTitle(), reader1.getName());
        boolean result = libraryService.returnBook(book3.getTitle(), reader2.getName());
        assertFalse(result);
    }

    @Test
    public void testGetAllAvailableBooks() {
        // Проверка списка доступных книг
        libraryService.borrowBook(book1.getTitle(), reader1.getName());
        MyList<Book> availableBooks = libraryService.getAllAvailableBooks();
        assertEquals(2, availableBooks.size());
    }

    @Test
    public void testSortBooksByAuthor() {
        // Проверка сортировки книг по автору
        libraryService.sortBooksByAuthor();
        MyList<Book> sortedBooks = libraryService.getAllBooks();

        assertEquals("Java Concurrency in Practice", sortedBooks.get(0).getTitle()); // Brian Goetz
        assertEquals("Effective Java", sortedBooks.get(1).getTitle()); // Joshua Bloch
        assertEquals("Clean Code", sortedBooks.get(2).getTitle()); // Robert C. Martin
    }

    @Test
    public void testSortBooksByTitle() {
        // Проверка сортировки книг по названию
        libraryService.sortBooksByTitle();
        MyList<Book> sortedBooks = libraryService.getAllBooks();

        assertEquals("Clean Code", sortedBooks.get(0).getTitle());
        assertEquals("Effective Java", sortedBooks.get(1).getTitle());
        assertEquals("Java Concurrency in Practice", sortedBooks.get(2).getTitle());
    }

    @Test
    public void testDaysBookBorrowed() {
        // Проверка количества дней, когда книга находится у пользователя
        libraryService.borrowBook(book3.getTitle(), reader1.getName());
        Book borrowedBook = libraryService.searchBooksByTitle(book3.getTitle()).get(0);
        borrowedBook.setBorrowedDate(LocalDate.now().minusDays(5)); // Устанавливаем дату взятия 5 дней назад
        assertEquals(5, borrowedBook.daysBorrowed());
    }
}
