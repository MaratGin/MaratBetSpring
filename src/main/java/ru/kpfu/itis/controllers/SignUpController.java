package ru.kpfu.itis.controllers;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.ConfirmDto;
import ru.kpfu.itis.models.dtos.UserDto;
import ru.kpfu.itis.models.entities.Confirmation;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.ConfirmForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.services.EmailServiceImpl;
import ru.kpfu.itis.services.UsersService;
import ru.kpfu.itis.services.Validator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Controller
public class SignUpController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private EmailServiceImpl emailService;
    private String curEmail;

//    @Autowired
//    private USersService uSersService;
//    @Override
//    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("signUp.ftl");
////       modelAndView.addObject("user",)
//        return modelAndView;
//    }

@RequestMapping(value = "/signUp" , method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
    System.out.println("smdskldalkmsdkakdllm");
//        List<User> users = usersService.getUsers();
//        modelAndView.addObject("users", users);
        modelAndView.addObject("registrationStatus", "");
        modelAndView.setViewName("signUp");
        return modelAndView;
    }

    @RequestMapping(value = "/signUp" , method = RequestMethod.POST)
    public ModelAndView userPOST(UserDto userDto, HttpServletResponse response, HttpServletRequest request) {
        Integer code = (int) (100000 + Math.random()* 100000);
        ModelAndView modelAndView = new ModelAndView();
        Validator validator = new Validator();
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])[a-zA-Z0-9]{6,64}");
        Pattern emailPattern = Pattern.compile("[A-Z0-9a-z]{1,64}@[0-9A-Za-z]{1,64}\\\\.[a-z]{2,64}");
        Pattern loginPattern = Pattern.compile("(?=.*[0-9])[a-zA-Z0-9]{6,12}");
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
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (password.length() < 8) {
            error = "Недопустимая длина пароля (минимум 8 символов)!";
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (!password.equals(repassword)) {
            error = "пароли не совпадают!";
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (!passwordPattern.matcher(password).matches()) {
            error = "неверный формат пароля!";
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (!emailPattern.matcher(password).matches()) {
            error = "неверный формат e-mail адреса!";
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }
        if (!loginPattern.matcher(password).matches()) {
            error = "неверный формат имени пользователя!";
            modelAndView.setViewName("redirect:/signUp");
            modelAndView.addObject("registrationStatus",error);
            return modelAndView;
        }

        if (password.equals(repassword)) {
            UserForm userForm = UserForm.builder()
                    .login(login)
                    .password(password)
                    .email(email)
                    .build();
           AuthForm authForm =  AuthForm.builder()
                    .login(login)
                    .email(email)
                    .password(password)
                   .build();
            ConfirmForm confirmForm = ConfirmForm.builder()
                    .email(email)
                    .code(code)
                    .build();

           Cookie cookie =  usersService.signIn(authForm);
           if (cookie != null) {
               String messageBody = login + ", Добро пожаловать на MaratBet! \n " +
                       "Для того, чтобы пользоваться нашим сайтам необходимо подтвердить аккаунт введя код ниже \n "
                       + code.toString();

               emailService.sendSimpleMessage(email,"Подтверждение аккаунта",messageBody);
              Confirmation confirmation = usersService.saveConfirm(confirmForm);
//               curEmail = email;
//               cookie.setMaxAge(10*60*60);
               modelAndView.setViewName("redirect:/confirmation");
//               response.addCookie(cookie);
//               response.addCookie(new Cookie("attempt", Integer.toString(confirmation.getId())));
              ConfirmDto confirmDto =  ConfirmDto.builder()
                       .code(code)
                       .email(email)
                       .build();
               return confirmation(confirmDto, request);
           } else {
               error = "Ошибка сервера, повторите позже";
               modelAndView.setViewName("redirect:/signUp");
               modelAndView.addObject("registrationStatus",error);
               return modelAndView;
           }
            
        } else {
            error = "Пароли не совпадают!";
        }
        modelAndView.setViewName("redirect:/signUp");
        modelAndView.addObject("registrationStatus",error);
        return modelAndView;
//        List<User> users = usersService.getUsers();
//        modelAndView.addObject("users", users);
    }

    @RequestMapping(value = "/confirmation" , method = RequestMethod.GET)
    public ModelAndView confirmation(ConfirmDto confirmDto, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
//        response.cook
        System.out.println("smdskldalkmsdkakdllm");
        request.setAttribute("email", confirmDto.getEmail());
//        List<User> users = usersService.getUsers();
//        modelAndView.addObject("users", users);
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
    @RequestMapping(value = "/confirmation" , method = RequestMethod.POST)
    public ModelAndView confirmationPOST(ConfirmDto confirmDto, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
//        response.cook
        System.out.println("smdskldalkmsdkakdllm");
//        List<User> users = usersService.getUsers();
//        modelAndView.addObject("users", users);
        modelAndView.setViewName("signUp");
        return modelAndView;
    }


}
