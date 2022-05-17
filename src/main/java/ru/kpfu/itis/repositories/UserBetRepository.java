package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.models.entities.UserBet;

public interface UserBetRepository extends JpaRepository<UserBet, Long> {

    @Query("select count(ub) from UserBet ub where ub.userId = ?1 and ub.outcome = 1")
    Long getWonByID(Long id);

    @Query("select count(ub) from UserBet ub where ub.userId = ?1 and ub.outcome = 0")
    Long getLoseByID(Long id);
}
