package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.models.entities.Bet;

import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {
    @Query("select b from Bet b where b.matchId=?1 and b.outcome=?2")
    Optional<Bet> findByMatchId(Long id, Integer outcome);


    @Modifying
    @Query("update Bet set outcome=?2 where id=?1")
    void updateBet(Long id, int outcome );

}
