package ru.betanalysis.repository.mock;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;
import ru.betanalysis.util.BetUtil;
import ru.betanalysis.util.Util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.betanalysis.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.betanalysis.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryBetRepositoryImpl implements BetRepository {

    // Map  userId -> (betId-> bet)
    private Map<Integer, Map<Integer, Bet>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        BetUtil.BETS.forEach(bet -> save(bet,USER_ID));

        save(new Bet("Admin bet1", 0.0, "123", 0.0,
                0.0, 0.0,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), true), ADMIN_ID);
        save(new Bet("Admin bet2", 0.0, "123", 0.0,
                0.0, 0.0,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), true), ADMIN_ID);
    }


    @Override
    public Bet save(Bet bet, int userId) {
        Map<Integer, Bet> bets = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (bet.isNew()) {
            bet.setId(counter.incrementAndGet());
            bets.put(bet.getId(), bet);
            return bet;
        }
        // treat case: update, but absent in storage
        return bets.computeIfPresent(bet.getId(), (id, oldBet) -> bet);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Bet> bets = repository.get(userId);
        return bets != null && bets.remove(id) != null;
    }

    @Override
    public Bet get(int id, int userId) {
        Map<Integer, Bet> bets = repository.get(userId);
        return bets == null ? null : bets.get(id);
    }

    @Override
    public List<Bet> getAll(int userId) {
        return getAllFiltered(userId, bet -> true);
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllFiltered(userId, bet -> Util.isBetween(bet.getDateTime(), startDateTime, endDateTime));
    }

    private List<Bet> getAllFiltered(int userId, Predicate<Bet> filter) {
        Map<Integer, Bet> bets = repository.get(userId);
        return CollectionUtils.isEmpty(bets) ? Collections.emptyList() :
                bets.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Bet::getDateTime).reversed())
                        .collect(Collectors.toList());
    }

}
