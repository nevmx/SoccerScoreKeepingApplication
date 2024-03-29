package ca.mcgill.ecse321.soccerscorekeeping.test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.soccerscorekeeping.admin.managerTools;
import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Infraction;
import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Shot;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;
import ca.mcgill.ecse321.soccerscorekeeping.persistence.XStreamPersistence;

public class TestController {

	@Before
	public void setUp() throws Exception 
	{
		Manager m = Manager.getInstance();
		XStreamPersistence.setFilename("src\\ca\\mcgill\\ecse321\\soccerscorekeeping\\test\\controller\\test.xml");
		XStreamPersistence.setAlias("Team",Team.class);
		XStreamPersistence.setAlias("Player",Player.class);
		XStreamPersistence.setAlias("Shot",Shot.class);
		XStreamPersistence.setAlias("Infraction",Infraction.class);
		XStreamPersistence.setAlias("Manager",Manager.class);
		XStreamPersistence.setAlias("Match",Match.class);
		
	}

	@After
	public void tearDown() throws Exception 
	{
		Manager m = Manager.getInstance();
		m.delete();
	}

	@Test
	public void test() 
	{
		Manager m = Manager.getInstance();
		
		//Check manager in empty
		assertEquals(0,m.getTeams().size());
		assertEquals(0,m.getMatches().size());
		
		managerTools mt = new managerTools();
		
		mt.createTeam("Pakistan");
		mt.createTeam("England");
		
		assertEquals("Pakistan",m.getTeam(0).getName());
		assertEquals("England",m.getTeam(1).getName());
		
		mt.createPlayer("Younis", m.getTeam(0));
		mt.createPlayer("Imran", m.getTeam(0));
		
		mt.createPlayer("Finn", m.getTeam(1));
		mt.createPlayer("Cook", m.getTeam(1));
		
		controller c = new controller();
		
		c.createMatch("Pakistan","England");
		
		Match m1 = m.getMatche(0);
		
		c.createShot("Younis", m1, true);
		c.createShot("Cook", m1, false);
		c.createShot("Imran", m1, true);
		
		c.createInfraction("Younis",m1,"RED",true);
		c.createInfraction("Cook",m1,"YELLOW",true);
		c.createInfraction("Finn",m1,"RED",false);
		c.createInfraction("Cook",m1,"YELLOW",false);
		
		c.chooseWinner(m1);
		
	}

}
