package ru.betanalysis.repository;

import ru.betanalysis.model.Bet;
import ru.betanalysis.util.BetUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBetRepositoryImpl implements BetRepository {

    private Map<Integer, Bet> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        BetUtil.BETS.forEach(this::save);
    }


    @Override
    public Bet save(Bet bet) {
        if (bet.isNew()) {
            bet.setId(counter.incrementAndGet());
            repository.put(bet.getId(), bet);
            return bet;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(bet.getId(), (id, oldBet) -> bet);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Bet get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Bet> getAll() {
        return repository.values();
    }

}
