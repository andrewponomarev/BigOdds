package ru.betanalysis.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.betanalysis.model.Bet;
import ru.betanalysis.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;
import static ru.betanalysis.web.user.UserTestData.USER_ID;


public class AbstractBetServiceTest extends AbstractServiceTest {

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
