package ru.betanalysis.repository;

import ru.betanalysis.model.Bet;

import java.util.Collection;

public interface BetRepository {

    Bet save(Bet meal);

    void delete(int id);

    Bet get(int id);

    Collection<Bet> getAll();
}

