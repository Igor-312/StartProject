package repository;

import model.Reader;
import model.Role;
import utils.MyList;

public interface ReaderRepository {

    Reader addReader(String name, String email, Role role);

    Reader getReaderByName(String name);

    MyList<Reader> getAllReaders;
}
