package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.services.interfaces.AuthService;
import ru.kpfu.itis.services.interfaces.MatchService;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class ChatController {
    @Autowired
    MatchService matchService;
    @Autowired
    UsersService usersService;
    @Autowired
    AuthService authService;

    private final Logger log = LoggerFactory.getLogger(ChatController.class);

    @GetMapping("/chat")
    public ModelAndView getIndexPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        log.info("Обработка запроса контроллера чата");
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        User user = new User();
        for (Cookie cookie:cookies) {
            if (Objects.equals(cookie.getName(), "Auth")) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                Auth auth = authService.getAuthByCookie(cookie.getValue());
                user = usersService.findById(auth.getUser().getId());
                System.out.println(user.toString());
            }
        }
        if (user == null) {
            System.out.println("NUUUUUUUUUUUL");
            modelAndView.setViewName("redirect:/mainPage");
            return modelAndView;
        }
        System.out.println("------sd-----------s-d-sd-s-ds-d-");
        System.out.println(user.getLogin() + "   "+ user.getLogin() );
        modelAndView.addObject("id", user.getLogin());
        modelAndView.addObject("name", user.getLogin());
        modelAndView.setViewName("chat");
//
//        model.addAttribute("id", user.getLogin());
//        model.addAttribute("name",user.getLogin());
        return modelAndView;
    }
}
