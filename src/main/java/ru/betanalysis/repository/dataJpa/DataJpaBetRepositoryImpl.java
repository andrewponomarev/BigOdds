package ru.betanalysis.repository.dataJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaBetRepositoryImpl implements BetRepository {

    @Autowired
    private CrudBetRepository crudRepository;

    @Override
    public Bet save(Bet meal, int userId) {
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