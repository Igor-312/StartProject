package repository;

import model.Reader;
import model.Role;
import utils.MyList;

// Интерфейс репозитория читателей
public interface ReaderRepository {

    // Добавить читателя
    Reader addReader(String name, String email, String password, Role role);

    // Получить читателя по имени
    Reader getReaderByName(String name);

    // Получить список всех читателей
    MyList<Reader> getAllReaders();
}
