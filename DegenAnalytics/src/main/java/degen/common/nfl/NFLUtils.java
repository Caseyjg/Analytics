package degen.common.nfl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import degen.common.ProcessingException;


@Component
public class NFLUtils {
	public List<Map<Integer, Integer>> getBoxScoreStats(String url, String teamName) throws ProcessingException {
		//need to figure out if selected team is home or away every single week
		Map<Integer, Integer> homeTeam = new HashMap<>();
		Map<Integer, Integer> awayTeam = new HashMap<>();
		Document doc;
		try {
			doc = Jsoup.connect(url).maxBodySize(Integer.MAX_VALUE).timeout(600000).get();
		} catch (IOException e) {
			return Collections.emptyList();
		}
		
		Node lineScoreTable = doc.getElementsByClass("linescore_wrap").get(0).childNode(1).childNode(3);
		
		String awayTeamName = String.valueOf(lineScoreTable.childNode(1).childNode(3).childNode(0).childNode(0)); 
		awayTeam.put(NFLWeeklyStats.LIST_INDEX, assignTeamIndex(awayTeamName));
		
		String homeTeamName = String.valueOf(lineScoreTable.childNode(3).childNode(3).childNode(0).childNode(0)); 
		homeTeam.put(NFLWeeklyStats.LIST_INDEX, assignTeamIndex(homeTeamName));
		
		Element tmp = doc.select("div#all_team_stats").get(0);
		String comment = tmp.childNode(5).toString().replaceAll("<!--|-->","");
		Document d = Jsoup.parse(comment);
		Element table = d.getElementById("team_stats");
		table = table.select("tbody").get(0);
		Elements rows = table.select("tr");
		
		//0
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS, Integer.parseInt(rows.get(0).childNode(1).childNode(0).outerHtml())); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS, Integer.parseInt(rows.get(0).childNode(2).childNode(0).outerHtml())); 
		//1
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_TD, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[2])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_TD, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[2])); 
		//2
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[0]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[1]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_YARDS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[2])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_YARDS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[2]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_TD, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[3])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_TD, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[3]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_INT, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[4])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_INT, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[4]));
		//3
		homeTeam.put(NFLWeeklyStats.SACKS, Integer.parseInt(rows.get(3).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.OPPOSING_SACKS, Integer.parseInt(rows.get(3).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		//5 (skip 4 -- 4 = 'net pass yards')
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS, Integer.parseInt(rows.get(5).childNode(1).childNode(0).outerHtml())); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS, Integer.parseInt(rows.get(5).childNode(2).childNode(0).outerHtml())); 
		//6
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED, Integer.parseInt(rows.get(6).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST, Integer.parseInt(rows.get(6).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		//7
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_TURNOVERS, Integer.parseInt(rows.get(7).childNode(1).childNode(0).outerHtml())); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_TURNOVERS, Integer.parseInt(rows.get(7).childNode(2).childNode(0).outerHtml())); 
		//8
		homeTeam.put(NFLWeeklyStats.OPPOSING_PENALTIES, Integer.parseInt(rows.get(8).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.PENALTIES, Integer.parseInt(rows.get(8).childNode(2).childNode(0).outerHtml().split("-")[0]));
		homeTeam.put(NFLWeeklyStats.OPPOSING_PENALTY_YARDS, Integer.parseInt(rows.get(8).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.PENALTY_YARDS, Integer.parseInt(rows.get(8).childNode(2).childNode(0).outerHtml().split("-")[1]));
		//9
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS, Integer.parseInt(rows.get(9).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS, Integer.parseInt(rows.get(9).childNode(2).childNode(0).outerHtml().split("-")[0]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS, Integer.parseInt(rows.get(9).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS, Integer.parseInt(rows.get(9).childNode(2).childNode(0).outerHtml().split("-")[1]));
		//10
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS, Integer.parseInt(rows.get(10).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS, Integer.parseInt(rows.get(10).childNode(2).childNode(0).outerHtml().split("-")[0]));
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS, Integer.parseInt(rows.get(10).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS, Integer.parseInt(rows.get(10).childNode(2).childNode(0).outerHtml().split("-")[1]));
		
		int min = Integer.parseInt(rows.get(11).childNode(1).childNode(0).outerHtml().split(":")[0]); 
		int sec = Integer.parseInt(rows.get(11).childNode(1).childNode(0).outerHtml().split(":")[1]); 
		//11
		homeTeam.put(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS, (min * 60) + sec); 
		
		min = Integer.parseInt(rows.get(11).childNode(2).childNode(0).outerHtml().split(":")[0]); 
		sec = Integer.parseInt(rows.get(11).childNode(2).childNode(0).outerHtml().split(":")[1]); 
		//11
		homeTeam.put(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS, (min * 60) + sec);
		
		
		//0
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS, Integer.parseInt(rows.get(0).childNode(2).childNode(0).outerHtml())); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS, Integer.parseInt(rows.get(0).childNode(1).childNode(0).outerHtml())); 
		//1
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_RUSH_TD, Integer.parseInt(rows.get(1).childNode(2).childNode(0).outerHtml().split("-")[2])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_RUSH_TD, Integer.parseInt(rows.get(1).childNode(1).childNode(0).outerHtml().split("-")[2])); 
		//2
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[0]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[1]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_YARDS, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[2])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_YARDS, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[2]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_TD, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[3])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_TD, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[3]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_PASS_INT, Integer.parseInt(rows.get(2).childNode(2).childNode(0).outerHtml().split("-")[4])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_PASS_INT, Integer.parseInt(rows.get(2).childNode(1).childNode(0).outerHtml().split("-")[4]));
		//3
		awayTeam.put(NFLWeeklyStats.SACKS, Integer.parseInt(rows.get(3).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.OPPOSING_SACKS, Integer.parseInt(rows.get(3).childNode(1).childNode(0).outerHtml().split("-")[0])); 
		//5 (skip 4 -- 4 = 'net pass yards')
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS, Integer.parseInt(rows.get(5).childNode(2).childNode(0).outerHtml())); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS, Integer.parseInt(rows.get(5).childNode(1).childNode(0).outerHtml())); 
		//6
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED, Integer.parseInt(rows.get(6).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST, Integer.parseInt(rows.get(6).childNode(1).childNode(0).outerHtml().split("-")[1])); 
		//7
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_TURNOVERS, Integer.parseInt(rows.get(7).childNode(2).childNode(0).outerHtml())); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_TURNOVERS, Integer.parseInt(rows.get(7).childNode(1).childNode(0).outerHtml())); 
		//8
		awayTeam.put(NFLWeeklyStats.OPPOSING_PENALTIES, Integer.parseInt(rows.get(8).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.PENALTIES, Integer.parseInt(rows.get(8).childNode(1).childNode(0).outerHtml().split("-")[0]));
		awayTeam.put(NFLWeeklyStats.OPPOSING_PENALTY_YARDS, Integer.parseInt(rows.get(8).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.PENALTY_YARDS, Integer.parseInt(rows.get(8).childNode(1).childNode(0).outerHtml().split("-")[1]));
		//9
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS, Integer.parseInt(rows.get(9).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS, Integer.parseInt(rows.get(9).childNode(1).childNode(0).outerHtml().split("-")[0]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS, Integer.parseInt(rows.get(9).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS, Integer.parseInt(rows.get(9).childNode(1).childNode(0).outerHtml().split("-")[1]));
		//10
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS, Integer.parseInt(rows.get(10).childNode(2).childNode(0).outerHtml().split("-")[0])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS, Integer.parseInt(rows.get(10).childNode(1).childNode(0).outerHtml().split("-")[0]));
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS, Integer.parseInt(rows.get(10).childNode(2).childNode(0).outerHtml().split("-")[1])); 
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS, Integer.parseInt(rows.get(10).childNode(1).childNode(0).outerHtml().split("-")[1]));
		
		min = Integer.parseInt(rows.get(11).childNode(2).childNode(0).outerHtml().split(":")[0]); 
		sec = Integer.parseInt(rows.get(11).childNode(2).childNode(0).outerHtml().split(":")[1]); 
		//11
		awayTeam.put(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS, (min * 60) + sec); 
		
		min = Integer.parseInt(rows.get(11).childNode(1).childNode(0).outerHtml().split(":")[0]); 
		sec = Integer.parseInt(rows.get(11).childNode(1).childNode(0).outerHtml().split(":")[1]); 
		//11
		awayTeam.put(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS, (min * 60) + sec);
		
		
		Element allgameinfo = doc.getElementById("all_game_info");
		comment = allgameinfo.childNode(5).toString().replaceAll("<!--|-->","");
		d = Jsoup.parse(comment);
		table = d.getElementById("game_info");
		table = table.select("tbody").get(0);
		rows = table.select("tr");

		//TODO need to figure out how to parse "Dallas Cowboys -6" etc

		/*String lineSpread = rows.get(4).child(1).childNode(0).outerHtml();
		String overUnderSpread = rows.get(5).childNode(1).childNode(0).outerHtml();
		String overOrUnder = rows.get(5).childNode(1).childNode(1).outerHtml().replace("<b>", "").replace("</b>", "").replace("(", "").replace(")", "");*/

		return Arrays.asList(homeTeam, awayTeam); 
	}

	public int assignTeamIndex(String teamName) throws ProcessingException {
		if(teamName.contains("Arizona Cardinals")) {
			return NFLTeams.CARDINALS;
		}
		else if(teamName.contains("San Francisco 49ers")) {
			return NFLTeams.NINERS;
		}
		else if(teamName.contains("Seattle Seahawks")) {
			return NFLTeams.SEAHAWKS;
		}
		else if(teamName.contains("Los Angeles Rams")) {
			return NFLTeams.RAMS;
		}
		else if(teamName.contains("Detroit Lions")) {
			return NFLTeams.LIONS;
		}
		else if(teamName.contains("Green Bay Packers")) {
			return NFLTeams.PACKERS;
		}
		else if(teamName.contains("Minnesota Vikings")) {
			return NFLTeams.VIKINGS;
		}
		else if(teamName.contains("Chicago Bears")) {
			return NFLTeams.BEARS;
		}
		else if(teamName.contains("Atlanta Falcons")) {
			return NFLTeams.FALCONS;
		}
		else if(teamName.contains("New Orleans Saints")) {
			return NFLTeams.SAINTS;
		}
		else if(teamName.contains("Carolina Panthers")) {
			return NFLTeams.PANTHERS;
		}
		else if(teamName.contains("Tampa Bay Buccaneers")) {
			return NFLTeams.BUCCANEERS;
		}
		else if(teamName.contains("Dallas Cowboys")) {
			return NFLTeams.COWBOYS;
		}
		else if(teamName.contains("New York Giants")) {
			return NFLTeams.GIANTS;
		}
		else if(teamName.contains("Washington Redskins")) {
			return NFLTeams.REDSKINS;
		}
		else if(teamName.contains("Philadelphia Eagles")) {
			return NFLTeams.EAGLES;
		}
		else if(teamName.contains("Denver Broncos")) {
			return NFLTeams.BRONCOS;
		}
		else if(teamName.contains("Kansas City Chiefs")) {
			return NFLTeams.CHIEFS;
		}
		else if(teamName.contains("Oakland Raiders")) {
			return NFLTeams.RAIDERS;
		}
		else if(teamName.contains("Los Angeles Chargers")) {
			return NFLTeams.CHARGERS;
		}
		else if(teamName.contains("Cincinnati Bengals")) {
			return NFLTeams.BENGALS;
		}
		else if(teamName.contains("Cleveland Browns")) {
			return NFLTeams.BROWNS;
		}
		else if(teamName.contains("Pittsburgh Steelers")) {
			return NFLTeams.STEELERS;
		}
		else if(teamName.contains("Baltimore Ravens")) {
			return NFLTeams.RAVENS;
		}
		else if(teamName.contains("Tennessee Titans")) {
			return NFLTeams.TITANS;
		}
		else if(teamName.contains("Houston Texans")) {
			return NFLTeams.TEXANS;
		}
		else if(teamName.contains("Jacksonville Jaguars")) {
			return NFLTeams.JAGUARS;
		}
		else if(teamName.contains("Indianapolis Colts")) {
			return NFLTeams.COLTS;
		}
		else if(teamName.contains("Buffalo Bills")) {
			return NFLTeams.BILLS;
		}
		else if(teamName.contains("New England Patriots")) {
			return NFLTeams.PATRIOTS;
		}
		else if(teamName.contains("New York Jets")) {
			return NFLTeams.JETS;
		}
		else if(teamName.contains("Miami Dolphins")) {
			return NFLTeams.DOLPHINS;
		}
		else {
			throw new ProcessingException("Team Name didn't match -- try 'Dallas Cowboys' format or let me know"); 
		}
	}
}
