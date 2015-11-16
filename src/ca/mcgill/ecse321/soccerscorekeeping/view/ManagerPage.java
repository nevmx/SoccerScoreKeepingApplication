package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.admin.authentication;

public class ManagerPage extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1167738945720479651L;
	
	private JLabel enterPassword;
	private JLabel error;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JPanel mainPanel;
	private JPanel loginPanel;
	
	public ManagerPage()
	{
		initializeComponents();
	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
	private void initializeComponents()
	{
		//Initialize Components
		enterPassword = new JLabel("Password: ");
		error = new JLabel("");
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Login");
		
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel, BorderLayout.NORTH);
		
		loginPanel = new JPanel(new GridBagLayout());
		mainPanel.add(loginPanel);
		
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(600,200);
		setTitle("Manager Login");
		
		
		//GridBagConstraints
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,20,20,20);
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx=1;
		c.gridy=0;
		
		loginPanel.add(error,c);
		
		c.gridx=0;
		c.gridy=4;
		//c.anchor = GridBagConstraints.LINE_START;
		loginPanel.add(enterPassword,c);
		
		c.gridx++;
		//c.anchor = GridBagConstraints.CENTER;
		loginPanel.add(passwordField,c);
		
		c.gridx++;
		//c.anchor = GridBagConstraints.LINE_END;
		loginPanel.add(loginButton,c);
		
		//Add Listener
		loginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				authentication a1 = new authentication();
				if(a1.authenticate(passwordField.getPassword()))
				{
					close();
					new ManagerTools().setVisible(true);
				}
				else
				{
				error.setForeground(Color.red);
				error.setText("The password you entered is incorrect.");
				}
			}
			
		});
		
		
	}

}
