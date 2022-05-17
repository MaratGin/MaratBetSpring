package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Match;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.security.authentification.CookieAuthentication;
import ru.kpfu.itis.services.interfaces.AuthService;
import ru.kpfu.itis.services.interfaces.MatchService;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class ProfileController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String DEFAULT_PATH = "/resources/photos/tasks/Task1.png";

    @Autowired
    UsersService usersService;

    @Autowired
    MatchService matchService;

    @Autowired
    AuthService authService;
    private final Logger log = LoggerFactory.getLogger(ProfileController.class);


    @Value("${custom.absolute.file.storage}")
    private String absoluteFilePath;

    @RequestMapping(value = "/profile" , method = RequestMethod.GET)
    public ModelAndView getProfilePage(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        log.info("Странница профиля!");
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
            log.info("объект пользователя нулл");
            modelAndView.setViewName("redirect:/logout");
            return modelAndView;
        }
        matchService.getCountWins(user.getId());
       Long won = matchService.getCountLoses(user.getId());

        Long lost =  matchService.getCountWins(user.getId());

        String photo = usersService.getAvatarById(user.getId());

        modelAndView.addObject("win",won);
       modelAndView.addObject("lose", lost);
       modelAndView.addObject("date", user.getRegistration().toString());
       modelAndView.addObject("login", user.getLogin());
       modelAndView.addObject("email",user.getEmail());
       modelAndView.addObject("url",photo);
        modelAndView.addObject("balance",user.getBalance());
        modelAndView.setViewName("profile");
        return modelAndView;
    }
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public ModelAndView uploadPhoto(@RequestParam MultipartFile file, CookieAuthentication authentication) {
       log.info("Опубликование фото");
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        String name = file.getOriginalFilename();

        usersService.updatePhoto(name, user.getId());
        try {
            file.transferTo(new File(absoluteFilePath + name));
        } catch (IOException e) {
        }
        modelAndView.setViewName("redirect:/profile");
        return modelAndView;
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        File file = new File(absoluteFilePath + filename);
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);
        } catch (FileNotFoundException e) {
//            logger.info("BadRequest - фото не удалость получить");
            return ResponseEntity.badRequest().build();
        }
    }


}
