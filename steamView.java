import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
/** Display the data
@author Robert Webb */
public class steamView extends JFrame implements ActionListener
{
	private steamController c;
	private JLabel URLlabel = new JLabel();
	private JLabel userName = new JLabel();
	private JLabel onlineStatus = new JLabel();
	private JLabel adStatus = new JLabel();
	private JLabel gameName = new JLabel();
	private Font defaultFont = onlineStatus.getFont();
	private Font font = new Font(defaultFont.getFontName(), defaultFont.getStyle(), 25); //lets me set fonts to size 25
	private JLabel avatarLabel = new JLabel();
	private JLabel gameImage = new JLabel();
	private JLabel gameServer = new JLabel();
	
	private Timer update = new Timer(1000, this);

	/**Constructor 
	@param controller is the controller*/
	public steamView(steamController controller)
	{
		c = controller;
		createGUI();
		updateInfo();
		addObjects();
		update.start();
		
	}
	/**create the main window*/
	private void createGUI()
	{
		setLayout(null);
		setSize(765,232);
		setTitle("Steam User Info - " + c.getID());
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**readd all of the information after it's updated*/
	private void updateInfo()
	{
		avatarLabel.setIcon(new ImageIcon(c.getAvatar()));
		gameImage.setIcon(new ImageIcon(setGameIcon()));		
		URLlabel.setText(c.getProfileURL());
		userName.setText(c.getUser());
		setServerIP();
		setOnlineStatus();
		repaint();
	}
	/**set the icon from the game, using the game's id*/
	private BufferedImage setGameIcon()
	{
		BufferedImage gameIcon = null;
		if (c.getGameImage() != null)
		{
			gameIcon = c.getGameImage();
		}
		else
		{
			try 
			{
				gameIcon = ImageIO.read(new File("black.jpg"));
			}
			catch (IOException e) 
			{
			}
		}
		return gameIcon;
	}
	/**get the server IP*/
	private void setServerIP()
	{
		if (c.serverIP() != null)
		{
			gameServer.setText("On server: " + c.serverIP());
		}
		else
		{
			gameServer.setText("");
		}
	}
	
	/**set the players online status, incuding what game they are playing*/
	private void setOnlineStatus()
	{
		String status = c.checkOnline();
		if (status.equals("Offline"))
		{
			adStatus.setText("Since " + c.getLastOnline());
			adStatus.setForeground(Color.RED);
			onlineStatus.setForeground(Color.RED);
		}
		else
		{
			if(c.checkGame() != null)
			{
				adStatus.setText("Currently playing:");
				gameName.setText(c.checkGame());
			}
			else
			{
				adStatus.setText("");
				gameName.setText("");
				
			}
			onlineStatus.setForeground(Color.GREEN);
		}
		onlineStatus.setText(c.checkOnline());
	}
	
	/**add everything to the window*/
	private void addObjects()
	{
		avatarLabel.setBounds(5,5,184,184);
		URLlabel.setBounds(290,5,1000,20);
		URLlabel.setForeground(Color.WHITE);
		userName.setBounds(200,20,800,30);
		userName.setFont(font);
		userName.setForeground(Color.BLUE);
		onlineStatus.setBounds(200,60,100,30);
		onlineStatus.setFont(font);
		adStatus.setBounds(200,100,800,30);
		adStatus.setFont(font);
		adStatus.setForeground(Color.ORANGE);
		gameName.setBounds(200,140,800,30);
		gameName.setFont(font);
		gameName.setForeground(Color.WHITE);
		gameImage.setBounds(450,30,292,136);
		gameServer.setBounds(500,165,300,30);
		gameServer.setForeground(Color.WHITE);
		add(avatarLabel);
		add(URLlabel);
		add(userName);
		add(onlineStatus);
		add(adStatus);
		add(gameImage);
		add(gameName);
		add(gameServer);
	}
	/**when the timer activates get the data and update it's graphical
	representaiton */
	public void actionPerformed(ActionEvent e)
	{
		c.getData();
		updateInfo();
	}
}
		
