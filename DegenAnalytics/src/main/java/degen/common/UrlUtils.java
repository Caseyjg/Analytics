package degen.common;

import org.springframework.stereotype.Component;

@Component
public class UrlUtils {
	private static int PLAYER_NAME = 0;
	private static int TEAM = 1; 
	private static int OPPOSING_TEAM = 2; 
	
	public static String[] parseNbaUrl(String firstName, String lastName, String teamLocation, 
			String teamMascot, String opposingTeamLocation, String opposingTeamMascot) {
		
		String[] toReturn = new String[3]; 
		toReturn[PLAYER_NAME] = firstName + " " + lastName;
		toReturn[TEAM] = teamLocation + " " + teamMascot; 
		toReturn[OPPOSING_TEAM] = opposingTeamLocation + " " + opposingTeamMascot; 
		
		return toReturn; 
	}

}
