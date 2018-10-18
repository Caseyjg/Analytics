package degen;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import degen.common.FileUtils;
import degen.common.ProcessingException;
import degen.common.nfl.NFLUtils;
import degen.common.nfl.NFLWeeklyStats;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DegenAnalyticsApplicationTests {
	@Autowired FileUtils fileUtils; 
	
	@Test
	public void testHomeTeamBoxScoreSteelersBrownsWeekOne() throws ProcessingException {
		NFLUtils nflUtils = new NFLUtils();
		Map<Integer, Integer> results = nflUtils.getBoxScoreStats("https://www.pro-football-reference.com/boxscores/201709100cle.htm", "Cleveland Browns").get(0);
		//0
		assertEquals(16, (int)results.get(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS));
		assertEquals(20, (int)results.get(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS));
		//1
		assertEquals(17, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS));
		assertEquals(35, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_TD));
		assertEquals(25, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS));
		assertEquals(57, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_TD));
		//2
		assertEquals(24, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS));
		assertEquals(36, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS));
		assertEquals(263, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_YARDS));
		assertEquals(2, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_TD));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_INT));
		assertEquals(20, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS));
		assertEquals(30, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS));
		assertEquals(222, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_YARDS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_TD));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_INT));
		//3
		assertEquals(1, (int)results.get(NFLWeeklyStats.SACKS));
		assertEquals(7, (int)results.get(NFLWeeklyStats.OPPOSING_SACKS));
		//5
		assertEquals(290, (int)results.get(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS));
		assertEquals(237, (int)results.get(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS));
		//6
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST));
		//7
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_TURNOVERS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_TURNOVERS));
		//8
		assertEquals(13, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTIES));
		assertEquals(144, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTY_YARDS));
		assertEquals(4, (int)results.get(NFLWeeklyStats.PENALTIES));
		assertEquals(61, (int)results.get(NFLWeeklyStats.PENALTY_YARDS));
		//9
		assertEquals(5, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(13, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS));
		assertEquals(3, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(12, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS));
		//10
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS));
		assertEquals(2, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(2, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS));
		//11
		assertEquals(1730, (int)results.get(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS));
		assertEquals(1870, (int)results.get(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS));
	}
	
	@Test
	public void testHomeTeamBoxScoreCowboysGiantsWeekOne() throws ProcessingException {
		NFLUtils nflUtils = new NFLUtils();
		Map<Integer, Integer> results = nflUtils.getBoxScoreStats("https://www.pro-football-reference.com/boxscores/201709100dal.htm", "Dallas Cowboys").get(0);
		//0
		assertEquals(13, (int)results.get(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS));
		assertEquals(22, (int)results.get(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS));
		//1
		assertEquals(12, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS));
		assertEquals(35, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_TD));
		assertEquals(31, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS));
		assertEquals(129, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_TD));
		//2
		assertEquals(29, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS));
		assertEquals(38, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS));
		assertEquals(220, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_TD));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_INT));
		assertEquals(24, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS));
		assertEquals(39, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS));
		assertEquals(268, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_YARDS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_TD));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_INT));
		//3
		assertEquals(3, (int)results.get(NFLWeeklyStats.SACKS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OPPOSING_SACKS));
		//5
		assertEquals(233, (int)results.get(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS));
		assertEquals(392, (int)results.get(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS));
		//6
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST));
		//7
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_TURNOVERS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_TURNOVERS));
		//8
		assertEquals(6, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTIES));
		assertEquals(55, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTY_YARDS));
		assertEquals(5, (int)results.get(NFLWeeklyStats.PENALTIES));
		assertEquals(50, (int)results.get(NFLWeeklyStats.PENALTY_YARDS));
		//9
		assertEquals(4, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(12, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS));
		assertEquals(8, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(15, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS));
		//10
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS));
		//11
		assertEquals(1546, (int)results.get(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS));
		assertEquals(2054, (int)results.get(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS));
	}
	
	@Test
	public void testAwayTeamBoxScoreCowboysGiantsWeekOne() throws ProcessingException {
		NFLUtils nflUtils = new NFLUtils();
		Map<Integer, Integer> results = nflUtils.getBoxScoreStats("https://www.pro-football-reference.com/boxscores/201709100dal.htm", "New York Giants").get(1);
		//0
		assertEquals(13, (int)results.get(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS));
		assertEquals(22, (int)results.get(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS));
		//1
		assertEquals(12, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS));
		assertEquals(35, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_TD));
		assertEquals(31, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS));
		assertEquals(129, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_TD));
		//2
		assertEquals(29, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS));
		assertEquals(38, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS));
		assertEquals(220, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_TD));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_INT));
		assertEquals(24, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS));
		assertEquals(39, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS));
		assertEquals(268, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_YARDS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_TD));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_INT));
		//3
		assertEquals(3, (int)results.get(NFLWeeklyStats.OPPOSING_SACKS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.SACKS));
		//5
		assertEquals(233, (int)results.get(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS));
		assertEquals(392, (int)results.get(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS));
		//6
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED));
		//7
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_TURNOVERS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_TURNOVERS));
		//8
		assertEquals(5, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTIES));
		assertEquals(50, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTY_YARDS));
		assertEquals(6, (int)results.get(NFLWeeklyStats.PENALTIES));
		assertEquals(55, (int)results.get(NFLWeeklyStats.PENALTY_YARDS));
		//9
		assertEquals(4, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(12, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS));
		assertEquals(8, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(15, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS));
		//10
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS));
		//11
		assertEquals(1546, (int)results.get(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS));
		assertEquals(2054, (int)results.get(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS));
	}

	@Test
	public void testHomeTeamBoxScoreSteelersJets2014() throws ProcessingException {
		NFLUtils nflUtils = new NFLUtils();
		Map<Integer, Integer> results = nflUtils.getBoxScoreStats("https://www.pro-football-reference.com/boxscores/201411090nyj.htm", "New York Jets").get(0);
		//0
		assertEquals(15, (int)results.get(NFLWeeklyStats.OFFENSIVE_FIRST_DOWNS));
		assertEquals(22, (int)results.get(NFLWeeklyStats.DEFENSIVE_FIRST_DOWNS));
		//1
		assertEquals(36, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_ATTEMPTS));
		assertEquals(150, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_RUSH_TD));
		assertEquals(17, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_ATTEMPTS));
		assertEquals(36, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_YARDS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_RUSH_TD));
		//2
		assertEquals(10, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_COMPLETIONS));
		assertEquals(18, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_ATTEMPTS));
		assertEquals(132, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_YARDS));
		assertEquals(2, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_TD));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_PASS_INT));
		assertEquals(30, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_COMPLETIONS));
		assertEquals(43, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_ATTEMPTS));
		assertEquals(343, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_YARDS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_TD));
		assertEquals(2, (int)results.get(NFLWeeklyStats.DEFENSIVE_PASS_INT));
		//3
		assertEquals(4, (int)results.get(NFLWeeklyStats.OPPOSING_SACKS));
		assertEquals(2, (int)results.get(NFLWeeklyStats.SACKS));
		//5
		assertEquals(275, (int)results.get(NFLWeeklyStats.OFFENSIVE_TOTAL_YARDS));
		assertEquals(362, (int)results.get(NFLWeeklyStats.DEFENSIVE_TOTAL_YARDS));
		//6
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FUMBLES_LOST));
		assertEquals(2, (int)results.get(NFLWeeklyStats.DEFENSIVE_FUMBLES_RECOVERED));
		//7
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_TURNOVERS));
		assertEquals(4, (int)results.get(NFLWeeklyStats.DEFENSIVE_TURNOVERS));
		//8
		assertEquals(3, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTIES));
		assertEquals(20, (int)results.get(NFLWeeklyStats.OPPOSING_PENALTY_YARDS));
		assertEquals(9, (int)results.get(NFLWeeklyStats.PENALTIES));
		assertEquals(75, (int)results.get(NFLWeeklyStats.PENALTY_YARDS));
		//9
		assertEquals(6, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(15, (int)results.get(NFLWeeklyStats.OFFENSIVE_THIRD_DOWN_ATTEMPTS));
		assertEquals(6, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_CONVERSIONS));
		assertEquals(11, (int)results.get(NFLWeeklyStats.DEFENSIVE_THIRD_DOWN_ATTEMPTS));
		//10
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.OFFENSIVE_FOURTH_DOWN_ATTEMPTS));
		assertEquals(0, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_CONVERSIONS));
		assertEquals(1, (int)results.get(NFLWeeklyStats.DEFENSIVE_FOURTH_DOWN_ATTEMPTS));
		//11
		assertEquals(1833, (int)results.get(NFLWeeklyStats.OFFENSIVE_TIME_OF_POSSESION_SECONDS));
		assertEquals(1767, (int)results.get(NFLWeeklyStats.DEFENSIVE_TIME_OF_POSSESION_SECONDS));
	}
	
	
}
