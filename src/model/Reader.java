package model;

import utils.MyArrayList;
import utils.MyList;


public class Reader {
    // Реализация метода:
    private int id;
    private String name;
    private String email;
    private Role role;
    private final MyList<Book> borrowedBooks;

    public Reader(int id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.borrowedBooks = new MyArrayList<>();
    }

    public String getName() { // Возвращает имя читателя
        return name; // Возвращает значение name
    }

    public int getId() { // Возвращает ID читателя
        return id; // Возвращает значение id
    }

    public String getEmail() { // Возвращает электронную почту читателя
        return email; // Возвращает значение email
    }

    public Role getRole() { // Возвращает роль читателя
        return role; // Возвращает значение role
    }

    public void setRole(Role role) { // Устанавливает роль читателя
        this.role = role; // Присваивает значение переменной role
    }

    public MyList<Book> getBorrowedBooks() { // Возвращает список взятых книг
        return borrowedBooks; // Возвращает значение borrowedBooks
    }

    public void addBook(Book book) { // Добавляет книгу в список взятых
        borrowedBooks.add(book); // Добавляет book в borrowedBooks
    }

    public void removeBook(Book book) { // Удаляет книгу из списка взятых
        borrowedBooks.remove(book); // Удаляет book из borrowedBooks
    }

}

