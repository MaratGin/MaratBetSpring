package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.exceptions.UserNotFoundException;
import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface UsersService {
    User register(UserForm userForm);
    Cookie signIn(LoginForm loginForm);
    User findByLogin(String login) throws UserNotFoundException;
    String getCookie(Cookie[] cookies);
//    boolean uploadBet(UserBet userBet);
    User findById(Long id);
    Confirmation saveConfirm(ConfirmForm confrimForm);
    Boolean isEmailExists(String email);
    Boolean isLoginExists(String login);
    Confirmation findByEmail(String email);
    User findUserByEmail(String email);
    Cookie signInAfter(AuthForm authForm, User user);
   String getAvatarById(Long id);
   void updatePhoto(String url, Long id);
   void updateBalance(Long total, Long id);



}
