package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.admin.managerTools;
import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class ManagerTools extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -95309694738439034L;
	
	private JLabel errorLabel;
	private JLabel addTeamLabel;
	private JLabel addPlayerLabel;
	
	private JLabel teamNameLabel;
	private JLabel playerNameLabel;
	private JLabel selectTeamLabel;
	
	private JTextField teamName;
	private JTextField playerName;
	private JButton addTeam;
	private JButton addPlayer;
	private JComboBox<String> listOfTeams;
	private String choice;
	private JPanel mainPanel;
	
	public ManagerTools()
	{
		initializeComponents();
	}
	
	public void initializeComponents()
	{
		//init comps
		errorLabel = new JLabel();
		addTeamLabel = new JLabel("Add Team: ");
		addPlayerLabel = new JLabel("Add Player: ");
		
		teamNameLabel = new JLabel("Team Name: ");
		playerNameLabel = new JLabel("Player Name: ");
		selectTeamLabel = new JLabel("Select Team: ");
		
		teamName = new JTextField("",20);
		playerName = new JTextField("",20);
		
		addTeam = new JButton("Add Team");
		addPlayer = new JButton("Add Player");
		
		listOfTeams = new JComboBox<String>();
		mainPanel = new JPanel(new GridBagLayout());
		
		//Design the JFrame
		this.getContentPane().add(mainPanel,BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();
		c.insets= new Insets(10,10,10,10);
		
		c.gridx=0;
		c.gridy=0;
		mainPanel.add(errorLabel,c);
		
		c.gridy++;
		mainPanel.add(addTeamLabel,c);
		
		c.gridy++;
		mainPanel.add(teamNameLabel, c);
		
		c.gridx++;
		mainPanel.add(teamName,c);
		
		c.gridx++;
		mainPanel.add(addTeam,c);
		
		c.gridy=3;
		c.gridx=0;
		mainPanel.add(addPlayerLabel,c);
		
		c.gridy++;
		mainPanel.add(playerNameLabel,c);
		
		c.gridx++;
		mainPanel.add(playerName,c);
		
		c.gridx++;
		mainPanel.add(selectTeamLabel,c);
		
		c.gridx++;
		mainPanel.add(listOfTeams,c);
		
		c.gridx++;
		mainPanel.add(addPlayer,c);
		
		//Populate JComboBox
		Manager m = Manager.getInstance();
		Iterator<Team> i = m.getTeams().iterator();
		while(i.hasNext())
		{
			Team t = i.next();
			listOfTeams.addItem(t.getName());
		}
		
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(500,500);
		
		//Add listeners
		addTeam.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				managerTools mt = new managerTools();
				if(mt.createTeam(teamName.getText()))
				{
					listOfTeams.addItem(teamName.getText());
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Team name cannot be empty.");
				}
			}
			
		});
		
		addPlayer.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				managerTools mt = new managerTools();
				String a = mt.createPlayer(playerName.getText(),choice);
				if(a==null)
				{
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText(a);
				}
				
			}
			
		});
		
		listOfTeams.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				choice = (String)listOfTeams.getSelectedItem();
			}
			
		});
	}
	
	public void refreshData()
	{
		teamName.setText("");
		playerName.setText("");
		choice = "";
		
	}
}
