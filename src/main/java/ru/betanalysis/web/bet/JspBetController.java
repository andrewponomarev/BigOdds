package ru.betanalysis.web.bet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.betanalysis.model.Bet;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.betanalysis.util.DateTimeUtil.parseLocalDate;
import static ru.betanalysis.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/bets")
public class JspBetController extends AbstractBetController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/bets";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("bet", super.get(getId(request)));
        return "betForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("bet", new Bet("", 0.0, "123", 0.0,
                0.0, 0.0,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), true) );
        return "betForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Bet bet = new Bet(
                request.getParameter("event"),
                0.0,
                "123",
                0.0,
                0.0,
                Double.valueOf(request.getParameter("coefficient")),
                LocalDateTime.parse(request.getParameter("dateTime")),
                false
        );

        if (request.getParameter("id").isEmpty()) {
            super.create(bet);
        } else {
            super.update(bet, getId(request));
        }
        return "redirect:/bets";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("bets", super.getBetween(startDate, startTime, endDate, endTime));
        return "bets";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
