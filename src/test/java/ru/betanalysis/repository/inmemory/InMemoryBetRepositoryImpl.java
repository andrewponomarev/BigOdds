package ru.betanalysis.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;
import ru.betanalysis.util.Util;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryBetRepositoryImpl implements BetRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryBetRepositoryImpl.class);

    // Map  userId -> (betId-> bet)
    private Map<Integer, InMemoryBaseRepositoryImpl<Bet>> usersBetsMap = new ConcurrentHashMap<>();

    @Override
    public Bet save(Bet bet, int userId) {
        Objects.requireNonNull(bet, "bet must not be null");
        var bets = usersBetsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepositoryImpl<>());
        return bets.save(bet);
    }

    @Override
    public boolean delete(int id, int userId) {
        var bets = usersBetsMap.get(userId);
        return bets != null && bets.delete(id);
    }

    @Override
    public Bet get(int id, int userId) {
        var bets = usersBetsMap.get(userId);
        return bets == null ? null : bets.get(id);
    }

    @Override
    public List<Bet> getAll(int userId) {
        return getAllFiltered(userId, bet -> true);
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        return getAllFiltered(userId, bet -> Util.isBetween(bet.getDateTime(), startDateTime, endDateTime));
    }

    private List<Bet> getAllFiltered(int userId, Predicate<Bet> filter) {
        var bets = usersBetsMap.get(userId);
        return bets == null ? Collections.emptyList() :
                bets.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Bet::getDateTime).reversed())
                        .collect(Collectors.toList());
    }

}
