package ru.betanalysis.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;
import ru.betanalysis.repository.mock.InMemoryBetRepositoryImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class BetServlet extends HttpServlet {
    
    private static final Logger log = LoggerFactory.getLogger(BetServlet.class);

    private BetRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryBetRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Bet bet = new Bet(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("event"),
                0.0,
               "123",
                0.0,
                0.0,
                Double.valueOf(request.getParameter("coefficient")),
                LocalDateTime.parse(request.getParameter("dateTime")),
                false
                );

        log.info(bet.isNew() ? "Create {}" : "Update {}", bet);
        repository.save(bet, SecurityUtil.authUserId());
        response.sendRedirect("bets");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id, SecurityUtil.authUserId());
                response.sendRedirect("bets");
                break;
            case "create":
            case "update":
                final Bet bet = "create".equals(action) ?
                        new Bet("", 0.0, "123", 0.0,
                                0.0, 0.0,
                                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), true) :
                        repository.get(getId(request), SecurityUtil.authUserId());
                request.setAttribute("bet", bet);
                request.getRequestDispatcher("/betForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("bets", repository.getAll(SecurityUtil.authUserId()));
                request.getRequestDispatcher("/bets.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
