package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

public class BookRepositoryImpl implements BookRepository{
    private final MyList<Book> books;

    // объект, отвечающий за генерацию уникальных id
//    private final AtomicInteger currentId = new AtomicInteger(1);
//
    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
//        addBooks();
    }

//    private void addBooks() {
//        books.addAll(
//                new Book(currentId.getAndIncrement(), "Капитанская дочь", "Пушкин А. С."),
//                new Book(currentId.getAndIncrement(), "Божественная комедия", "Данте"),
//                new Book(currentId.getAndIncrement(), "Гамлет", "Шекспир"),
//                new Book(currentId.getAndIncrement(), "Робинзон Крузо", "Дефо Д."),
//                new Book(currentId.getAndIncrement(), "Фауст", "Гёте И.В."),
//                new Book(currentId.getAndIncrement(), "Гордость и предубеждение", "Остин Д.")
//        );
//    }

//    @Override
//    public void addBook(String id, String title, String author) {
//        Book book = new Book(currentId.getAndIncrement(), title, author);
//        books.add(book); // сохранение в "хранилище"
//    }


    @Override
    public void addBook(String title, String author) {

    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equals(title)) result.add(book);
        }
        return result;
    }

//    @Override
//    public MyList<Book> getAvailableBook() {
//        return null;
//    }

    // Метод, возвращающий список всех доступных книг
    public MyList<Book> getAllAvailableBooks() {
        MyList<Book> result = new MyArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) result.add(book);
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }
}