package ru.betanalysis.util;

import ru.betanalysis.model.Bet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class BetUtil {

    public static List<Bet> BETS = Arrays.asList(
            new Bet(null, "Россия - Англия", 123, "123", 123,
                    123, 1.23,
                    LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false),
            new Bet(null, "Россия - Парагвай", 321, "321", 321,
                    321, 3.21,
                    LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false),
            new Bet(null, "Парагвай - Англия", 222, "222", 222,
                    222, 2.22,
                    LocalDateTime.of(2015, Month.MAY, 30, 10, 0), false)
    );

}
