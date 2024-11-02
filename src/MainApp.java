import db.DB;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.ReaderRepository;
import repository.ReaderRepositoryImpl;
import service.LibraryService;
import service.LibraryServiceImpl;
import service.Security;
import utils.Validator;
import view.LibraryView;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * * Authors: группа №1 Светлана, Елена, Игорь, Богдан.
 * * Date: 31-10-2024
 * Главный класс приложения Библиотека.
 */
public class MainApp {

    public static void main(String[] args) {
        DB db = new DB();
        BookRepository bookRepository = new BookRepositoryImpl(db);
        ReaderRepository readerRepository = new ReaderRepositoryImpl(db);
        Validator validator = new Validator();
        Security security = new Security();
        LibraryService libraryService = new LibraryServiceImpl(bookRepository, readerRepository, validator, security);
        LibraryView libraryView = new LibraryView(libraryService);

        libraryView.run();
    }
}
