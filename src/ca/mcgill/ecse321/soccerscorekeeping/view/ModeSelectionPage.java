package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ModeSelectionPage extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6358380156573929337L;
	
	private JButton managerLoginButton;
	private JButton liveInputModeButton;
	private JButton batchInputModeButton;
	private JButton playerAnalysisButton;
	private JButton leagueAnalysisButton;
	private JLabel chooseModeLabel;
	private JPanel mainPanel;
	private JPanel panelButtons;
	
	
	
	public ModeSelectionPage()
	{
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		//Initialize components
		managerLoginButton = new JButton();
		liveInputModeButton = new JButton();
		batchInputModeButton = new JButton();
		playerAnalysisButton = new JButton();
		leagueAnalysisButton = new JButton();
		chooseModeLabel = new JLabel();
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		
		panelButtons = new JPanel(new GridBagLayout());
		mainPanel.add(panelButtons);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,10,10,10);
		
		
		c.gridx=0;
		c.gridy=0;
		
		panelButtons.add(chooseModeLabel,c);
		c.gridy++;
		
		panelButtons.add(managerLoginButton,c);
		c.gridy++;
		
		panelButtons.add(liveInputModeButton,c);
		c.gridy++;
		
		panelButtons.add(batchInputModeButton,c);
		c.gridy++;
		
		panelButtons.add(playerAnalysisButton,c);
		c.gridy++;
		
		panelButtons.add(leagueAnalysisButton,c);
		c.gridy++;
		
		//Global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Soccer Scorekeeping Application");
		setSize(500,400);
		
		//Set text
		chooseModeLabel.setText("Please Select: ");
		managerLoginButton.setText("Login as Manager");
		liveInputModeButton.setText("Live Input");
		batchInputModeButton.setText("Batch Input");
		playerAnalysisButton.setText("Player Analysis");
		leagueAnalysisButton.setText("League Analysis");
		
		//Add Listeners
		managerLoginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ManagerPage().setVisible(true);
			}
			
		});
		
		liveInputModeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new LiveInputMode().setVisible(true);
			}
			
		});
		
		batchInputModeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new BatchInputMode().setVisible(true);
			}
			
		});
		
		playerAnalysisButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new PlayerAnalysisMode().setVisible(true);
			}
			
		});
		
		leagueAnalysisButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new LeagueAnalysisMode().setVisible(true);
			}
			
		});
		
		
	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
}
