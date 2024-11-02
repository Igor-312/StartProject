package view;

/**
 * Интерфейс с ANSI-кодами цветов для консольного вывода.
 */
public interface Color {
    String COLOR_BLACK = "\u001B[0m";
    String COLOR_RESET = "\u001B[0m";
    String COLOR_RED = "\u001B[31m";
    String COLOR_GREEN = "\u001B[32m";
    String COLOR_YELLOW = "\u001B[33m";
    String COLOR_BLUE = "\u001B[34m";
    String COLOR_PURPLE = "\u001B[35m";
    String COLOR_CYAN = "\u001B[36m";
    String COLOR_WHITE = "\u001B[37m";
}
