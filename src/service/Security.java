package service;

import model.Reader;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 31-10-2024
 */
/*

 */
public class Security {

    public String authenticateUser(Reader reader, String password) {
        if (reader == null) {
            return "Пользователь не найден.";
        }
        if (isPasswordCorrect(reader, password)) {
            return "Аутентификация успешна!";
        } else {
            return "Неверный пароль.";
        }
    }

    public boolean isPasswordCorrect(Reader reader, String password) {
        return reader.getPassword().equals(password);
    }
}
