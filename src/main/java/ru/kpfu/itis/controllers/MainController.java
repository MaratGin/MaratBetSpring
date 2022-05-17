package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "/")
    public String introductionPage() {
        log.info("Главная странница");
        return "introduction";}
}
