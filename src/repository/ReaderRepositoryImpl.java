package repository;

import model.Reader;


public class ReaderRepositoryImpl implements ReaderRepository {


    @Override
    public Reader addReader(int id, String name, String email) {
        return null;
    }

    @Override
    public boolean isTitleExist(String name) {
        return false;
    }

    @Override
    public Reader getReaderByTitle(String name) {
        return null;
    }
}
