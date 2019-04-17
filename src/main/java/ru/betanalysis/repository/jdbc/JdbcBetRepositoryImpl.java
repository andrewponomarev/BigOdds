package ru.betanalysis.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcBetRepositoryImpl implements BetRepository {

    @Override
    public Bet save(Bet Bet, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Bet get(int id, int userId) {
        return null;
    }

    @Override
    public List<Bet> getAll(int userId) {
        return null;
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
