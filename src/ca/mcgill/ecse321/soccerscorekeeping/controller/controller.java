package ca.mcgill.ecse321.soccerscorekeeping.controller;

import ca.mcgill.ecse321.soccerscorekeeping.model.Infraction;
import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Shot;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;
import ca.mcgill.ecse321.soccerscorekeeping.persistence.XStreamPersistence;

public class controller 
{
	public controller()
	{
		
	}
	
	public void createMatch(String name,Team team1, Team team2)
	{
		Match m1 = new Match(name,team1, team2);
		Manager m = Manager.getInstance();
		m.addMatche(m1);
		XStreamPersistence.saveToXMLwithXStream(m);
	}
	
	public String createMatch(String team1, String team2)
	{
		Manager m = Manager.getInstance();
		Team t1 = new Team();
		Team t2 = new Team();
		
		for(Team team:m.getTeams())
		{
			if(team.getName().equals(team1))
			{
				t1=team;
			}
			else if(team.getName().equals(team2))
			{
				t2=team;
			}
		}
		
		if(t1==null || t2==null)
		{
			return "The teams do not exist";
		}
		
		Match m1 = new Match(t1.getName()+" v "+t2.getName(),t1, t2);
		m.addMatche(m1);
		XStreamPersistence.saveToXMLwithXStream(m);
		return null;
	}
	
	public void createShot(Player player,Match match, boolean goal)
	{
		Shot s = new Shot(goal);
		player.addShot(s);
		match.addShot(s);
		
		if(goal==true)
		{
			if(player.getTeam().getName().equals(match.getTeam(0).getName()))
			{
				match.incrementGoals1();
			}
			else
			{
				match.incrementGoals2();
			}
			
		}
		
		Manager m = Manager.getInstance();
		XStreamPersistence.saveToXMLwithXStream(m);
	}
	
	//Assuming no two players have the same name.
	public String createShot(String player,Match match, boolean goal)
	{
		Manager m = Manager.getInstance();
		Shot s = new Shot(goal);
		
		Player p1 = new Player();
		for(Team t : m.getTeams())
		{
			for(Player p : t.getPlayers())
			{
				if(p.getName().equals(player))
				{
					p1=p;
				}
			}
		}
		
		if(p1.getName()==null)
		{
			return "Player does not exist";
		}
		
		match.addShot(s);
		
		if(goal==true)
		{
			if(p1.getTeam().getName().equals(match.getTeam(0).getName()))
			{
				match.incrementGoals1();
			}
			else
			{
				match.incrementGoals2();
			}
			
		}
		
		XStreamPersistence.saveToXMLwithXStream(m);
		return null;
	}
	
	public void createInfraction(Player player, Match match, String type, boolean isPenalty)
	{
		Infraction i1 = new Infraction(type, isPenalty);
		match.addInfraction(i1);
		player.addInfraction(i1);
		Manager m = Manager.getInstance();
		XStreamPersistence.saveToXMLwithXStream(m);
	}
	
	public String createInfraction(String player, Match match, String type, boolean isPenalty)
	{	
		Manager m =Manager.getInstance();
		Infraction i1 = new Infraction(type, isPenalty);
		
		Player p1 = new Player();
		for(Team t : m.getTeams())
		{
			for(Player p : t.getPlayers())
			{
				if(p.getName().equals(player))
				{
					p1=p;
				}
			}
		}
		
		if(p1.getName()==null)
		{
			return "Player does not exist";
		}
		
		match.addInfraction(i1);
		p1.addInfraction(i1);
		
		XStreamPersistence.saveToXMLwithXStream(m);
		return null;
	}
	
	public void chooseWinner(Match match)
	{
		int a = match.getGoals1();
		int b = match.getGoals2();
		
		if(a<b)
		{
			match.getTeam(1).addWin();
		}
		else if(a>b)
		{
			match.getTeam(0).addWin();
		}
		else
		{
			match.getTeam(0).addDraw();
			match.getTeam(1).addDraw();
		}
		
		Manager m = Manager.getInstance();
		XStreamPersistence.saveToXMLwithXStream(m);
	}
	
	
}
