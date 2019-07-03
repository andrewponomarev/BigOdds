package ru.betanalysis.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.betanalysis.model.Bet;

public interface CrudBetRepository extends JpaRepository<Bet, Integer> {
}