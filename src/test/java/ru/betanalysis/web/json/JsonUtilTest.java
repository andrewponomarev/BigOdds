package ru.betanalysis.web.json;

import org.junit.jupiter.api.Test;
import ru.betanalysis.model.Bet;

import java.util.List;

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
}