package ru.betanalysis.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.betanalysis.Profiles;
import ru.betanalysis.model.Bet;
import ru.betanalysis.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;
import static ru.betanalysis.web.user.UserTestData.USER_ID;

//import org.slf4j.bridge.SLF4JBridgeHandler;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.ACTIVE_DB)
public class BetServiceTest {

    private static final Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    static {
    //    SLF4JBridgeHandler.install();
    }

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Autowired
    private BetService service;

    @Test
    public void delete() throws Exception {
        service.delete(BET1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), BET3, BET2);
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(BET1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Bet newBet = getCreated();
        Bet created = service.create(newBet, USER_ID);
        newBet.setId(created.getId());
        assertMatch(newBet, created);
        assertMatch(service.getAll(USER_ID), newBet, BET3, BET2, BET1);
    }

    @Test
    public void get() throws Exception {
        Bet actual = service.get(ADMIN_BET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_BET1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(BET1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Bet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(BET1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + BET1_ID);
        service.update(BET1, ADMIN_ID);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), BETS);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), BET3, BET2, BET1);
    }
}
