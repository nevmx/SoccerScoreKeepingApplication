package ca.mcgill.ecse321.soccerscorekeeping.admin;

import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;
import ca.mcgill.ecse321.soccerscorekeeping.persistence.XStreamPersistence;

public class managerTools 
{
	public managerTools()
	{
		
	}
	
	public boolean createTeam(String name)
	{
		if(name!=null)
		{
			Manager m = Manager.getInstance();
			Team t1= new Team(name);
			m.addTeam(t1);
			XStreamPersistence.saveToXMLwithXStream(m);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void createPlayer(String playerName, Team team)
	{
		Manager m = Manager.getInstance();
		Player p1 = new Player(playerName, team);
		int index = m.indexOfTeam(team);
		m.getTeam(index).addPlayer(p1);
		XStreamPersistence.saveToXMLwithXStream(m);
	}
	
	public String createPlayer(String playerName, String teamName)
	{
		if(playerName==null || teamName==null || playerName=="" || teamName == "")
		{
			return "The team/player name cannot be empty.";
		}
		
		Manager m = Manager.getInstance();
		
		Team temp = new Team();
		
		for(Team t : m.getTeams())
		{
			if(t.getName().equals(teamName))
			{
				temp=t;
			}
		}
		
		if(temp.getName()==null)
		{
			return "That team does not exist";
		}
		
		Player p1 = new Player(playerName,temp);
		
		int index = m.indexOfTeam(temp);
		m.getTeam(index).addPlayer(p1);
		XStreamPersistence.saveToXMLwithXStream(m);
		
		return null;
	}
}
