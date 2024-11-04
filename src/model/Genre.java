package model;

import utils.MyArrayList;
import utils.MyList;

public enum Genre {
    ADVENTURE("adventure", "\u001B[38;5;130m", "adv"), // Терракотовый
    AUTOBIOGRAPHY("autobiography", "\u001B[38;5;137m", "auto"), // Медово-желтый
    BIOGRAPHY("biography", "\u001B[38;5;142m", "bio"), // Оливковый
    CHILDREN("children's literature", "\u001B[38;5;174m", "child", "ch"), // Светло-розовый
    CLASSIC("classic", "\u001B[38;5;180m", "clas"), // Кремовый
    COMEDY("comedy", "\u001B[38;5;185m", "com", "fun"), // Пастельный розовый
    CRIME("crime", "\u001B[38;5;124m", "cri", "detective"), // Бордовый
    DRAMA("drama", "\u001B[38;5;95m", "dr"), // Сливовый
    DYSTOPIAN("dystopian", "\u001B[38;5;100m", "dys", "dy"), // Глубокий серый
    EDUCATIONAL("educational", "\u001B[38;5;110m", "edu"), // Темно-бирюзовый
    FANTASY("fantasy", "\u001B[38;5;141m", "fant", "fan", "fa"), // Пастельно-фиолетовый
    FICTION("fiction", "\u001B[38;5;102m", "fic"), // Спокойный синий
    HISTORICAL("historical", "\u001B[38;5;178m", "hist", "hi"), // Светло-коричневый
    HORROR("horror", "\u001B[38;5;88m", "hor"), // Вишневый
    MYSTERY("mystery", "\u001B[38;5;61m", "myst", "my"), // Сине-фиолетовый
    NON_FICTION("non-fiction", "\u001B[38;5;145m", "non-fic", "no"), // Серо-голубой
    PHILOSOPHICAL_FICTION("philosophical fiction", "\u001B[38;5;136m", "phil", "phi"), // Карамельный
    POETRY("poetry", "\u001B[38;5;139m", "po"), // Лавандовый
    RELIGION("religion", "\u001B[38;5;101m", "rel"), // Спокойный пурпурный
    ROMANCE("romance", "\u001B[38;5;211m", "rom"), // Пастельно-розовый
    SCIENCE("science", "\u001B[38;5;110m", "sci"), // Темный бирюзовый
    SCIENCE_FICTION("science fiction", "\u001B[38;5;111m", "sci-fi", "sci"), // Пастельный синий
    SELF_HELP("self-help", "\u001B[38;5;151m", "self"), // Пастельно-зеленый
    SHORT_STORY("short story", "\u001B[38;5;143m", "short", "story"), // Оливковый
    TECHNICAL("technical", "\u001B[38;5;109m", "tech"), // Пастельный морской зеленый
    THRILLER("thriller", "\u001B[38;5;88m", "thr"), // Бордовый
    TRAVEL("travel", "\u001B[38;5;178m", "trav"), // Светло-коричневый
    YOUNG_ADULT("young adult", "\u001B[38;5;217m", "ya", "teen"); // Пастельный персиковый

    private final String description;
    private final String color;
    private final MyList<String> parameters;

    // Конструктор для описания, цвета и параметров
    Genre(String description, String color, String... parameters) {
        this.description = description;
        this.color = color;
        this.parameters = new MyArrayList<>();
        this.parameters.add(description.toLowerCase()); // Основное описание
        for (String param : parameters) {
            this.parameters.add(param.toLowerCase());
        }
    }

    // Метод для поиска жанра по короткому названию
    public static Genre getGenre(String genre) {
        if (genre != null && !genre.isBlank()) {
            genre = genre.toLowerCase();
            for (Genre each : Genre.values()) {
                if (each.parameters.contains(genre)) {
                    return each;
                }
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }
}
