package ca.mcgill.ecse321.soccerscorekeeping.admin;

public class authentication 
{
	final private String managerPassword = "hello";
	
	public authentication()
	{
		
	}
	
	public boolean authenticate(char[] password)
	{
		for(int i=0;i<password.length;i++)
		{
			if(!(managerPassword.charAt(i)==password[i]))
			{
				return false;
			}
		}
		return true;
	}
}
