package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.entities.User;

import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User set balance=?1 where id=?2")
    void updateBalance(Long value, Long id);
//    Optional<User> findByCookie(String cookie_value);
}