package ru.betanalysis.web.user;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.betanalysis.model.Role;
import ru.betanalysis.model.User;
import ru.betanalysis.web.json.JsonUtil;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.betanalysis.model.AbstractBaseEntity.START_SEQ;
import static ru.betanalysis.web.TestUtil.readFromJsonMvcResult;
import static ru.betanalysis.web.TestUtil.readListFromJsonMvcResult;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "user", "email@mail.com", "password",
            "secondName", "firstName", "phoneNumber",
            new Date(), Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin", "admin@mail.com", "password",
            "secondName", "firstName", "phoneNumber",
            new Date(), Role.ROLE_ADMIN, Role.ROLE_USER);

    public static String[] IGNORING_FIELDS = {"registered","birthDay", "bets", "password"};

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, IGNORING_FIELDS);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(IGNORING_FIELDS).isEqualTo(expected);
    }


    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
