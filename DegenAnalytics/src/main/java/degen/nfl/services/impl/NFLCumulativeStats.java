package degen.nfl.services.impl;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import degen.common.FileUtils;
import degen.common.ProcessingException;
import degen.common.nfl.NFLUtils;

@Component
public class NFLCumulativeStats {
	//https://www.pro-football-reference.com/years/2017/week_1.htm
	private static final String BASE_URL = "https://www.pro-football-reference.com/years/";
	
	@Autowired FileUtils fileUtils; 
	@Autowired NFLUtils nflUtils; 
	
	public Map<Integer, List<Integer>> getOffensiveAndDefensiveCumulativeStats(String year, String week) throws ProcessingException {
		//Map<Integer, List<Integer>>
		
		for(int currentWeek = 1; currentWeek < Integer.parseInt(week); currentWeek++) {
			String url = BASE_URL + year + "/week_" + currentWeek + ".htm";
			Document doc = fileUtils.getHtmlPage(url); 
			if(doc != null) {
				Elements gameDivs = doc.getElementsByClass("game_summaries").get(0).getElementsByTag("div"); 
				
				for(int gameNum = 1; gameNum < gameDivs.size(); gameNum++) {
					//click final to get to game page
					Element gameDiv = gameDivs.get(gameNum); 
					String boxScoreLink = gameDiv.childNode(1).childNode(1).childNode(3)
											.childNode(5).childNode(1)
											.attr("href"); 
					
					//get box score stats for both home and away teams
					List<Map<Integer, Integer>> boxScoreStats = nflUtils.getBoxScoreStats(BASE_URL + boxScoreLink, "");
					
					//home team @ 0, away team @ 1 
					for(Map.Entry<Integer, Integer> homeEntry : boxScoreStats.get(0).entrySet()) {
						
					}
					
				}
			}
			else {
				throw new ProcessingException("Cannot retrieve all weekly updates"); 
			}
		}
		
		return null; 
	}

}
