import model.Book;
import model.Reader;
import model.Role;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.ReaderRepository;
import repository.ReaderRepositoryImpl;
import service.LibraryService;
import service.LibraryServiceImpl;
import utils.MyList;

import java.util.Scanner;

import static view.Color.*;

/**
 * Главный класс приложения
 */
public class MainApp {
    private static LibraryService libraryService;

    private static Reader currentReader;

    public static void main(String[] args) {
        // Инициализация репозиториев и сервиса

        BookRepository bookRepository = new BookRepositoryImpl();
        ReaderRepository readerRepository = new ReaderRepositoryImpl();
        libraryService = new LibraryServiceImpl(bookRepository, readerRepository);

        // Добавим несколько книг в библиотеку
        libraryService.addBook("Капитанская дочка", "Пушкин А.С.");
        libraryService.addBook("Война и мир", "Толстой Л.Н.");
        libraryService.addBook("Преступление и наказание", "Достоевский Ф.М.");
        libraryService.addBook("Герой нашего времени", "Лермонтов М.Ю.");
        libraryService.addBook("Мастер и Маргарита", "Булгаков М.А.");

        // Добавим пользователей
        libraryService.registerReader("admin", "admin@example.com", "ADMIN");
        libraryService.registerReader("Ivan", "ivan@example.com", "READER");
        libraryService.registerReader("Maria", "maria@example.com", "READER");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentReader == null) {
                System.out.println("Добро пожаловать в библиотеку!");
                System.out.print("Пожалуйста, введите ваше имя для авторизации: ");
                String name = scanner.nextLine();
                currentReader = libraryService.authenticateReader(name);
                if (currentReader == null) {
                    System.out.print("Пользователь не найден. Желаете зарегистрироваться? (да/нет): ");
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("да")) {
                        System.out.print("Введите ваш email: ");
                        String email = scanner.nextLine();
                        libraryService.registerReader(name, email, "READER");
                        currentReader = libraryService.authenticateReader(name);
                        System.out.println("Регистрация успешна!");
                    } else {
                        continue;
                    }
                }
            }

            System.out.println(COLOR_CYAN + "\n===== Меню =====" + COLOR_RESET);
            System.out.println("1. Показать все книги");
            System.out.println("2. Поиск книги по названию");
            System.out.println("3. Поиск книги по автору");
            System.out.println("4. Взять книгу");
            System.out.println("5. Вернуть книгу");
            System.out.println("6. Показать доступные книги");
            System.out.println("7. Показать занятые книги");
            System.out.println("8. Показать мои книги");
            System.out.println("9. Сортировать книги по названию");
            System.out.println("10. Сортировать книги по автору");
            if (currentReader.getRole() == Role.ADMIN) {
                System.out.println("11. Добавить книгу");
                System.out.println("12. Редактировать книгу");
                System.out.println("13. Посмотреть, у кого находится книга");
            }
            System.out.println("0. Выйти");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAllBooks();
                    break;
                case "2":
                    searchBookByTitle(scanner);
                    break;
                case "3":
                    searchBookByAuthor(scanner);
                    break;
                case "4":
                    borrowBook(scanner);
                    break;
                case "5":
                    returnBook(scanner);
                    break;
                case "6":
                    showAvailableBooks();
                    break;
                case "7":
                    showBorrowedBooks();
                    break;
                case "8":
                    showMyBooks();
                    break;
                case "9":
                    libraryService.sortBooksByTitle();
                    System.out.println("Книги отсортированы по названию.");
                    break;
                case "10":
                    libraryService.sortBooksByAuthor();
                    System.out.println("Книги отсортированы по автору.");
                    break;
                case "11":
                    if (currentReader.getRole() == Role.ADMIN) {
                        addBook(scanner);
                    } else {
                        System.out.println("Неверный выбор.");
                    }
                    break;
                case "12":
                    if (currentReader.getRole() == Role.ADMIN) {
                        editBook(scanner);
                    } else {
                        System.out.println("Неверный выбор.");
                    }
                    break;
                case "13":
                    if (currentReader.getRole() == Role.ADMIN) {
                        viewBookBorrower(scanner);
                    } else {
                        System.out.println("Неверный выбор.");
                    }
                    break;
                case "0":
                    currentReader = null;
                    System.out.println("Вы вышли из системы.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }


    private static void showAllBooks() {
        MyList<Book> books = libraryService.getAllBooks();
        if (books == null || books.size() == 0) {  // проверка на null или пустой список
            System.out.println("Список книг пуст.");
            return;
        }
        System.out.println(COLOR_YELLOW + "Список всех книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " (доступна)" : " (занята)"));
        }
    }

    private static void searchBookByTitle(Scanner scanner) {
        System.out.print("Введите название книги или его часть: ");
        String title = scanner.nextLine();
        MyList<Book> books = libraryService.searchBooksByTitle(title);
        if (books.size() > 0) {
            System.out.println(COLOR_YELLOW + "Найденные книги:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " (доступна)" : " (занята)"));
            }
        } else {
            System.out.println("Книги не найдены.");
        }
    }

    private static void searchBookByAuthor(Scanner scanner) {
        System.out.print("Введите имя автора или его часть: ");
        String author = scanner.nextLine();
        MyList<Book> books = libraryService.searchBooksByAuthor(author);
        if (books.size() > 0) {
            System.out.println(COLOR_YELLOW + "Найденные книги:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " (доступна)" : " (занята)"));
            }
        } else {
            System.out.println("Книги не найдены.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Введите название книги, которую хотите взять: ");
        String title = scanner.nextLine();
        boolean success = libraryService.borrowBook(title, currentReader.getName());
        if (success) {
            System.out.println(COLOR_GREEN + "Книга успешно взята." + COLOR_RESET);
        } else {
            System.out.println(COLOR_RED + "Не удалось взять книгу. Возможно, она занята или не существует." + COLOR_RESET);
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Введите название книги, которую хотите вернуть: ");
        String title = scanner.nextLine();
        boolean success = libraryService.returnBook(title, currentReader.getName());
        if (success) {
            System.out.println(COLOR_GREEN + "Книга успешно возвращена." + COLOR_RESET);
        } else {
            System.out.println(COLOR_RED + "Не удалось вернуть книгу. Возможно, она не числится за вами." + COLOR_RESET);
        }
    }

    private static void showAvailableBooks() {
        MyList<Book> books = libraryService.getAllAvailableBooks();
        System.out.println(COLOR_YELLOW + "Список доступных книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor());
        }
    }

    private static void showBorrowedBooks() {
        MyList<Book> books = libraryService.getAllBorrowedBooks();
        System.out.println(COLOR_YELLOW + "Список занятых книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor());
        }
    }

    private static void showMyBooks() {
        MyList<Book> books = libraryService.getBooksBorrowedByReader(currentReader.getName());
        if (books != null && books.size() > 0) {
            System.out.println(COLOR_YELLOW + "Книги, которые вы взяли:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor());
            }
        } else {
            System.out.println("У вас нет взятых книг.");
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        libraryService.addBook(title, author);
        System.out.println(COLOR_GREEN + "Книга успешно добавлена." + COLOR_RESET);
    }

    private static void editBook(Scanner scanner) {
        System.out.print("Введите название книги, которую хотите отредактировать: ");
        String oldTitle = scanner.nextLine();
        System.out.print("Введите новое название книги: ");
        String newTitle = scanner.nextLine();
        System.out.print("Введите нового автора книги: ");
        String newAuthor = scanner.nextLine();
        boolean success = libraryService.editBook(oldTitle, newTitle, newAuthor, currentReader);
        if (success) {
            System.out.println(COLOR_GREEN + "Книга успешно отредактирована." + COLOR_RESET);
        } else {
            System.out.println(COLOR_RED + "Не удалось отредактировать книгу. Возможно, книга не найдена." + COLOR_RESET);
        }
    }

    private static void viewBookBorrower(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        Reader borrower = libraryService.getBookBorrower(title);
        if (borrower != null) {
            System.out.println("Книгу '" + title + "' взял: " + borrower.getName());
        } else {
            System.out.println("Книга доступна или не найдена.");
        }
    }
}
