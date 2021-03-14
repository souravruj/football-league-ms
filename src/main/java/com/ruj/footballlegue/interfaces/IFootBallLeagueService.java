package com.ruj.footballlegue.interfaces;

import com.ruj.footballlegue.elements.TeamStanding;
import com.ruj.footballlegue.elements.TeamStandingRequest;

public interface IFootBallLeagueService {
    public TeamStanding getTeamStanding(TeamStandingRequest teamStandingRequest);
}
