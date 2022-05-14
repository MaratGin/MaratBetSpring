package ru.kpfu.itis.services;

import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;

import javax.servlet.http.Cookie;

public interface UsersService {
    User register(UserForm userForm);
    Cookie signIn(AuthForm authForm);
    User findByLogin(String login);
    User findByCookie(String cookie);
    String getCookie(Cookie[] cookies);
    boolean uploadBet(UserBet userBet);
    Cookie setCookie(User user);
    Confirmation saveConfirm(ConfirmForm confrimForm);
    Boolean isEmailExists(String email);
    Boolean isLoginExists(String login);
    Confirmation findByEmail(String email);


}
