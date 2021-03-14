package com.ruj.footballlegue.service;

import com.ruj.footballlegue.elements.*;
import com.ruj.footballlegue.exception.Error;
import com.ruj.footballlegue.exception.ResourceNotFoundException;
import com.ruj.footballlegue.interfaces.IFootBallLeagueService;
import com.ruj.footballlegue.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FootBallLeagueService implements IFootBallLeagueService {

    @Value("$(football.service.baseUrl)")
    private String footballBaseUrl;
    @Value("$(football.service.api_key)")
    private String api_key;
    @Value("$(football.action.country)")
    private String actionForgetCountry;


    @Override
    public TeamStanding getTeamStanding(TeamStandingRequest teamStandingRequest){

        List<Country> countryList = null;
        List<League> leagueList = null;
        List<TeamStanding> teamStandingList = null;

        Country[] coutryResponse = getCountry();
        if(coutryResponse != null){
          countryList =   Arrays.asList(coutryResponse);
        }
        Country country = validateCountry(countryList,teamStandingRequest.getCountryName());

        League[] leaguesResponse = getLeagues(country.getCountry_id());
        if(leaguesResponse != null){
            leagueList = Arrays.asList(leaguesResponse);
        }
        League league = validateLeagueName(leagueList, teamStandingRequest.getLeagueName());

        TeamStanding[] teamStandingResponse = getTeamStandingCall(league.getLeague_id());
        if(teamStandingResponse != null){
            teamStandingList = Arrays.asList(teamStandingResponse);
        }
        TeamStanding teamStanding = filterLeagueStanding(teamStandingList, teamStandingRequest.getTeamName());
        teamStanding.setCountry_id(country.getCountry_id());
        return teamStanding;
    }

    private TeamStanding filterLeagueStanding(List<TeamStanding> teamStandingList, String teamName) {
       return teamStandingList.stream()
                .filter(teamStanding -> teamStanding.getTeam_name().equalsIgnoreCase(teamName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
    }

    private League validateLeagueName(List<League> leagueList, String leagueName) {
        League leagues =  leagueList.stream()
                .filter(leagues1 -> leagues1.getLeague_name().equalsIgnoreCase(leagueName))
                .findFirst().orElse(null);

        if(leagues == null)
            throw new ResourceNotFoundException("League not found");
        return leagues;
    }

    private Country validateCountry(List<Country> countryList, String countryName){
        Country country1 =  countryList.stream()
                .filter(country -> country.getCountry_name().equalsIgnoreCase(countryName))
                .findFirst().orElse(null);

        if(country1 == null) {
            throw new ResourceNotFoundException("Country  not found");
        }
        return country1;
    }


    public Country[] getCountry(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Country[]> requestHttpEntity = new HttpEntity<>(getHeaders());
        return restTemplate
                .exchange("https://apiv2.apifootball.com/?action=get_countries&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", HttpMethod.GET,requestHttpEntity, Country[].class).getBody();
    }

    public League[] getLeagues(String country_id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<League[]> requestHttpEntity = new HttpEntity<>(getHeaders());
        return restTemplate
                .exchange("https://apiv2.apifootball.com/?action=get_leagues&country_id="+country_id+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", HttpMethod.GET,requestHttpEntity, League[].class).getBody();
    }

    public TeamStanding[] getTeamStandingCall(String league_id){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TeamStanding[]> requestHttpEntity = new HttpEntity<>(getHeaders());
        return restTemplate
                .exchange("https://apiv2.apifootball.com/?action=get_standings&league_id="+league_id+"&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978", HttpMethod.GET,requestHttpEntity, TeamStanding[].class).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }


    private String buildCompleteURIForGetCountry(){
        String actionForGetCountry = "get_country";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(footballBaseUrl)
                .queryParam(Constants.ACTION,actionForgetCountry)
                .queryParam(Constants.API_KEY,"");
        return builder.toUriString();
    }

}
