package ru.betanalysis.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.betanalysis.model.Bet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudBetRepository extends JpaRepository<Bet, Integer> {

    @Override
    @Transactional
    Bet save(Bet bet);

    @Transactional
    @Modifying
    @Query("DELETE FROM Bet b WHERE b.id=:id AND b.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Optional<Bet> findById(Integer id);

    @Query("SELECT b FROM Bet b WHERE b.user.id=:userId ORDER BY b.dateTime DESC")
    List<Bet> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT b FROM Bet b WHERE b.user.id=:userId AND " +
            "b.dateTime BETWEEN :startDate AND :endDate ORDER BY b.dateTime DESC")
    List<Bet> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime c, @Param("userId") int userId);

    @Query("SELECT b FROM Bet b JOIN FETCH b.user WHERE b.id = ?1 and b.user.id = ?2")
    Bet getWithUser(int id, int userId);

}