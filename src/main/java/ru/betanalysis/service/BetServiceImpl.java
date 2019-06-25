package ru.betanalysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.betanalysis.model.Bet;
import ru.betanalysis.repository.BetRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.betanalysis.util.ValidationUtil.checkNotFoundWithId;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository repository;

    @Autowired
    public BetServiceImpl(BetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bet get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Bet> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Bet> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(Bet bet, int userId) {
        Assert.notNull(bet, "bet must not be null");
        checkNotFoundWithId(repository.save(bet, userId), bet.getId());
    }

    @Override
    public Bet create(Bet bet, int userId) {
        Assert.notNull(bet, "bet must not be null");
        return repository.save(bet, userId);
    }
}
