package repository;

import model.Reader;

public interface ReaderRepository {

    Reader addReader(int id, String name, String email);

    boolean isTitleExist(String name); // Возвращает всех пользователей

    Reader getReaderByTitle(String name);

}
