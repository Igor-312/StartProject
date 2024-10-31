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
import utils.Validator;

import java.util.Scanner;

import static view.Color.*;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 *  * Authors: группа №1 Светлана, Елена, Игорь, Богдан.
 *  * Date: 31-10-2024
 * Главный класс приложения Библиотека.
 */
public class MainApp {
    private static LibraryService libraryService;

    private static Reader currentReader;

    private static final Validator validator = new Validator();

    public static void main(String[] args) {

        // Инициализация репозиториев и сервиса

        BookRepository bookRepository = new BookRepositoryImpl();
        ReaderRepository readerRepository = new ReaderRepositoryImpl();
        libraryService = new LibraryServiceImpl(bookRepository, readerRepository);

        // книги зарегистрированные в библиотеке
        // книги на русском
        libraryService.addBook("Капитанская дочка", "Пушкин А.С.");
        libraryService.addBook("Война и мир", "Толстой Л.Н.");
        libraryService.addBook("Преступление и наказание", "Достоевский Ф.М.");
        libraryService.addBook("Герой нашего времени", "Лермонтов М.Ю.");
        libraryService.addBook("Мастер и Маргарита", "Булгаков М.А.");
        // Книги на английском языке
        libraryService.addBook("To Kill a Mockingbird", "Harper Lee");
        libraryService.addBook("Pride and Prejudice", "Jane Austen");
        libraryService.addBook("1984", "George Orwell");
        libraryService.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        libraryService.addBook("Moby-Dick", "Herman Melville");

        // Книги на немецком
        libraryService.addBook("Faust", "Johann Wolfgang von Goethe");
        libraryService.addBook("Die Verwandlung", "Franz Kafka");
        libraryService.addBook("Der Steppenwolf", "Hermann Hesse");
        libraryService.addBook("Die Leiden des jungen Werther", "Johann Wolfgang von Goethe");
        libraryService.addBook("Im Westen nichts Neues", "Erich Maria Remarque");

        // Главные администраторы
        libraryService.registerReader("Bogdan", "bogdan@example.com", "123", "ADMIN");
        libraryService.registerReader("Igor", "igor@example.com","123", "ADMIN");
        libraryService.registerReader("Elena", "elena@example.com","123", "ADMIN");
        libraryService.registerReader("Svitlana", "svitlana@example.com","123", "ADMIN");

        // зарегистрированные пользователи библиотеки
        libraryService.registerReader("admin", "admin@example.com","123", "ADMIN");
        libraryService.registerReader("Ivan", "ivan@example.com","123", "READER");
        libraryService.registerReader("Maria", "maria@example.com","123", "READER");



        // сканируем ввод пользователя
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentReader == null) {
                System.out.println("Добро пожаловать в библиотеку \"Знания Века\"!");
                System.out.print("Пожалуйста, введите ваше имя для авторизации, используя латинские буквы: ");

                String name = scanner.nextLine();
                currentReader = libraryService.authenticateReader(name);
                if (currentReader == null) {
                    System.out.print("Пользователь не найден. Желаете зарегистрироваться? (да/нет): ");
                    String answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("yes")) {
                        System.out.print("Введите ваш email: ");
                        String email = scanner.nextLine();
                        while(!validator.isValidEmail(email)) {
                            System.out.println("\"Ваш email " +
                                    "должен содержать латинские буквы, цифры, символ '@' и домен, например, " +
                                    "'name@example.com'.\" ");
                            System.out.print("Введите ваш email: ");
                            email = scanner.nextLine();
                        }
                        System.out.print("Введите ваш password: ");
                        String password = scanner.nextLine();
                        while(!validator.isValidPassword(password)) {
                            System.out.println("Ваш пароль должен содержать минимум 8 символов, хотя бы одна заглавная и " +
                                    "строчная буква, хотя бы одна цифра и один спецсимвол, например: @, #, $.");
                            System.out.print("Введите ваш password: ");
                            password = scanner.nextLine();
                        }
                        libraryService.registerReader(name, email, password, "READER");
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


    // проверяем список книг
    private static void showAllBooks() {
        MyList<Book> books = libraryService.getAllBooks();
        if (books == null || books.isEmpty()) {  // проверка на null или пустой список
            System.out.println("Список книг пуст.");
            return;
        }
        System.out.println(COLOR_YELLOW + "Список всех книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " " +
                    "(доступна)" : " (занята)"));
        }
    }

    // проверяем доступность книги даже при частичном вводе названия
    private static void searchBookByTitle(Scanner scanner) {
        System.out.print("Введите название книги или его часть: ");
        String title = scanner.nextLine();
        MyList<Book> books = libraryService.searchBooksByTitle(title);
        if (!books.isEmpty()) {
            System.out.println(COLOR_YELLOW + "Найденные книги:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " " +
                        "(доступна)" : " (занята)"));
            }
        } else {
            System.out.println("Книги не найдены.");
        }
    }

    // находим книгу по автору или частичному его название
    private static void searchBookByAuthor(Scanner scanner) {
        System.out.print("Введите имя автора или его часть: ");
        String author = scanner.nextLine();
        MyList<Book> books = libraryService.searchBooksByAuthor(author);
        if (!books.isEmpty()) {
            System.out.println(COLOR_YELLOW + "Найденные книги:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor() + (book.isAvailable() ? " " +
                        "(доступна)" : " (занята)"));
            }
        } else {
            System.out.println("Книги не найдены.");
        }
    }

    // вот пользователям книги которую хочет взять
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

    // вот пользователям название книги которую хочет вернуть
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

    // выводим список доступных книг
    private static void showAvailableBooks() {
        MyList<Book> books = libraryService.getAllAvailableBooks();
        System.out.println(COLOR_YELLOW + "Список доступных книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor());
        }
    }

    // выводим список занятых книг
    private static void showBorrowedBooks() {
        MyList<Book> books = libraryService.getAllBorrowedBooks();
        System.out.println(COLOR_YELLOW + "Список занятых книг:" + COLOR_RESET);
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getAuthor());
        }
    }

    // будет список книг которую взял пользователь
    private static void showMyBooks() {
        MyList<Book> books = libraryService.getBooksBorrowedByReader(currentReader.getName());
        if (books != null && !books.isEmpty()) {
            System.out.println(COLOR_YELLOW + "Книги, которые вы взяли:" + COLOR_RESET);
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor());
            }
        } else {
            System.out.println("У вас нет взятых книг.");
        }
    }

    // добавляем книгу
    private static void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        libraryService.addBook(title, author);
        System.out.println(COLOR_GREEN + "Книга успешно добавлена." + COLOR_RESET);
    }

    // редактировать книгу
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

    // выводим на печать кто взял книгу название книги
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
