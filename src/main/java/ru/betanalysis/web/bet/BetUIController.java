package ru.betanalysis.web.bet;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.betanalysis.model.Bet;
import ru.betanalysis.util.ValidationUtil;

import javax.validation.Valid;
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
    public ResponseEntity<String> createOrUpdate(@Valid Bet bet, BindingResult result) {
        if (result.hasErrors()) {
            // TODO change to exception handler
            return ValidationUtil.getErrorResponse(result);
        }
        if (bet.isNew()) {
            super.create(bet);
        } else {
            super.update(bet, bet.getId());
        }
        return ResponseEntity.ok().build();
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
