package model;

import utils.MyArrayList;
import utils.MyList;

/**
 * Класс, представляющий читателя библиотеки.
 */
public class Reader {
    private final int id; // Уникальный идентификатор читателя
    private final String name; // Имя читателя
    private final String email; // Электронная почта читателя
    private final String password; // Пароль читателя
    private Role role; // Роль читателя (READER, ADMIN, BLOCKED)
    private final MyList<Book> borrowedBooks; // Список взятых книг

    public Reader(int id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.borrowedBooks = new MyArrayList<>();
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Добавляет книгу в список взятых книг читателя.
     * @param book Книга для добавления.
     */
    public void addBook(Book book) {
        borrowedBooks.add(book);
    }

    /**
     * Удаляет книгу из списка взятых книг читателя.
     * @param book Книга для удаления.
     */
    public void removeBook(Book book) {
        borrowedBooks.remove(book);
    }
}
