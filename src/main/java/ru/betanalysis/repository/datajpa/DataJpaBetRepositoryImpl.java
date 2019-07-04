package ru.betanalysis.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.betanalysis.model.Bet;
import ru.betanalysis.model.User;
import ru.betanalysis.repository.BetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaBetRepositoryImpl implements BetRepository {

    private static final Sort SORT_DATETIME = new Sort(Sort.Direction.DESC,"dateTime");

    @Autowired
    private CrudBetRepository crudBetRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Bet save(Bet bet, int userId) {
        if (!bet.isNew() && get(bet.getId(), userId) == null) {
            return null;
        }
        User user = crudUserRepository.getOne(userId);
        bet.setUser(user);
        return crudBetRepository.save(bet);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudBetRepository.delete(id, userId) != 0;
    }

    @Override
    public Bet get(int id, int userId) {
        Optional<Bet> bet = crudBetRepository.findById(id);
        return bet.filter(b -> b.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Bet> getAll(int userId) {
        return crudBetRepository.getAll(userId);
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudBetRepository.getBetween(startDate, endDate, userId);
    }
}