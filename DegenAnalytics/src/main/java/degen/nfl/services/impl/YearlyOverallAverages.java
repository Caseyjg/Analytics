package degen.nfl.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import degen.common.FileUtils;
import degen.common.nfl.NFLUtils;

@Component
public class YearlyOverallAverages {
	private static final String BASE_URL = "https://www.pro-football-reference.com/teams/";
	
	@Autowired NFLUtils nflUtils; 
	@Autowired FileUtils fileUtils; 
	
	public List<Double> getTeamYearlyAverages(String url) throws IOException { 
		Document doc = fileUtils.getHtmlPage(url); 
		
		
		Element table = doc.getElementById("team_stats");
		table = table.select("tbody").get(0);
		Elements rows = table.select("tr");
		

		
		/*for(Element table : tables) {
			table = table.select("tbody").get(0);
			Elements rows = table.select("tr");

			for(int i = 0; i < rows.size(); i++) {
				Element row = rows.get(i); 
				Elements cols = row.select("td"); 
				Element th = row.select("th").get(0); 
				
				if(!cols.isEmpty() && th.select("a").text().trim().equals(playerName.trim())) {
					points.add(Integer.parseInt(cols.get(18).text()));
					rebounds.add(Integer.parseInt(cols.get(12).text()));
					assists.add(Integer.parseInt(cols.get(13).text()));
					foundTable = true; 
					break;
				}
			}
			if(foundTable)
				break;
		}
		*/
		
		
		return null; 
	}
	
	/*public Map<Integer, Double> getTeamYearlyAveragesUntilWeek(String url, int untilWeek, String teamName) {	
		Map<Integer, Integer> runningTotals = new HashMap<>();
		Document doc = fileUtils.getHtmlPage(url); 		
		
		Element table = doc.getElementById("games");
		table = table.select("tbody").get(0);
		Elements rows = table.select("tr");
		
		for(int i = 0; i < untilWeek; i++) {
			//click boxscore link and add up values, return to sum in this loop
			
			//is home team = 7th <td>
			Element gameLocation = rows.get(i).select("td").get(7);
			boolean isHomeTeam = !gameLocation.outerHtml().contains("@");
			
			Element boxScoreTd = rows.get(i).select("td").get(3);
			if(!boxScoreTd.childNodes().isEmpty()) {
				//to account for bye weeks -- there is no box score link available
				String boxScoreUrl = "https://www.pro-football-reference.com" + boxScoreTd.childNode(0).attr("href");
				Map<Integer, Integer> boxScoreStats = nflUtils.getBoxScoreStats(boxScoreUrl, isHomeTeam, teamName);
				
				boxScoreStats.forEach((key, value) -> runningTotals.merge(key, value, (v1, v2) -> v1 + v2)); 
			}
		}
		
		Map<Integer, Double> averages = new HashMap<>();
		for (Entry<Integer, Integer> e : runningTotals.entrySet()) {
			averages.put(e.getKey(), (double)e.getValue() / (untilWeek));
		}
		
		return averages; 
	}*/

	public String createTeamPageUrl(String year, String teamName) throws Exception {
		String url = BASE_URL; 
		
		if(teamName.equals("Arizona Cardinals")) {
			url += "crd/";
		}
		else if(teamName.equals("San Francisco 49ers")) {
			url += "sfo/";
		}
		else if(teamName.equals("Seattle Seahawks")) {
			url += "sea/";
		}
		else if(teamName.equals("Los Angeles Rams")) {
			url += "ram/";
		}
		else if(teamName.equals("Detroit Lions")) {
			url += "det/";
		}
		else if(teamName.equals("Green Bay Packers")) {
			url += "gnb/";
		}
		else if(teamName.equals("Minnesota Vikings")) {
			url += "min/";
		}
		else if(teamName.equals("Chicago Bears")) {
			url += "chi/";
		}
		else if(teamName.equals("Atlanta Falcons")) {
			url += "atl/";
		}
		else if(teamName.equals("New Orleans Saints")) {
			url += "nor/";
		}
		else if(teamName.equals("Carolina Panthers")) {
			url += "car/";
		}
		else if(teamName.equals("Tampa Bay Buccaneers")) {
			url += "tam/";
		}
		else if(teamName.equals("Dallas Cowboys")) {
			url += "dal/";
		}
		else if(teamName.equals("New York Giants")) {
			url += "nyg/";
		}
		else if(teamName.equals("Washington Redskins")) {
			url += "was/";
		}
		else if(teamName.equals("Philadelphia Eagles")) {
			url += "phi/";
		}
		else if(teamName.equals("Denver Broncos")) {
			url += "den/";
		}
		else if(teamName.equals("Kansas City Chiefs")) {
			url += "kan/";
		}
		else if(teamName.equals("Oakland Raiders")) {
			url += "rai/";
		}
		else if(teamName.equals("Los Angeles Chargers")) {
			url += "sdg/";
		}
		else if(teamName.equals("Cincinnati Bengals")) {
			url += "cin/";
		}
		else if(teamName.equals("Cleveland Browns")) {
			url += "cle/";
		}
		else if(teamName.equals("Pittsburgh Steelers")) {
			url += "pit/";
		}
		else if(teamName.equals("Baltimore Ravens")) {
			url += "rav/";
		}
		else if(teamName.equals("Tennessee Titans")) {
			url += "oti/";
		}
		else if(teamName.equals("Houston Texans")) {
			url += "htx/";
		}
		else if(teamName.equals("acksonville Jaguars")) {
			url += "jax/";
		}
		else if(teamName.equals("Indianapolis Colts")) {
			url += "clt/";
		}
		else if(teamName.equals("Buffalo Bills")) {
			url += "buf/";
		}
		else if(teamName.equals("New England Patriots")) {
			url += "nwe/";
		}
		else if(teamName.equals("New York Jets")) {
			url += "nyj/";
		}
		else if(teamName.equals("Miami Dolphins")) {
			url += "mia/";
		}
		else {
			throw new Exception("Team Name didn't match -- try 'Dallas Cowboys' format or let me know"); 
		}
		
		url += "/" + year + ".htm";
		return url; 
	}
}
