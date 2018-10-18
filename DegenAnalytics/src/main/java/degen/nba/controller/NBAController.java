package degen.nba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import degen.common.UrlUtils;
import degen.nba.services.impl.StatsVsTeamService;

@RestController
public class NBAController {
	
	@Autowired private StatsVsTeamService statsVsTeamService;
	@Value("${NBA_PLAYER_INDEX}") private int PLAYER_NAME; 
	@Value("${NBA_TEAM_INDEX}") private int TEAM; 
	@Value("${NBA_OPPOSING_TEAM_INDEX}") private int OPPOSING_TEAM; 
	
	@ResponseBody
	@RequestMapping("/nba-player-stats-vs-team-game-totals/{year}/{playerFirstName}/{playerLastName}/{teamLocation}/{teamMascot}/{opposingTeamLocation}/{opposingTeamMascot}") 
	public List<List<Integer>> playerStatsVsTeamTotals(@PathVariable("year") String year,
			@PathVariable("playerFirstName") String firstName, @PathVariable("playerLastName") String lastName,
			@PathVariable("teamLocation") String teamLocation, @PathVariable("teamMascot") String teamMascot, 
			@PathVariable("opposingTeamLocation") String opposingTeamLocation, @PathVariable("opposingTeamMascot") String opposingTeamMascot) {
		
		try {
			String[] parsedValues = UrlUtils.parseNbaUrl(firstName, lastName, teamLocation, teamMascot, opposingTeamLocation, opposingTeamMascot);
			return statsVsTeamService.crawlPerGameTotals(year, parsedValues[PLAYER_NAME], parsedValues[TEAM], parsedValues[OPPOSING_TEAM]);
		} catch (Exception e) {

			return new ArrayList<List<Integer>>(); 
		}
	}
	
	@ResponseBody
	@RequestMapping("/nba-player-stats-vs-team-averages/{year}/{playerFirstName}/{playerLastName}/{teamLocation}/{teamMascot}/{opposingTeamLocation}/{opposingTeamMascot}") 
	public List<Double> playerStatsVsTeamAverages(@PathVariable("year") String year,
			@PathVariable("playerFirstName") String firstName, @PathVariable("playerLastName") String lastName,
			@PathVariable("teamLocation") String teamLocation, @PathVariable("teamMascot") String teamMascot, 
			@PathVariable("opposingTeamLocation") String opposingTeamLocation, @PathVariable("opposingTeamMascot") String opposingTeamMascot) {
		
		try {
			String[] parsedValues = UrlUtils.parseNbaUrl(firstName, lastName, teamLocation, teamMascot, opposingTeamLocation, opposingTeamMascot);
			return statsVsTeamService.crawlForAverages(year, parsedValues[PLAYER_NAME], parsedValues[TEAM], parsedValues[OPPOSING_TEAM]);
		} catch (Exception e) {

			return new ArrayList<>(); 
		}
	}
}
