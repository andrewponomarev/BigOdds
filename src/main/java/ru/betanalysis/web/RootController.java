package ru.betanalysis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.betanalysis.service.BetService;

@Controller
public class RootController {

    @Autowired
    private BetService betService;

    @GetMapping("/")
    public String root() {
        return "redirect:bets";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/bets")
    public String bets(Model model) {
        model.addAttribute("bets",
                betService.getAll(SecurityUtil.authUserId()));
        return "bets";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
