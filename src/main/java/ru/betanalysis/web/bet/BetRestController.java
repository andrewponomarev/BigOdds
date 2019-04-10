package ru.betanalysis.web.Bet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.betanalysis.model.Bet;
import ru.betanalysis.service.BetService;
import ru.betanalysis.util.DateTimeUtil;
import ru.betanalysis.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.betanalysis.util.ValidationUtil.assureIdConsistent;
import static ru.betanalysis.util.ValidationUtil.checkNew;

@Controller
public class BetRestController {

    private static final Logger log = LoggerFactory.getLogger(BetRestController.class);

    private final BetService service;

    @Autowired
    public BetRestController(BetService service) {
        this.service = service;
    }

    public Bet get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get Bet {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete Bet {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Bet> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Bet create(Bet Bet) {
        int userId = SecurityUtil.authUserId();
        checkNew(Bet);
        log.info("create {} for user {}", Bet, userId);
        return service.create(Bet, userId);
    }

    public void update(Bet Bet, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(Bet, id);
        log.info("update {} for user {}", Bet, userId);
        service.update(Bet, userId);
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
