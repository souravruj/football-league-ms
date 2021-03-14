package com.ruj.footballlegue.controller;

import com.ruj.footballlegue.elements.TeamStandingRequest;
import com.ruj.footballlegue.elements.TeamStandingResponse;
import com.ruj.footballlegue.interfaces.IFootBallLeagueService;
import com.ruj.footballlegue.interfaces.ILeagueStandingResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class FootballLeagueResource {

    @Autowired
    IFootBallLeagueService iFootBallLeagueService;
    @Autowired
    ILeagueStandingResponseMapper iLeagueStandingResponseMapper;

    @GetMapping(path = "/team/standing")
    public TeamStandingResponse getLeagueStanding(@RequestParam(name = "countryName") String countryName, @RequestParam(name = "teamName") String teamName, @RequestParam(name = "leagueName") String leagueName) throws Exception {
        log.info("Rest input : ");
        if(isBlankStringOrNotNull(countryName) || isBlankStringOrNotNull(teamName) || isBlankStringOrNotNull(leagueName)){
            throw new IllegalArgumentException("Please provide all required input with correct value");
        }
        TeamStandingRequest teamStandingRequest = mapTeamStandingRequest(countryName, teamName, leagueName);

        return iLeagueStandingResponseMapper.mapTeamStandingResponse(iFootBallLeagueService.getTeamStanding(teamStandingRequest));
    }

    private TeamStandingRequest mapTeamStandingRequest(String countryName, String teamName, String leagueName) {
        TeamStandingRequest teamStandingRequest = new TeamStandingRequest();
        teamStandingRequest.setCountryName(countryName);
        teamStandingRequest.setLeagueName(leagueName);
        teamStandingRequest.setTeamName(teamName);
        return teamStandingRequest;
    }

    private boolean isBlankStringOrNotNull(String string) {
        return string == null || string.trim().isEmpty();
    }
}
