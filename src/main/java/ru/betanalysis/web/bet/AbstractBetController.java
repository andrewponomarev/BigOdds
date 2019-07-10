package ru.betanalysis.web.bet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.betanalysis.model.Bet;
import ru.betanalysis.service.BetService;
import ru.betanalysis.util.DateTimeUtil;
import ru.betanalysis.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.betanalysis.util.ValidationUtil.assureIdConsistent;
import static ru.betanalysis.util.ValidationUtil.checkNew;

public abstract class AbstractBetController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BetService service;

    public Bet get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get bet {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete bet {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Bet> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Bet create(Bet bet) {
        int userId = SecurityUtil.authUserId();
        checkNew(bet);
        log.info("create {} for user {}", bet, userId);
        return service.create(bet, userId);
    }

    public void update(Bet bet, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(bet, id);
        log.info("update {} for user {}", bet, userId);
        service.update(bet, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<Bet> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Bet> betsDateFiltered = service.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId);

        return betsDateFiltered;
    }

}