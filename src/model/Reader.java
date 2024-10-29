package model;

import utils.MyArrayList;
import utils.MyList;

public class Reader {
    // Реализация метода:
    private String idReader;
    private String email;
    private String nameReader;
    private Role role;
    private final MyList<Book> readerBooks;

    public Reader(String idReader, String email, String nameReader) {
        this.idReader = idReader;
        this.email = email;
        this.nameReader = nameReader;
        this.role = Role.READER;
        this.readerBooks = new MyArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "idReader='" + idReader + '\'' +
                ", nameReader='" + nameReader + '\'' +
                ", readerBooks=" + readerBooks +
                '}';
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getReaderBooks() {
        return readerBooks;
    }
}

