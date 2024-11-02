package model;

import utils.MyArrayList;
import utils.MyList;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 02-11-2024
 */
public enum Genre {
    HISTORICAL("historical", "hist", "hi"),
    CLASSIC("classic", "clas"),
    PHILOSOPHICAL_FICTION("philosophical fiction", "phil", "phi"),
    MYSTERY("mystery", "myst", "my"),
    DRAMA("drama", "dr"),
    ROMANCE("romance", "rom"),
    DYSTOPIAN("dystopian", "dys", "dy"),
    SCIENCE_FICTION("science fiction", "sci-fi", "sci"),
    ADVENTURE("adventure", "adv"),
    FANTASY("fantasy", "fant", "fan", "fa"),
    BIOGRAPHY("biography", "bio"),
    AUTOBIOGRAPHY("autobiography", "auto"),
    THRILLER("thriller", "thr"),
    HORROR("horror", "hor"),
    SELF_HELP("self-help", "self"),
    POETRY("poetry", "po"),
    CHILDREN("children's literature", "child", "ch"),
    NON_FICTION("non-fiction", "non-fic", "no"),
    RELIGION("religion", "rel"),
    TECHNICAL("technical", "tech"),
    EDUCATIONAL("educational", "edu");

    private final String description;
    private final MyList<String> parameters;

    // Конструктор для описания с параметрами
    Genre(String description, String... parameters) {
        this.description = description;
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
}
