package ru.betanalysis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:bets";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/bets")
    public String bets() {
        return "bets";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
