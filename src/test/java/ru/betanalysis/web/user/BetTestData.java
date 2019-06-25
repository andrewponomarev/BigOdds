package ru.betanalysis.web.user;

import ru.betanalysis.model.Bet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.betanalysis.model.AbstractBaseEntity.START_SEQ;

public class BetTestData {

    public static final int BET1_ID = START_SEQ + 2;
    public static final int ADMIN_BET_ID = START_SEQ + 8;

    public static final Bet BET1 = new Bet("Россия - Англия", 123, "123", 123, 123, 1.23,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false);
    public static final Bet BET2 = new Bet( "Россия - Парагвай", 321, "321", 321, 321, 3.21,
                     LocalDateTime.of(2015, Month.MAY, 30, 15, 0), false);
    public static final Bet BET3 = new Bet("Парагвай - Англия", 222, "222", 222, 222, 2.22,
                    LocalDateTime.of(2015, Month.MAY, 30, 20, 0), false);
    public static final Bet ADMIN_BET1 = new Bet("Admin bet1", 0.0, "'123'", 0.0, 0.0, 0.0,
            LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false);
    public static final Bet ADMIN_BET2 = new Bet("Admin bet2", 0.0, "'123'", 0.0, 0.0, 0.0,
            LocalDateTime.of(2015, Month.MAY, 30, 20, 0), false);


    public static final List<Bet> BETS = Arrays.asList(BET1, BET2, BET3);

    public static Bet getCreated() {
       return new Bet(null, "Созданная ставка", 432, "432", 432, 432, 4.32,
               of(2015, Month.JUNE , 1, 18, 0), false);
    }

    public static Bet getUpdated() {
        return new Bet(BET1_ID, "Обновленный ставка", BET1.getValue(), BET1.getCurrency(), BET1.getReturnSum(),
                BET1.getNetProfit(), BET1.getCoefficient(), BET1.getDateTime(), false);
    }

    public static void assertMatch(Bet actual, Bet expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Bet> actual, Bet... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Bet> actual, Iterable<Bet> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
