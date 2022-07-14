package ru.kpfu.itis.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.controllers.MainController;
import ru.kpfu.itis.models.entities.Bet;
import ru.kpfu.itis.models.entities.UserBet;
import ru.kpfu.itis.models.forms.MatchForm;
import ru.kpfu.itis.models.entities.Match;
import ru.kpfu.itis.models.forms.UserBetForm;
import ru.kpfu.itis.repositories.BetRepository;
import ru.kpfu.itis.repositories.MatchRepository;
import ru.kpfu.itis.repositories.UserBetRepository;
import ru.kpfu.itis.services.interfaces.MatchService;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchRepository matchRepository;
    private final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    UserBetRepository userBetRepository;

    @Autowired
    BetRepository betRepository;


    @Override
    public Match createMatch(MatchForm matchForm) {
        log.info("Creating match");
     Match match =   Match.builder()
                .date(matchForm.getDate())
                .drawK(matchForm.getDrawK())
                .teamOneK(matchForm.getTeamOneK())
                .teamOneLogo(matchForm.getTeamOneLogo())
                .teamOneName(matchForm.getTeamOneName())
                .teamTwoK(matchForm.getTeamTwoK())
                .teamTwoLogo(matchForm.getTeamTwoLogo())
                .teamTwoName(matchForm.getTeamTwoName())
                .time(matchForm.getTime())
                .build();
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match findId(Long id) {
        log.info("Finding by id");
        Optional<Match> match = matchRepository.findById(id);
        if (match.isPresent()) {
            return match.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Match> getSchedule() {

        return matchRepository.findAll();

    }

    @Override
    public Long getCountWins(Long id) {
        log.info("counting wins ");
        Long ans = userBetRepository.getWonByID(id).longValue();
        System.out.println(ans+ "''d''d'sd's'd");
        return ans;
    }

    @Override
    public Long getCountLoses(Long id) {
        log.info("Counting losses");
        Long ans = userBetRepository.getLoseByID(id).longValue();
        System.out.println(ans+ "''d''d'sd's'd");
        return ans;
    }

    @Override
    public UserBet uploadBet(UserBetForm userBetForm) {
        log.info("Uploading bet");
        Optional<Bet> bet = betRepository.findByMatchId(userBetForm.getMatchId(), userBetForm.getOutcome());
       if (bet.isPresent()) {
           Bet ans = bet.get();
          UserBet userBet = UserBet.builder()
                   .userId(userBetForm.getUserId())
                   .betId(ans.getId())
                   .outcome(userBetForm.getOutcome())
                   .value(userBetForm.getValue())
                   .build();
         return  userBetRepository.save(userBet);
       }
        return null;
    }

    @Override
    public Bet findByMatchId(Long matchId, Integer outcome) {
        log.info("Finding by id");
        Optional<Bet> bet = betRepository.findByMatchId(matchId,outcome);
       if (bet.isPresent()) {
           return bet.get();
       }
       return null;
    }
}
