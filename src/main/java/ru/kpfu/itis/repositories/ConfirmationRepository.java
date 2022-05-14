package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Confirmation;

import java.util.Optional;

@Repository
@Transactional
public interface ConfirmationRepository  extends JpaRepository<Confirmation,Long> {
    @Query("select c from Confirmation c " +
            "where c.id = (select max(conf.id) from Confirmation conf where conf.email = :value )")
    Optional<Confirmation> findByEmail(String email);
}
