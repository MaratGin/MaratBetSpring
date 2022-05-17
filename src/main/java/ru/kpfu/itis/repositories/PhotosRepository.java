package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.models.entities.Match;
import ru.kpfu.itis.models.entities.Photos;

import java.util.Optional;

public interface PhotosRepository extends JpaRepository<Photos, Long> {

    @Query("select p.photoURL from Photos p " +
            "where p.userID = (select max(ph.id) from Photos ph where ph.userID = ?1)")
    Optional<String> findByUserID(Long id);

    @Modifying
    @Query("update Photos set photoURL=?1 where userID=?2")
    void updatePhotoById(String url, Long id);
}
