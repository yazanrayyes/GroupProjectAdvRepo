
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


public class PotatoClickGUI implements ActionListener {
	
	JFrame frame;
	private JButton potatoButton;
	private ImageIcon potato;
	JLabel counter; // counts the amount of potatoes collected
	JLabel idlecounter; // potatoes per second
	JLabel lifetimecounter; // counts the amount of potatoes collected in total over the course of the game
	int potatoCounter = 0;
	int lifetimepotatoCounter = 0;
	
	
	//Done by Yazan, Khaled, Ismael
	// Updated 4/11
	
	public PotatoClickGUI() {
		
		potatoCounter = 0;
		
		JFrame frame = new JFrame();
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
		
		
		// Here you can buy autoclickers to click the potato automatically for you
		
		Panel autoupgradePanel = new Panel();
		autoupgradePanel.setBackground(Color.white);
		autoupgradePanel.setBounds(40, 40, 200, 200);
		autoupgradePanel.setLayout(new GridLayout(4,1));
		
		// Some dummy upgrades, will make them work later
		
		JLabel autoupgradelabel = new JLabel("Clickers:");
		autoupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		autoupgradelabel.setForeground(Color.white);
		autoupgradePanel.add(autoupgradelabel);
		autoupgradePanel.setBackground(Color.gray);
		Button autoupgrade1 = new Button("Clicker 1");
		autoupgradePanel.add(autoupgrade1);
		Button autoupgrade2 = new Button("Clicker 2");
		autoupgradePanel.add(autoupgrade2);
		Button autoupgrade3 = new Button("Clicker 3");
		autoupgradePanel.add(autoupgrade3);
		
		
		// Here you can buy upgrades that give you more potatoes per click
		
		Panel clickupgradePanel = new Panel();
		clickupgradePanel.setBackground(Color.white);
		clickupgradePanel.setBounds(40, 300, 200, 200);
		clickupgradePanel.setLayout(new GridLayout(4,1));
		
		JLabel clickupgradelabel = new JLabel("Upgrades:");
		clickupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		clickupgradelabel.setForeground(Color.white);
		clickupgradePanel.add(clickupgradelabel);
		clickupgradePanel.setBackground(Color.gray);
		
		Button clickupgrade1 = new Button("Upgrade 1");
		clickupgradePanel.add(clickupgrade1);
		Button clickupgrade2 = new Button("Upgrade 2");
		clickupgradePanel.add(clickupgrade2);
		Button clickupgrade3 = new Button("Upgrade 3");
		clickupgradePanel.add(clickupgrade3);
		
		counter=new JLabel(potatoCounter +" potatoes");
		counter.setForeground(Color.white);
		counter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		counter.setBounds(400,60,200,100);
		
		lifetimecounter=new JLabel("total potatoes gathered: " + lifetimepotatoCounter);
		lifetimecounter.setForeground(Color.white);
		lifetimecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		lifetimecounter.setBounds(300,500,200,100);
		
		idlecounter=new JLabel("Test");
		idlecounter.setForeground(Color.white);
		idlecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		idlecounter.setBounds(400,100,200,100);
		
		frame.add(counter);
		frame.add(idlecounter);
		frame.add(lifetimecounter);
		frame.add(autoupgradePanel);
		frame.add(clickupgradePanel);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		frame.setFocusable(true);
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
	
	public static void main (String args[]) {
		new PotatoClickGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		potatoCounter++;
		lifetimepotatoCounter++;
		counter.setText(potatoCounter+ " potatoes");
		lifetimecounter.setText("total potatoes gathered: " + lifetimepotatoCounter);
	}

}
