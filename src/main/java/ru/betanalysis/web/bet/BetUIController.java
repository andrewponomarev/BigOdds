package ru.betanalysis.web.bet;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.betanalysis.View;
import ru.betanalysis.model.Bet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/profile/bets")
public class BetUIController extends AbstractBetController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bet> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Bet get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

//        Bet bet = new Bet(
//                id,
//                event,
//                0.0,
//                "123",
//                0.0,
//                0.0,
//                coefficient,
//                dateTime,
//                false
//        );

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Validated(View.Web.class) Bet bet) {
        if (bet.isNew()) {
            super.create(bet);
        } else {
            super.update(bet, bet.getId());
        }
    }


    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bet> getBetween(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
