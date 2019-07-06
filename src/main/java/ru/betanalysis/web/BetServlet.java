package ru.betanalysis.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.betanalysis.model.Bet;
import ru.betanalysis.web.bet.BetRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.betanalysis.util.DateTimeUtil.parseLocalDate;
import static ru.betanalysis.util.DateTimeUtil.parseLocalTime;

public class BetServlet extends HttpServlet {

    private BetRestController betController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        betController = springContext.getBean(BetRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
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
                betController.create(bet);
            } else {
                betController.update(bet, getId(request));
            }
            response.sendRedirect("bets");

        } else if ("filter".equals(action)) {
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            request.setAttribute("bets", betController.getBetween(startDate, startTime, endDate, endTime));
            request.getRequestDispatcher("/bets.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                betController.delete(id);
                response.sendRedirect("bets");
                break;
            case "create":
            case "update":
                final Bet bet = "create".equals(action) ?
                        new Bet("", 0.0, "123", 0.0,
                                0.0, 0.0,
                                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), true) :
                        betController.get(getId(request));
                request.setAttribute("bet", bet);
                request.getRequestDispatcher("/betForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("bets", betController.getAll());
                request.getRequestDispatcher("/bets.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
