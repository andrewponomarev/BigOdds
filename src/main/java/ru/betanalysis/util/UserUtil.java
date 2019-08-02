package ru.betanalysis.util;

import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.to.UserTo;

public class UserUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(),
                null, null, null, null,  Role.ROLE_USER);
    }

}