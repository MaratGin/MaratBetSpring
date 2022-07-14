package ru.kpfu.itis.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.models.dtos.BetDto;
import ru.kpfu.itis.models.dtos.UserDto;
import ru.kpfu.itis.models.entities.Bet;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.services.interfaces.BetService;
import ru.kpfu.itis.services.interfaces.UsersService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class BetRESTController {

    @Autowired
    private BetService betService;

    @GetMapping("/rest/getBets")
    @PermitAll
    public List<Bet> getallBets() {
        return betService.getAllbets();
    }

    @PostMapping("/rest/postBets")
    public Bet addUser(@RequestBody BetDto betDto) {
        return betService.addNewbet(betDto);
    }

    @PutMapping("/rest/bets/{bet-id}")
    @PermitAll
    public Bet updateUser(@PathVariable("bet-id") Integer betid, @RequestBody BetDto betDto) {
        System.out.println(betid + " "+ betDto.toString());
        return betService.updateBet(Long.valueOf(betid),betDto);
    }

    @DeleteMapping("/rest/bets/{bet-id}")
    @PermitAll
    public Bet deleteUser(@PathVariable("bet-id") Integer betId) {
       return betService.deleteBet(Long.valueOf(betId));
    }
}
