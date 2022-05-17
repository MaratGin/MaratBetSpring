package ru.kpfu.itis.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.enums.Role;
import ru.kpfu.itis.exceptions.UserNotFoundException;
import ru.kpfu.itis.models.entities.*;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.ConfirmationRepository;
import ru.kpfu.itis.repositories.PhotosRepository;
import ru.kpfu.itis.repositories.UsersRepository;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PhotosRepository photosRepository;

    private final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);



    @Override
    public User register(UserForm userForm)  {
        log.info("Registration");
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setEmail(userForm.getEmail());
        user.setRole(Role.ROLE_USER);
        user.setConfirmed(userForm.getConfirmed());
        user.setBalance(1000L);
        Date date = new Date();
        user.setRegistration(new Timestamp(date.getTime()));
        String passwordHash = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(passwordHash);
        User savedUser = usersRepository.save(user);
       Photos photos =  Photos.builder()
                .userID(savedUser.getId())
                        .photoURL("Task1.png")
                                .build();
       photosRepository.save(photos);
        return savedUser;
    }

    @Override
    public Cookie signInAfter(AuthForm authForm, User user) {
        Auth auth = new Auth();
                System.out.println("VHOD USPESHEN!!!");
                String cookieValue = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("auth",cookieValue);
                cookie.setMaxAge(10 * 60 *60);
                auth.setUser(user);
                auth.setCookieValue(cookieValue);
                authRepository.save(auth);
                return cookie;
    }

    @Override
    public String getAvatarById(Long id) {
       Optional<String> photo =  photosRepository.findByUserID(id);
       if (photo.isPresent()) {
           return photo.get();
       } else {
           return "Task1.png";
       }
    }

    @Override
    public void updatePhoto(String url, Long id) {
        photosRepository.updatePhotoById(url,id);
    }

    @Override
    public void updateBalance(Long total, Long id) {
        log.info("Updating balance");
        usersRepository.updateBalance(total,id);
//        usersRepository.up
    }

    @Override
    public User findByLogin(String login) throws UserNotFoundException {
        log.info("Finding by login");
       Optional<User> user = usersRepository.findByLogin(login);

       if (user.isPresent()) {
           return user.get();
       }
       throw new UserNotFoundException("Пользователь не найден!");
    }

    @Override
    public String getCookie(Cookie[] cookies) {
        return null;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public Confirmation saveConfirm(ConfirmForm confrimForm) {
        log.info("Saving confirmation");
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

    @Override
    public User findUserByEmail(String email) {
        System.out.println(email);
       User user =  usersRepository
                .findByEmail(email).get();

        return user;
    }

    @Override
    public Cookie signIn(LoginForm loginForm) {
        Auth auth = new Auth();
        System.out.println(loginForm.getEmail());
        Optional<User> user = usersRepository.findByEmail(loginForm.getEmail());
        if (user.isPresent()) {
            System.out.println(loginForm.getPassword());
            System.out.println(user.get().getPassword());
            if (passwordEncoder.matches(loginForm.getPassword(),user.get().getPassword())){
                String cookieValue = UUID.randomUUID().toString();
                Cookie cookie = new Cookie("auth",cookieValue);
                cookie.setMaxAge(10 * 60 *60);
                auth.setUser(user.get());
                auth.setCookieValue(cookieValue);
                authRepository.save(auth);
                return cookie;
            } else {
                log.info("Sign in failed!");
            }
        }
        return null;
    }
}
