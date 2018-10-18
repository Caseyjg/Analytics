package degen.nfl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import degen.common.ProcessingException;
import degen.nfl.services.impl.NFLCumulativeStats;
import degen.nfl.services.impl.YearlyOverallAverages;

@RestController
public class NFLController {
	
	@Autowired private YearlyOverallAverages yearlyOverallAverages;
	@Autowired private NFLCumulativeStats cumulativeStats; 
	
	@ResponseBody
	@RequestMapping("/nfl-team-stats/{year}/{teamLocation}/{teamMascot}") 
	public List<Double> getTeamYearlyAverages( @PathVariable("year") String year, @PathVariable("teamLocation") String teamLocation, 
			@PathVariable("teamMascot") String teamMascot ) {	
		try {
			String url = yearlyOverallAverages.createTeamPageUrl(year, teamLocation + " " + teamMascot);
			return yearlyOverallAverages.getTeamYearlyAverages(url); 
		} catch (Exception e) {
			return new ArrayList<>(); 
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/nfl-team-stats/{year}/{week}/{teamLocation}/{teamMascot}") 
	public Map<Integer, Double> getTeamYearlyAverages_untilWeek( @PathVariable("year") String year, @PathVariable("week") int untilWeek , 
			@PathVariable("teamLocation") String teamLocation, @PathVariable("teamMascot") String teamMascot) {	
		String url;
		try {
		url = yearlyOverallAverages.createTeamPageUrl(year, teamLocation + " " + teamMascot);
		} catch(Exception e) {
			return new HashMap<>();
		}
		return null; 
		//return yearlyOverallAverages.getTeamYearlyAveragesUntilWeek(url, untilWeek, teamLocation + " " + teamMascot); 
		
	}
	
	@ResponseBody
	@RequestMapping("/temp/{year}/{week}") 
	public Map<Integer, Double> getStats( @PathVariable("year") String year, @PathVariable("week") String week ) {	
		
		try {
			cumulativeStats.getOffensiveAndDefensiveCumulativeStats(year, week);
		} catch (ProcessingException e) {
			return null; 
		} 
		return null;
	}

}
