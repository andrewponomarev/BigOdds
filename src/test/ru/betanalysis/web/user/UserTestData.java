package ru.betanalysis.web.user;

import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;

import java.time.LocalDateTime;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "user", "email@mail.ru", "password",
            "secondName", "firstName", "phoneNumber",
            LocalDateTime.now(), Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@mail.ru", "password",
            "secondName", "firstName", "phoneNumber",
            LocalDateTime.now(), Role.ROLE_ADMIN);
}
