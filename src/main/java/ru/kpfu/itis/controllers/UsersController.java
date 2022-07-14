package ru.kpfu.itis.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.AuthDto;
import ru.kpfu.itis.models.dtos.ConfirmDto;
import ru.kpfu.itis.models.dtos.UserDto;
import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.LoginForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.services.EmailServiceImpl;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailServiceImpl emailService;

    private final Logger log = LoggerFactory.getLogger(UsersController.class);


    @RequestMapping(value = "/signUp" , method = RequestMethod.GET)
    public ModelAndView users() {
        log.info("Sign up page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registrationStatus", "");
        modelAndView.setViewName("signUp");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp" , method = RequestMethod.POST)
    public ModelAndView userPOST(UserDto userDto, HttpServletResponse response, HttpServletRequest request) {
          log.info("Registrating user");
        Integer code = (int) (100000 + Math.random()* 100000);
        ModelAndView modelAndView = new ModelAndView();
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])[a-zA-Z0-9]{6,64}");
        Pattern emailPattern = Pattern.compile("[A-Z0-9a-z]{1,64}@[0-9A-Za-z]{1,64}\\.[a-z]{2,64}");
        Pattern loginPattern = Pattern.compile("[a-zA-Z0-9]{6,12}");
        String login = userDto.getLogin();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String repassword = userDto.getRepassword();
        String error;
        if (login.length() < 5) {
            error = "Недопустимая длина логина (минимум 5 символов)!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (email.length() == 0) {
            error = "Недопустимая длина e-mail!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);

            return modelAndView;
        }
        if (password.length() < 8) {
            error = "Недопустимая длина пароля (минимум 8 символов)!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);

            return modelAndView;
        }
        if (!password.equals(repassword)) {
            error = "пароли не совпадают!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);

            return modelAndView;
        }
        if (!passwordPattern.matcher(password).matches()) {
            error = "неверный формат пароля!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);

            return modelAndView;
        }
        if (!emailPattern.matcher(email).matches()) {
            error = "неверный формат e-mail адреса!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);

            return modelAndView;
        }
        if (usersService.isEmailExists(email)) {
            error = "аккаунт с таким e-mail уже существует!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }


        if (!loginPattern.matcher(login).matches()) {
            error = "неверный формат имени пользователя!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (usersService.isLoginExists(login)) {
            error = "аккаунт с таким логином уже существует!";
            modelAndView.setViewName("signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }

        if (password.equals(repassword)) {

            UserForm userForm = UserForm.builder()
                    .login(login)
                    .password(password)
                    .email(email)
                    .confirmed(0)
                    .build();
            ConfirmForm confirmForm = ConfirmForm.builder()
                    .email(email)
                    .code(code)
                    .build();


               String messageBody = login + ", Добро пожаловать на MaratBet! \n " +
                       "Для того, чтобы пользоваться нашим сайтам необходимо подтвердить аккаунт введя код ниже \n "
                       + code.toString();
                try {
                    emailService.sendSimpleMessage(email,"Подтверждение аккаунта",messageBody);
                } catch (Exception e) {
                    modelAndView.addObject("email", confirmForm.getEmail());
                    modelAndView.addObject("registrationStatus", "Непредвиденная ошибка, повторите");
                    modelAndView.setViewName("signUp");
                }
              Confirmation confirmation = usersService.saveConfirm(confirmForm);

               modelAndView.addObject("registrationStatus", "");
               modelAndView.setViewName("confirmation");
              ConfirmDto confirmDto =  ConfirmDto.builder()
                       .email(email)
                      .code(code)
                       .build();
           User user = usersService.register(userForm);
           if (user != null) {
               return  confirmation(confirmDto,request , userForm);
           } else  {
               modelAndView.addObject("registrationStatus", "Ошибка на стороне сервера, повторите!");
               modelAndView.setViewName("signUp");
               return modelAndView;
           }
        } else {
            error = "Пароли не совпадают!";
        }
        modelAndView.setViewName("signUp");
        modelAndView.addObject("registrationStatus",error);
        return modelAndView;
    }

    @RequestMapping(value = "/confirmation" , method = RequestMethod.GET)
    public ModelAndView confirmation( ConfirmDto confirmDto, HttpServletRequest request, UserForm userForm) {
         log.info("Confirming page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registrationStatus", "");
        modelAndView.addObject("email", confirmDto.getEmail());
        modelAndView.addObject("emaill", confirmDto.getEmail());
        request.setAttribute("email", confirmDto.getEmail());
        System.out.println("smdskldalkmsdkakdllm");
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
    @RequestMapping(value = "/confirmation" , method = RequestMethod.POST)
    public ModelAndView confirmationPOST( ConfirmDto confirmDto, HttpServletRequest request, HttpServletResponse response)  {
        log.info("Checking confirmation");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(confirmDto.toString());
        Confirmation value = usersService.findByEmail(confirmDto.getEmail());
        Gson gson = new Gson();
        if (value != null && Objects.equals(value.getCode(), confirmDto.getCode())) {
            System.out.println("1");
           User user =  usersService.findUserByEmail(confirmDto.getEmail());
           if (user != null) {
               AuthForm authForm = AuthForm.builder()
                       .user_id(user.getId())
                       .email(user.getEmail())
                       .build();
              Cookie cookie = usersService.signInAfter(authForm, user);
              cookie.setMaxAge(10 * 60 * 60);
              response.addCookie(cookie);
              modelAndView.setViewName("mainPage");
              return modelAndView;
           } else {
               modelAndView.addObject("registrationStatus", "Ошибка на стороне сервера, повторите!");
               modelAndView.setViewName("confirmation");
               return modelAndView;
           }
        }
        modelAndView.addObject("registrationStatus", "Неверный ввод кода, повторите!");
        modelAndView.addObject("email", confirmDto.getEmail());
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }

    @RequestMapping(value = "/signIn" , method = RequestMethod.GET)
    public String signIn(HttpServletRequest request, ModelMap model) {
        System.out.println("GEEEEEEEEEEET");

        ModelAndView modelAndView = new ModelAndView();
        if (request.getParameter("error") != null) {
            model.addAttribute("error", "Неправильный логин или пароль");
        }
        return "signIn";
    }

    @RequestMapping(value = "/signIn" , method = RequestMethod.POST)
    public ModelAndView doSignIn(AuthDto authDto, HttpServletResponse response, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        LoginForm loginForm = LoginForm.builder()
                .email(authDto.getEmail())
                .password(authDto.getPassword())
                .build();
        Cookie cookie = usersService.signIn(loginForm);
        if (cookie != null) {
            response.addCookie(cookie);
            modelAndView.setViewName("redirect:/main");
            return modelAndView;
        } else {
            modelMap.addAttribute("error","Неправильный логин или пароль");
            modelAndView.setViewName("redirect:/signIn");
            return modelAndView;
        }
    }
}
