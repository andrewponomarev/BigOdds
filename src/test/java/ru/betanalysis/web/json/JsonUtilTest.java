package ru.betanalysis.web.json;

import org.junit.jupiter.api.Test;
import ru.betanalysis.model.Bet;
import ru.betanalysis.model.User;
import ru.betanalysis.web.user.UserTestData;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.betanalysis.web.user.BetTestData.*;

class JsonUtilTest {

    @Test
    void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ADMIN_BET1);
        System.out.println(json);
        Bet bet = JsonUtil.readValue(json, Bet.class);
        assertMatch(bet, ADMIN_BET1);
    }

    @Test
    void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(BETS);
        System.out.println(json);
        List<Bet> meals = JsonUtil.readValues(json, Bet.class);
        assertMatch(meals, BETS);
    }

    @Test
    void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}