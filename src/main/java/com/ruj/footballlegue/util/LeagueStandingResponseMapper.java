package com.ruj.footballlegue.util;

import com.ruj.footballlegue.elements.TeamStanding;
import com.ruj.footballlegue.elements.TeamStandingResponse;
import com.ruj.footballlegue.interfaces.ILeagueStandingResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class LeagueStandingResponseMapper implements ILeagueStandingResponseMapper {

     public TeamStandingResponse mapTeamStandingResponse(TeamStanding teamStanding){
        TeamStandingResponse teamStandingResponse = new TeamStandingResponse();
        teamStandingResponse.setCountryIdAndName(teamStanding.getCountry_id() + " & " + teamStanding.getCountry_name());
        teamStandingResponse.setLeagueIdAndName(teamStanding.getLeague_id() + " & " + teamStanding.getLeague_name());
        teamStandingResponse.setTeamIdAndName(teamStanding.getTeam_id() + " & " + teamStanding.getTeam_name());
        teamStandingResponse.setOverallLeaguePosition(teamStanding.getOverall_league_position());

        return teamStandingResponse;

    }

}
