
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Done by Yazan, Khaled, Ismael
// Updated 4/11

public class PotatoClickGUI implements ActionListener {
	
	// GUI Stuff
	
	JFrame frame; // our frame
	private JButton potatoButton; // potato clickable area
	private ImageIcon potato; // the image for the potato
	JLabel counter; // counts the amount of potatoes collected
	JLabel idlecounter; // potatoes per second
	JLabel lifetimecounter; // counts the amount of potatoes collected over the course of the game
	
	// Counters
	
	int potatoCounter = 0; // counts the number of potatoes
	int lifetimepotatoCounter = 0; // counts the amount of potatoes collected over the course of the game
	
	public PotatoClickGUI() {
		
		potatoCounter = 0;
		
		// creates frame
		
		JFrame frame = new JFrame("Potato Clicker");
		
		// creates scaled imageIcon here
		
		potato = new ImageIcon("src\\potato.png");
		Image originalPotato = potato.getImage();
		Image scaled= originalPotato.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
		ImageIcon potato= new ImageIcon (scaled);
		
		//creates panel and potato button
		
		Panel potatoPanel = new Panel();
		potatoPanel.setBounds(250, 200, 500, 200);
		potatoButton = new JButton();
		potatoButton.setBackground(Color.gray);
		potatoButton.setIcon(potato);
		potatoButton.setBorder(null); //to avoid the blue border on potato
		potatoButton.addActionListener(this);
		potatoPanel.add(potatoButton);
		frame.add(potatoPanel);
		
		// CURRENTLY NON-FUNCTIONAL here you can buy autoclickers to click the potato automatically for you
		
		Panel autoupgradePanel = new Panel();
		autoupgradePanel.setBackground(Color.white);
		autoupgradePanel.setBounds(40, 40, 200, 200);
		autoupgradePanel.setLayout(new GridLayout(4,1));
		
		JLabel autoupgradelabel = new JLabel("Clickers:");
		autoupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		autoupgradelabel.setForeground(Color.white);
		autoupgradePanel.add(autoupgradelabel);
		autoupgradePanel.setBackground(Color.gray);
		
		// some dummy clickers, will make them work later
		
		Button autoupgrade1 = new Button("Clicker 1");
		autoupgradePanel.add(autoupgrade1);
		Button autoupgrade2 = new Button("Clicker 2");
		autoupgradePanel.add(autoupgrade2);
		Button autoupgrade3 = new Button("Clicker 3");
		autoupgradePanel.add(autoupgrade3);
		
		// CURRENTLY NON-FUNCTIONAL here you can buy upgrades that give you more potatoes per click
		
		Panel clickupgradePanel = new Panel();
		clickupgradePanel.setBackground(Color.white);
		clickupgradePanel.setBounds(40, 300, 200, 200);
		clickupgradePanel.setLayout(new GridLayout(4,1));
		
		JLabel clickupgradelabel = new JLabel("Upgrades:");
		clickupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		clickupgradelabel.setForeground(Color.white);
		clickupgradePanel.add(clickupgradelabel);
		clickupgradePanel.setBackground(Color.gray);
		
		// some dummy clickers, will make them work later
		
		Button clickupgrade1 = new Button("Upgrade 1");
		clickupgradePanel.add(clickupgrade1);
		Button clickupgrade2 = new Button("Upgrade 2");
		clickupgradePanel.add(clickupgrade2);
		Button clickupgrade3 = new Button("Upgrade 3");
		clickupgradePanel.add(clickupgrade3);
		
		// counter counts the number of potatoes gathered
		
		counter=new JLabel(potatoCounter +" potatoes");
		counter.setForeground(Color.white);
		counter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		counter.setBounds(400,60,200,100);
		
		// counter counts the number of potatoes gathered throughout the game
		
		lifetimecounter=new JLabel("total potatoes gathered: " + lifetimepotatoCounter);
		lifetimecounter.setForeground(Color.white);
		lifetimecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		lifetimecounter.setBounds(300,500,250,100);
		
		// CURRENTLY NON-FUNCTIONAL will display potatoes gathered per second as the player idles
		
		idlecounter=new JLabel("Test");
		idlecounter.setForeground(Color.white);
		idlecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		idlecounter.setBounds(400,100,200,100);
		
		// adds all the GUI to our frame
		
		frame.add(counter);
		frame.add(idlecounter);
		frame.add(lifetimecounter);
		frame.add(autoupgradePanel);
		frame.add(clickupgradePanel);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		frame.setFocusable(true);
		frame.setSize(620,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
	
	public static void main (String args[]) {
		new PotatoClickGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		potatoCounter++; // increases potatoes counted on click
		lifetimepotatoCounter++; // increases lifetime potatoes on click
		
		// updates GUI
		
		counter.setText(potatoCounter+ " potatoes");
		lifetimecounter.setText("total potatoes gathered: " + lifetimepotatoCounter);
		
	}

}
