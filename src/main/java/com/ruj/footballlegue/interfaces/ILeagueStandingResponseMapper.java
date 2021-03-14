package com.ruj.footballlegue.interfaces;

import com.ruj.footballlegue.elements.TeamStanding;
import com.ruj.footballlegue.elements.TeamStandingResponse;

public interface ILeagueStandingResponseMapper {
     public TeamStandingResponse mapTeamStandingResponse(TeamStanding teamStanding);
}
