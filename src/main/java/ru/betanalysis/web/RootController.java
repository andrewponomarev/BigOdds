package ru.betanalysis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.betanalysis.service.BetService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private BetService betService;

    @GetMapping("/")
    public String root() {
        return "index";
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

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:bets";
    }
}
