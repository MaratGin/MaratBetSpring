package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.entities.Auth;

import java.util.Optional;

@Repository
@Transactional
public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findByCookieValue(String cookieValue);



}
