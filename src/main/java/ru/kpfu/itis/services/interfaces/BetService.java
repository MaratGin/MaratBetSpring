package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.dtos.BetDto;
import ru.kpfu.itis.models.entities.Bet;

import java.util.List;

public interface BetService {
    List<Bet> getAllbets();
    Bet addNewbet(BetDto betDto);
    Bet updateBet(Long betId,BetDto betDto);
    Bet deleteBet(Long betid);

}
