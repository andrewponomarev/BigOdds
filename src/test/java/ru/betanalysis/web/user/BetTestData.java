package ru.betanalysis.web.user;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.betanalysis.model.Bet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.betanalysis.model.AbstractBaseEntity.START_SEQ;
import static ru.betanalysis.web.TestUtil.readListFromJsonMvcResult;

public class BetTestData {

    public static final int BET1_ID = START_SEQ + 2;
    public static final int ADMIN_BET_ID = START_SEQ + 5;

    public static final Bet BET1 = new Bet(BET1_ID,"Россия - Англия", 123, "123", 123, 123, 1.23,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false);
    public static final Bet BET2 = new Bet(BET1_ID+1, "Россия - Парагвай", 321, "321", 321, 321, 3.21,
                     LocalDateTime.of(2015, Month.MAY, 30, 15, 0), false);
    public static final Bet BET3 = new Bet(BET1_ID + 2,"Парагвай - Англия", 222, "222", 222, 222, 2.22,
                    LocalDateTime.of(2015, Month.MAY, 30, 20, 0), false);
    public static final Bet ADMIN_BET1 = new Bet(ADMIN_BET_ID,"Admin bet 1", 0.0, "123", 0.0, 0.0, 0.0,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), true);
    public static final Bet ADMIN_BET2 = new Bet(ADMIN_BET_ID + 1,"Admin bet 2", 0.0, "123", 0.0, 0.0, 0.0,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0), true);


    public static final List<Bet> BETS = List.of(BET3, BET2, BET1);

    public static Bet getCreated() {
       return new Bet(null, "Созданная ставка", 432, "432", 432, 432, 4.32,
               of(2015, Month.JUNE , 1, 18, 0), false);
    }

    public static Bet getUpdated() {
        return new Bet(BET1_ID, "Обновленный ставка", BET1.getValue(), BET1.getCurrency(), BET1.getReturnSum(),
                BET1.getNetProfit(), BET1.getCoefficient(), BET1.getDateTime(), false);
    }

    public static void assertMatch(Bet actual, Bet expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Bet> actual, Bet... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Bet> actual, Iterable<Bet> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Iterable<Bet> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Bet.class), expected);
    }

    public static ResultMatcher contentJson(Bet... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Bet.class), List.of(expected));
    }

}
