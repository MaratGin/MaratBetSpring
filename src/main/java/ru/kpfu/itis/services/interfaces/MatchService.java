package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.entities.Bet;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.MatchForm;
import ru.kpfu.itis.models.entities.Match;
import ru.kpfu.itis.models.forms.UserBetForm;

import java.util.List;

public interface MatchService {
    Match createMatch(MatchForm matchForm);
    Match findId(Long id);
    List<Match> getSchedule();
    Long getCountWins(Long id);
    Long getCountLoses(Long id);
    UserBet uploadBet(UserBetForm userBetForm);
    Bet findByMatchId(Long matchId, Integer outcome);
}