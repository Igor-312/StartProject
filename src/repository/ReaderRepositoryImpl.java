package repository;

import db.DB;
import model.Reader;
import model.Role;
import utils.MyArrayList;
import utils.MyList;

// Реализация репозитория читателей
public class ReaderRepositoryImpl implements ReaderRepository {

    private final DB db;

    public ReaderRepositoryImpl(DB db) {
        this.db = db;
    }

    @Override
    public Reader addReader(String name, String email, String password, Role role) {
        int id = db.getReaderId() + 1;
        Reader reader = new Reader(id, name, email, password, role);
        getAllReaders().add(reader);
        return reader;
    }

    @Override
    public Reader getReaderByName(String name) {
        for (Reader reader : getAllReaders()) {
            if (reader.getName().equalsIgnoreCase(name)) {
                return reader;
            }
        }
        return null;
    }

    @Override
    public MyList<Reader> getAllReaders() {
        return db.getReaders();
    }
}
