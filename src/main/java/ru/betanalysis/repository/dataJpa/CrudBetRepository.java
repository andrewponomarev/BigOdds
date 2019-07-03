package ru.betanalysis.repository.dataJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.betanalysis.model.Bet;

public interface CrudBetRepository extends JpaRepository<Bet, Integer> {
}