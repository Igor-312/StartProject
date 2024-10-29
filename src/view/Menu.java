package view;

import model.User;
import service.MainService;

import java.util.Scanner;

public class Menu {

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println(Color.COLOR_GREEN + "Добро пожаловать в меню" + Color.COLOR_RESET);
            System.out.println("1. Меню автомобилей");
            System.out.println("2. Меню пользователей");
            System.out.println("3. Меню администратора");
            System.out.println("0. Выход из системы");
            System.out.println(Color.COLOR_YELLOW + "\nВведите пункт меню:" + Color.COLOR_RESET);
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                System.out.println("До свидания!");
                System.exit(0);
            }

            showSubMenu(choice);

        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                // Todo
                // showCarMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                // Todo
                // showAdminMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор\n");
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println(Color.COLOR_GREEN + "Меню пользователя" + Color.COLOR_RESET);
            System.out.println("1. Вход в систему");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Logout");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println(Color.COLOR_BLUE + "\n Сделайте выбор пункта меню" + Color.COLOR_RESET);
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;
            ;

            handleUserMenuChoice(input);

        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                // Авторизация
                //Todo написать авторизацию
                System.out.println("Метод в разработке. Приходите завтра");
                waitRead();
                break;
            case 2:
                //Регистрация
                System.out.println("Регистрация нового пользователя");
                System.out.println("Введите email: ");
                String email = scanner.nextLine();

                System.out.println("Введите пароль");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null) {
                    System.out.println("Вы успешно зарегистрировались в системе");
                } else {
                    System.out.println(Color.COLOR_RED + "Регистрация провалена!" + Color.COLOR_RESET);
                }

                waitRead();

                break;
            case 3:
                //logout
                service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;
            default:
                System.out.println("\nНе верный ввод");
        }
    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }


}
