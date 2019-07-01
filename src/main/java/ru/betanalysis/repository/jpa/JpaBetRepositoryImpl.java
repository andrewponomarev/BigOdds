package ru.betanalysis.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.betanalysis.model.Bet;
import ru.betanalysis.model.User;
import ru.betanalysis.repository.BetRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaBetRepositoryImpl implements BetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Bet save(Bet bet, int userId) {
        if (!bet.isNew() && get(bet.getId(), userId) == null) {
            return null;
        }
        bet.setUser(em.getReference(User.class, userId));
        if (bet.isNew()) {
            em.persist(bet);
            return bet;
        } else {
            return em.merge(bet);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Bet.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Bet get(int id, int userId) {
        Bet bet = em.find(Bet.class, id);
        return bet != null && bet.getUser().getId() == userId ? bet : null;
    }

    @Override
    public List<Bet> getAll(int userId) {
        return em.createNamedQuery(Bet.ALL_SORTED, Bet.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Bet> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Bet.BETWEEN, Bet.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
