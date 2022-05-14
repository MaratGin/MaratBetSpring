package ru.kpfu.itis.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.ConfirmationRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService{
    private final int logRounds = 10;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthRepository authRepository;

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
        Auth auth = new Auth();
        System.out.println("I aam in signIn!");
        Optional<User> user = usersRepository.findByLogin(authForm.getEmail());
        if (user.isPresent()) {
            if (BCrypt.checkpw(authForm.getPassword(),user.get().getPasswordHash())){
                System.out.println("VHOD USPESHEN!!!");
                String cookieValue = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("auth",cookieValue);
                cookie.setMaxAge(10 * 60 *60);
                auth.setUser(user.get());
                auth.setCookieValue(cookieValue);
                authRepository.save(auth);
                return cookie;
            } else {
                System.out.println("signIn Failed!!!");
            }
        }
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
        System.out.println("Saving");
        Confirmation confirmation = new Confirmation();
        confirmation.setCode(confrimForm.getCode());
        confirmation.setEmail(confrimForm.getEmail());
        return confirmationRepository.save(confirmation);
    }

    @Override
    public Boolean isEmailExists(String email) {
       if ( usersRepository.findByEmail(email).isPresent()) {
           return true;
       } else  {
           return false;
       }
    }

    @Override
    public Boolean isLoginExists(String login) {
       if (usersRepository.findByLogin(login).isPresent()) {
           return true;
       } else  {
           return false;
       }
    }

    @Override
    public Confirmation findByEmail(String email) {
       Optional<Confirmation> confirmation = confirmationRepository.findByEmail(email);
       if (confirmation.isPresent()) {
           return confirmation.get();

       }
       return null;
    }
}
