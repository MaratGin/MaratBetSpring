package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.entities.Match;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MatchRepository  extends JpaRepository<Match, Long> {
    @Override
    List<Match> findAll();

    @Override
    Optional<Match> findById(Long id);


}
