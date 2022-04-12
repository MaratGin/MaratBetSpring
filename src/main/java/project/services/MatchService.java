package project.services;

import project.forms.MatchForm;
import project.models.Match;

public interface MatchService {
    Match createMatch(MatchForm matchForm);
    int findId();
    Match[] getSchedule();
}