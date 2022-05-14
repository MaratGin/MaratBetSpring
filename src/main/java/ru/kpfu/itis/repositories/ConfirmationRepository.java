package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Confirmation;

public interface ConfirmationRepository  extends JpaRepository<Confirmation,Long> {

}
