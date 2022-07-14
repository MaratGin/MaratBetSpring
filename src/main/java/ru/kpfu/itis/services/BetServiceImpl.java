package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.dtos.BetDto;
import ru.kpfu.itis.models.entities.Bet;
import ru.kpfu.itis.repositories.BetRepository;
import ru.kpfu.itis.services.interfaces.BetService;

import java.util.List;
import java.util.Optional;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    BetRepository betRepository;

    @Override
    public List<Bet> getAllbets() {
        return betRepository.findAll();
    }

    @Override
    public Bet addNewbet(BetDto betDto) {
      Bet bet =  Bet.builder()
                .k(betDto.getValue())
                .matchId(Long.valueOf(betDto.getMatchId()))
                .outcome(betDto.getOutcome())
                .build();
        return betRepository.save(bet);
    }

    @Override
    public Bet updateBet(Long betId,BetDto betDto) {
       Optional<Bet> bet = betRepository.findById(betId);
       betRepository.updateBet(betId,betDto.getOutcome());
       if (bet.isPresent()) {
           return bet.get();
       }
       return null;
    }

    @Override
    public Bet deleteBet(Long betid) {
      Optional<Bet> bet =  betRepository.findById(betid);
        if (bet.isPresent()) {
            betRepository.deleteById(betid);
            return bet.get();
        }
        return null;
    }
}
