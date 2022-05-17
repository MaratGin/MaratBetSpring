package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.entities.Auth;

import java.util.Optional;

public interface AuthService {
    void deleteCookies(String cookieValue);
    Auth getAuthByCookie(String cookieValue);
}
