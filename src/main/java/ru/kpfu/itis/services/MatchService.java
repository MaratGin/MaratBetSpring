package ru.kpfu.itis.services;

import ru.kpfu.itis.models.forms.MatchForm;
import ru.kpfu.itis.models.entities.Match;

public interface MatchService {
    Match createMatch(MatchForm matchForm);
    int findId();
    Match[] getSchedule();
}