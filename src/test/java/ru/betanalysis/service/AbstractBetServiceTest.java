package ru.betanalysis.service;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.betanalysis.model.Bet;
import ru.betanalysis.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.betanalysis.web.user.BetTestData.*;
import static ru.betanalysis.web.user.UserTestData.ADMIN_ID;
import static ru.betanalysis.web.user.UserTestData.USER_ID;


public abstract class AbstractBetServiceTest extends AbstractServiceTest {

    @Autowired
    protected BetService service;

    @Test
    void delete() throws Exception {
        service.delete(BET1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), BET3, BET2);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
            service.delete(BET1_ID, 1));
    }

    @Test
    void create() throws Exception {
        Bet newBet = getCreated();
        Bet created = service.create(newBet, USER_ID);
        newBet.setId(created.getId());
        assertMatch(newBet, created);
        assertMatch(service.getAll(USER_ID), newBet, BET3, BET2, BET1);
    }

    @Test
    void get() throws Exception {
        Bet actual = service.get(ADMIN_BET_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_BET1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
            service.get(BET1_ID, ADMIN_ID));
    }

    @Test
    void update() throws Exception {
        Bet updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(BET1_ID, USER_ID), updated);
    }

    @Test
    void updateNotFound() throws Exception {
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(BET1, ADMIN_ID));
        assertEquals(e.getMessage(), "Not found entity with id=" + BET1_ID);
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), BETS);
    }

    @Test
    void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), BET3, BET2, BET1);
    }

    //todo: hibernate не валидирует ставку
    // org.hibernate.cfg.beanvalidation.BeanValidationEventListener.validate()
    @Test
    void testValidation() throws Exception {
        Assumptions.assumeTrue(isJpaBased(), "Validation not supported (JPA only)");
//        validateRootCause(() -> service.create(
//                new Bet(10000000,"  ", 123, "123", 123, 123, 1.23,
//                LocalDateTime.of(2015, Month.MAY, 30, 23, 59), false)
//                , USER_ID), ConstraintViolationException.class);
//        validateRootCause(() -> service.create(
//                new Bet(10000000,"Россия - Англия", 123, "  ", 123, 123, 1.23,
//                        LocalDateTime.of(2015, Month.MAY, 30, 23, 59), false)
//                , USER_ID), ConstraintViolationException.class);
//        validateRootCause(() -> service.create(
//                new Bet(10000000,"Россия - Англия", 123, "123", 123, 123, 1.23,
//                        null, false)
//                , USER_ID), ConstraintViolationException.class);
    }
}
