package ru.kpfu.itis.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.controllers.GamesController;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.services.interfaces.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Override
    public void deleteCookies(String cookieValue) {
        log.info("Deleting cookies");
        Auth auth = authRepository.findByCookieValue(cookieValue).get();
        authRepository.delete(auth);
    }

    @Override
    public Auth getAuthByCookie(String cookieValue) {
        log.info("Getting cookies");
        Optional<Auth> auth = authRepository.findByCookieValue(cookieValue);
        if (auth.isPresent()) {
            return auth.get();
        } else {
            return null;
        }
    }
}
