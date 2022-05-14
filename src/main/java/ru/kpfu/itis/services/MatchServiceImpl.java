package ru.kpfu.itis.services;

import ru.kpfu.itis.models.forms.MatchForm;
import ru.kpfu.itis.models.entities.Match;

public class MatchServiceImpl implements MatchService{
    @Override
    public Match createMatch(MatchForm matchForm) {
        return null;
    }

    @Override
    public int findId() {
        return 0;
    }

    @Override
    public Match[] getSchedule() {
        return new Match[0];
    }
}
