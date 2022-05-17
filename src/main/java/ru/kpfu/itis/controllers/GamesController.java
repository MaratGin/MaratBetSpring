package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.exceptions.UserNotFoundException;
import ru.kpfu.itis.models.dtos.BetDto;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Bet;
import ru.kpfu.itis.models.entities.Match;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.UserBetForm;
import ru.kpfu.itis.services.interfaces.AuthService;
import ru.kpfu.itis.services.interfaces.MatchService;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class GamesController {
    @Autowired
    MatchService matchService;

    @Autowired
    UsersService usersService;

    @Autowired
    AuthService authService;

    private final Logger log = LoggerFactory.getLogger(GamesController.class);



    @RequestMapping(value = "/main" , method = RequestMethod.GET)
    public ModelAndView getMainPage(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        log.info("Переход на главную страницу");
        ModelAndView modelAndView = new ModelAndView();
        List<Match> matches =  matchService.getSchedule();
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
            modelAndView.setViewName("redirect:/logout");
            return modelAndView;
        }
        modelAndView.addObject("balance",user.getBalance());
        modelAndView.addObject("name",user.getLogin());
        System.out.println(matches.size());
        modelAndView.addObject("matches",matches);
        modelAndView.setViewName("mainPage");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping( value = "/bet")
    public String placeBet(@RequestBody() BetDto betDto) throws JsonProcessingException {
        log.info("Обработка полученного JSON от AJAX");

        User user = null;
        try {
            user = usersService.findByLogin(betDto.getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
       Bet bet =matchService.findByMatchId(Long.valueOf(betDto.getMatchId()),betDto.getOutcome());

      UserBetForm userBetForm =  UserBetForm.builder()
                .matchId(bet.getMatchId())
                .betId(bet.getId())
                .outcome(bet.getOutcome())
                .userId(user.getId())
                .value(betDto.getValue())
                .build();
      Long total = user.getBalance() - userBetForm.getValue();
      usersService.updateBalance(total, user.getId());
        System.out.println(total);
        matchService.uploadBet(userBetForm);
        return "mainPage";
    }
}
