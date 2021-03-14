package com.ruj.footballlegue.elements;

import lombok.Data;

@Data
public class TeamStandingResponse {
    private String countryIdAndName;
    private String leagueIdAndName;
    private String teamIdAndName;
    private String overallLeaguePosition;
}
