package ru.betanalysis.repository;

import ru.betanalysis.model.Bet;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface BetRepository {

    // null if updated bet do not belong to userId
    Bet save(Bet meal, int userId);

    // false if bet do not belong to userId
    boolean delete(int id, int userId);

    // null if bet do not belong to userId
    Bet get(int id, int userId);

    // ORDERED dateTime desc
    List<Bet> getAll(int userId);

    // ORDERED dateTime desc
    List<Bet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

