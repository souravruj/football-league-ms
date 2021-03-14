package com.ruj.footballlegue.elements;

import lombok.Data;

@Data
public class TeamStanding {
    private String country_id;
    private String country_name;
    private String league_id;
    private String league_name;
    private String team_id;
    private String team_name;
    private String overall_league_position;
}
