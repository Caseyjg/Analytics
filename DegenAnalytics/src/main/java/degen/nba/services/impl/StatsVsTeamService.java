package degen.nba.services.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StatsVsTeamService {

	private static final String BASE_URL = "https://www.basketball-reference.com/";
	private static final int POINTS = 0;
	private static final int REBOUNDS = 1;
	private static final int ASSISTS = 2; 
	
	@Value("${2014-15-url-ending}")
	private String yearSpecificPlaceholder_14_15; 
	
	@Value("${2015-16-url-ending}")
	private String yearSpecificPlaceholder_15_16; 
	
	@Value("${2016-17-url-ending}")
	private String yearSpecificPlaceholder_16_17; 
	
	@Value("${2017-18-url-ending}")
	private String yearSpecificPlaceholder_17_18; 
	
	public List<List<Integer>> crawlPerGameTotals(String year, String playerName, String teamName, String opposingTeamName) throws Exception { 
		playerName = parseUrlInput(playerName); 
		teamName = parseUrlInput(teamName); 
		opposingTeamName = parseUrlInput(opposingTeamName);
		
		return getTotals(getLinksToGames(year, teamName, opposingTeamName), parseUrlInput(playerName));
	}
	
	public List<Double> crawlForAverages(String year, String playerName, String teamName, String opposingTeamName) throws Exception {
		playerName = parseUrlInput(playerName); 
		teamName = parseUrlInput(teamName); 
		opposingTeamName = parseUrlInput(opposingTeamName);
		
		List<List<Integer>> totals = getTotals(getLinksToGames(year, teamName, opposingTeamName), playerName);
		
		int pointSum = 0, reboundSum = 0, assistSum = 0; 
		for(int temp : totals.get(POINTS))
			pointSum += temp;
		
		for(int temp : totals.get(REBOUNDS))
			reboundSum += temp;
		
		for(int temp : totals.get(ASSISTS))
			assistSum += temp;
		
		return Arrays.asList( (double)pointSum/totals.get(POINTS).size(), (double)reboundSum/totals.get(REBOUNDS).size(),
				(double)assistSum/totals.get(ASSISTS).size()); 
	}
	
	private List<List<Integer>> getTotals(ArrayList<String> links, String playerName) throws IOException {
		ArrayList<Integer> points = new ArrayList<>();
		ArrayList<Integer> rebounds = new ArrayList<>();
		ArrayList<Integer> assists = new ArrayList<>();
		
		for(String link : links) {
			URL url = new URL(BASE_URL + link);
			InputStream is = url.openStream();
			try(BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
				StringBuilder sb = new StringBuilder(); 
				String temp; 
				
				while((temp = in.readLine()) != null) {
					sb.append(temp); 
				}
				
				is.close();
				Document doc = Jsoup.parse(sb.toString());
				
				// should find a better way to find these tables
				Elements tablesOnPage = doc.select("table"); 
				ArrayList<Element> tables = new ArrayList<>(); 
				for(Element table : tablesOnPage) {
					if(table.id().contains("basic")) 
						tables.add(table); 
				}
				
				boolean foundTable = false; 
				
				for(Element table : tables) {
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
				
			} catch (FileNotFoundException e) {
		         System.out.println(e.getMessage());
		         return new ArrayList<>();
		    }
		    catch (IOException e) {
		        System.out.println(e.getMessage());
		        return new ArrayList<>();
		    }
		}
		return Arrays.asList(points, rebounds, assists);
	}
	
	private String createUrl(String year, String teamName) throws Exception {
		String url = BASE_URL; 
		//https://www.basketball-reference.com/teams/MIN/2017_games.html
		url += "teams/";
		String yearSpecificPlaceholder = determinePlaceholder(year); 
		
		if(teamName.equals("Golden State Warriors")) {
			url += "GSW/";
		}
		else if(teamName.equals("San Antonio Spurs")) {
			url += "SAS/";
		}
		else if(teamName.equals("Houston Rockets")) {
			url += "HOU/";
		}
		else if(teamName.equals("Los Angeles Clippers")) {
			url += "LAC/";
		}
		else if(teamName.equals("Utah Jazz")) {
			url += "UTA/";
		}
		else if(teamName.equals("Toronto Raptors")) {
			url += "TOR/";
		}
		else if(teamName.equals("Cleveland Cavaliers")) {
			url += "CLE/";
		}
		else if(teamName.equals("Boston Celtics")) {
			url += "BOS/";
		}
		else if(teamName.equals("Washington Wizards")) {
			url += "WAS/";
		}
		else if(teamName.equals("Oklahoma City Thunder")) {
			url += "OKC/";
		}
		else if(teamName.equals("Memphis Grizzlies")) {
			url += "MEM/";
		}
		else if(teamName.equals("Miami Heat")) {
			url += "MIA/";
		}
		else if(teamName.equals("Denver Nuggets")) {
			url += "DEN/";
		}
		else if(teamName.equals("Charlotte Hornets")) {
			url += "CHO/";
		}
		else if(teamName.equals("Chicago Bulls")) {
			url += "CHI/";
		}
		else if(teamName.equals("Portland Trail Blazers")) {
			url += "POR/";
		}
		else if(teamName.equals("Milwaukee Bucks")) {
			url += "MIL/";
		}
		else if(teamName.equals("Indiana Pacers")) {
			url += "IND/";
		}
		else if(teamName.equals("Minnesota Timberwolves")) {
			url += "MIN/";
		}
		else if(teamName.equals("Atlanta Hawks")) {
			url += "ATL/";
		}
		else if(teamName.equals("Detroit Pistons")) {
			url += "DET/";
		}
		else if(teamName.equals("New Orleans Pelicans")) {
			url += "NOP/";
		}
		else if(teamName.equals("Dallas Mavericks")) {
			url += "DAL/";
		}
		else if(teamName.equals("Sacramento Kings")) {
			url += "SAC/";
		}
		else if(teamName.equals("New York Knicks")) {
			url += "NYK/";
		}
		else if(teamName.equals("Phoenix Suns")) {
			url += "PHO/";
		}
		else if(teamName.equals("Philadelphia 76ers")) {
			url += "PHI/";
		}
		else if(teamName.equals("Los Angeles Lakers")) {
			url += "LAL/";
		}
		else if(teamName.equals("Brooklyn Nets")) {
			url += "BRK/";
		}
		else if(teamName.equals("Orlando Magic")) {
			url += "ORL/";
		}
		else {
			throw new Exception("Team Name didn't match -- try 'Minnesota Timberwolves' format"); 
		}
		
		url += yearSpecificPlaceholder;
		return url; 
	}
	
	private String determinePlaceholder(String year) {
		if(year.trim().equals("2014"))
			return yearSpecificPlaceholder_14_15; 
		
		if(year.trim().equals("2015"))
			return yearSpecificPlaceholder_15_16; 
		
		if(year.trim().equals("2016"))
			return yearSpecificPlaceholder_16_17; 
		
		if(year.trim().equals("2017"))
			return yearSpecificPlaceholder_17_18;
		
		return year;
	}

	private ArrayList<String> getLinksToGames(String year, String teamName, String opposingTeamName) throws Exception {
		ArrayList<String> links = new ArrayList<>();
		URL url = new URL(createUrl(year, teamName));
		InputStream is = url.openStream();
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
			StringBuilder sb = new StringBuilder(); 
			String temp; 
			
			while((temp = in.readLine()) != null) {
				sb.append(temp); 
			}
			
			is.close();
			Document doc = Jsoup.parse(sb.toString());
			Element table = doc.select("#games").select("tbody").get(0); 
			Elements rows = table.select("tr");
			
			for(int i = 0; i < rows.size(); i++) {
				Element row = rows.get(i); 
				Elements cols = row.select("td"); 
				
				if(!cols.isEmpty() && cols.get(5).text().trim().equals(opposingTeamName.trim())) {
					links.add(cols.get(3).select("a").attr("href"));
				}
			}
			return links; 
			
		} catch (FileNotFoundException e) {
	         System.out.println(e.getMessage());
	         return new ArrayList<>();
	    }
	    catch (IOException e) {
	        System.out.println(e.getMessage());
	        return new ArrayList<>();
	    }
		
	}
	
	private String parseUrlInput(String input) {
		return input.replaceAll(Pattern.quote("+"), " ");
	}

}
