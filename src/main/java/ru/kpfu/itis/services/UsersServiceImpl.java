package ru.kpfu.itis.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.repositories.ConfirmationRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;

@Service
public class UsersServiceImpl implements UsersService{
    private final int logRounds = 10;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ConfirmationRepository confirmationRepository;


    @Override
    public User register(UserForm userForm) {
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setEmail(userForm.getEmail());
        String passwordHash = BCrypt.hashpw(userForm.getPassword(),BCrypt.gensalt(logRounds));
        user.setPasswordHash(passwordHash);

        return usersRepository.save(user);
    }

    @Override
    public Cookie signIn(AuthForm authForm) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findByCookie(String cookie) {
        return null;
    }

    @Override
    public String getCookie(Cookie[] cookies) {
        return null;
    }

    @Override
    public boolean uploadBet(UserBet userBet) {
        return false;
    }

    @Override
    public Cookie setCookie(User user) {
        return null;
    }

    @Override
    public Confirmation saveConfirm(ConfirmForm confrimForm) {
        Confirmation confirmation = new Confirmation();
        confirmation.setCode(confrimForm.getCode());
        confirmation.setEmail(confrimForm.getEmail());
        return confirmationRepository.save(confirmation);
    }
}
