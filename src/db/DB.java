package db;

import model.Book;
import model.Genre;
import model.Reader;
import model.Role;
import utils.MyArrayList;
import utils.MyList;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 02-11-2024
 */
/*

 */
public class DB {

    private final MyList<Reader> readers;
    private final MyList<Book> books;
    private int readerId;
    private int bookId;

    public DB() {
        this.readers = initReaders();
        this.books = initBooks();
    }

    private MyList<Reader> initReaders() {
        MyList<Reader> list = new MyArrayList<>();
        // Главные администраторы
        list.add(new Reader(1, "Bogdan", "bogdan@example.com", "123", Role.ADMIN));
        list.add(new Reader(2, "Igor", "igor@example.com", "123", Role.ADMIN));
        list.add(new Reader(3, "Elena", "elena@example.com", "123", Role.ADMIN));
        list.add(new Reader(4, "Svitlana", "svitlana@example.com", "123", Role.ADMIN));

        // зарегистрированные пользователи библиотеки
        list.add(new Reader(5, "admin", "admin@example.com", "123", Role.ADMIN));
        list.add(new Reader(6, "Ivan", "ivan@example.com", "123", Role.READER));
        list.add(new Reader(7, "Maria", "maria@example.com", "123", Role.READER));
        readerId = list.size();

        return list;
    }

