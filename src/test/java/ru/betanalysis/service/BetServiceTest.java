package ru.betanalysis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.betanalysis.model.Bet;
import ru.betanalysis.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;
import static ru.betanalysis.web.user.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class BetServiceTest {

    static {
    //    SLF4JBridgeHandler.install();
    }

    @Autowired
    private BetService service;

    @Test
    public void delete() throws Exception {
        service.delete(BET1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), BET2, BET3);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(BET1_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Bet newMeal = getCreated();
        Bet created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(newMeal, created);
        assertMatch(service.getAll(USER_ID), newMeal, BET1, BET2, BET3);
    }

    @Test
    public void get() throws Exception {
        Bet actual = service.get(ADMIN_BET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_BET1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(BET1_ID, ADMIN_ID);
    }

    @Test
    public void update() throws Exception {
        Bet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(BET1_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
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
