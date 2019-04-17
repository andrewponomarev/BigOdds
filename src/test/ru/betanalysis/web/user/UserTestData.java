package ru.betanalysis.web.user;

import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.betanalysis.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "user", "email@mail.ru", "password",
            "secondName", "firstName", "phoneNumber",
            LocalDateTime.now(), Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@mail.ru", "password",
            "secondName", "firstName", "phoneNumber",
            LocalDateTime.now(), Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