    private MyList<Book> initBooks() {
        MyList<Book> list = new MyArrayList<>();

        list.add(new Book(1, "Капитанская дочка", "А.С. Пушкин", 1836, 300, "ru", Book.createGenres(Genre.HISTORICAL, Genre.CLASSIC))); // Русский
        list.add(new Book(2, "Война и мир", "Л.Н. Толстой", 1869, 1225, "ru", Book.createGenres(Genre.HISTORICAL, Genre.PHILOSOPHICAL_FICTION))); // Русский
        list.add(new Book(3, "Анна Каренина", "Л.Н. Толстой", 1878, 864, "ru", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Русский
        list.add(new Book(4, "Мастер и Маргарита", "М.А. Булгаков", 1967, 480, "ru", Book.createGenres(Genre.MYSTERY, Genre.CLASSIC))); // Русский
        list.add(new Book(5, "Тарас Бульба", "Н.В. Гоголь", 1835, 360, "uk", Book.createGenres(Genre.HISTORICAL, Genre.ADVENTURE))); // Украинский/Русский

        list.add(new Book(6, "Тіні забутих предків", "М. Коцюбинський", 1911, 320, "uk", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Украинский
        list.add(new Book(7, "Маруся", "Г. Квітка-Основ'яненко", 1834, 245, "uk", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Украинский
        list.add(new Book(8, "Die Verwandlung", "Franz Kafka", 1915, 74, "de", Book.createGenres(Genre.CLASSIC, Genre.MYSTERY))); // Немецкий
        list.add(new Book(9, "Der Steppenwolf", "Hermann Hesse", 1927, 237, "de", Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC))); // Немецкий
        list.add(new Book(10, "Faust", "J.W. von Goethe", 1808, 340, "de", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Немецкий

        list.add(new Book(11, "To Kill a Mockingbird", "Harper Lee", 1960, 324, "en", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Английский
        list.add(new Book(12, "1984", "George Orwell", 1949, 328, "en", Book.createGenres(Genre.DYSTOPIAN, Genre.SCIENCE_FICTION))); // Английский
        list.add(new Book(13, "The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "en", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Английский
        list.add(new Book(14, "Harry Potter", "J.K. Rowling", 1997, 309, "en", Book.createGenres(Genre.FANTASY, Genre.ADVENTURE))); // Английский
        list.add(new Book(15, "Pride and Prejudice", "Jane Austen", 1813, 279, "en", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Английский

        list.add(new Book(16, "Clean Code", "Robert C. Martin", 2008, 464, "en", Book.createGenres(Genre.TECHNICAL, Genre.NON_FICTION))); // IT
        list.add(new Book(17, "The Pragmatic Programmer", "A. Hunt & D. Thomas", 1999, 352, "en", Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL))); // IT
        list.add(new Book(18, "Java Reference", "Herbert Schildt", 2018, 1248, "en", Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL))); // IT
        list.add(new Book(19, "Algorithms", "Thomas Cormen", 2009, 1312, "en", Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL))); // IT
        list.add(new Book(20, "Design Patterns", "E. Gamma & R. Helm", 1994, 395, "en", Book.createGenres(Genre.TECHNICAL, Genre.EDUCATIONAL))); // IT

        list.add(new Book(21, "Moby-Dick", "Herman Melville", 1851, 635, "en", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Английский
        list.add(new Book(22, "The Odyssey", "Homer", -800, 374, "en", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Английский
        list.add(new Book(23, "Brothers Karamazov", "F. Dostoevsky", 1880, 796, "ru/en", Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC))); // Русский/Английский

        list.add(new Book(24, "Siddhartha", "Hermann Hesse", 1922, 152, "de", Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION))); // Немецкий
        list.add(new Book(25, "The Catcher in the Rye", "J.D. Salinger", 1951, 277, "en", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Английский
        list.add(new Book(26, "Animal Farm", "George Orwell", 1945, 112, "en", Book.createGenres(Genre.DYSTOPIAN, Genre.CLASSIC))); // Английский
        list.add(new Book(27, "Les Misérables", "Victor Hugo", 1862, 1463, "fr/en", Book.createGenres(Genre.HISTORICAL, Genre.DRAMA))); // Французский/Английский
        list.add(new Book(28, "Margarita", "M. Bulgakov", 1967, 480, "ru", Book.createGenres(Genre.MYSTERY))); // Русский

        list.add(new Book(29, "The Iliad", "Homer", -750, 500, "en", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Английский
        list.add(new Book(30, "Don Quixote", "Miguel de Cervantes", 1605, 863, "es", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Испанский
        list.add(new Book(31, "Jane Eyre", "Charlotte Brontë", 1847, 500, "en", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Английский
        list.add(new Book(32, "Fahrenheit 451", "Ray Bradbury", 1953, 194, "en", Book.createGenres(Genre.DYSTOPIAN, Genre.SCIENCE_FICTION))); // Английский
        list.add(new Book(33, "Ulysses", "James Joyce", 1922, 730, "en", Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION))); // Английский
        list.add(new Book(34, "Aeneid", "Virgil", -29, 442, "la", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Латинский
        list.add(new Book(35, "Inferno", "Dante Alighieri", 1320, 798, "it", Book.createGenres(Genre.CLASSIC, Genre.POETRY))); // Итальянский
        list.add(new Book(36, "Meditations", "Marcus Aurelius", 180, 254, "la", Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC))); // Латинский
        list.add(new Book(37, "The Stranger", "Albert Camus", 1942, 123, "fr", Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.CLASSIC))); // Французский
        list.add(new Book(38, "The Trial", "Franz Kafka", 1925, 304, "de", Book.createGenres(Genre.CLASSIC, Genre.MYSTERY))); // Немецкий

        list.add(new Book(39, "The Hobbit", "J.R.R. Tolkien", 1937, 310, "en", Book.createGenres(Genre.FANTASY, Genre.ADVENTURE))); // Английский
        list.add(new Book(40, "Hamlet", "William Shakespeare", 1603, 342, "en", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Английский
        list.add(new Book(41, "Emma", "Jane Austen", 1815, 474, "en", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Английский
        list.add(new Book(42, "The Metamorphosis", "Franz Kafka", 1915, 64, "de", Book.createGenres(Genre.CLASSIC, Genre.MYSTERY))); // Немецкий
        list.add(new Book(43, "Heart of Darkness", "Joseph Conrad", 1899, 72, "en", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Английский
        list.add(new Book(44, "Anna Karenina", "Leo Tolstoy", 1877, 864, "ru", Book.createGenres(Genre.CLASSIC, Genre.ROMANCE))); // Русский
        list.add(new Book(45, "The Art of War", "Sun Tzu", -500, 256, "zh", Book.createGenres(Genre.CLASSIC, Genre.NON_FICTION))); // Китайский
        list.add(new Book(46, "Frankenstein", "Mary Shelley", 1818, 280, "en", Book.createGenres(Genre.CLASSIC, Genre.SCIENCE_FICTION))); // Английский
        list.add(new Book(47, "A Tale of Two Cities", "Charles Dickens", 1859, 489, "en", Book.createGenres(Genre.HISTORICAL, Genre.CLASSIC))); // Английский
        list.add(new Book(48, "The Little Prince", "Antoine de Saint-Exupéry", 1943, 96, "fr", Book.createGenres(Genre.FANTASY, Genre.CLASSIC))); // Французский

        list.add(new Book(49, "Macbeth", "William Shakespeare", 1606, 159, "en", Book.createGenres(Genre.CLASSIC, Genre.DRAMA))); // Английский
        list.add(new Book(50, "Leaves of Grass", "Walt Whitman", 1855, 567, "en", Book.createGenres(Genre.POETRY, Genre.CLASSIC))); // Английский
        list.add(new Book(51, "Lolita", "Vladimir Nabokov", 1955, 336, "en", Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION))); // Английский/Русский
        list.add(new Book(52, "Journey to the Center", "Jules Verne", 1864, 183, "fr", Book.createGenres(Genre.ADVENTURE, Genre.SCIENCE_FICTION))); // Французский
        list.add(new Book(53, "The Call of the Wild", "Jack London", 1903, 232, "en", Book.createGenres(Genre.ADVENTURE, Genre.CLASSIC))); // Английский
        list.add(new Book(54, "Beowulf", "Unknown", 700, 128, "en", Book.createGenres(Genre.CLASSIC, Genre.ADVENTURE))); // Английский
        list.add(new Book(55, "The Count of Monte Cristo", "Alexandre Dumas", 1844, 1276, "fr", Book.createGenres(Genre.ADVENTURE, Genre.CLASSIC))); // Французский
        list.add(new Book(56, "The Prince", "Niccolò Machiavelli", 1532, 128, "it", Book.createGenres(Genre.PHILOSOPHICAL_FICTION, Genre.NON_FICTION))); // Итальянский
        list.add(new Book(57, "The Jungle Book", "Rudyard Kipling", 1894, 276, "en", Book.createGenres(Genre.CHILDREN, Genre.ADVENTURE))); // Английский
        list.add(new Book(58, "Crime and Punishment", "Fyodor Dostoevsky", 1866, 671, "ru", Book.createGenres(Genre.CLASSIC, Genre.PHILOSOPHICAL_FICTION))); // Русский

        bookId = list.size();

        return list;
    }


    public MyList<Reader> getReaders() {
        return readers;
    }

    public MyList<Book> getBooks() {
        return books;
    }

    public int getReaderId() {
        return readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
