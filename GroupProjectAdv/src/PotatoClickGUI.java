
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import javax.sound.sampled.*;

// Potato Clicker Project V.2 by Yazan, Khaled, Ismael
// Updated 4/22
// 6.2 Version Progress

/*

Future Additions:

Save system
Achievements
Leaderboard
Reset button x
More upgrades
Improved graphics and UI
Improved gameplay
Music
Optimized code

*/


public class PotatoClickGUI implements ActionListener {
	
	// GUI Stuff
	
	JFrame frame; // our frame
	private JButton clicker; // potato clickable area
	private ImageIcon avatar; // the image for the potato
	JLabel counter; // counts the amount of potatoes collected
	JLabel idlecounter; // potatoes per second
	JLabel lifetimecounter; // counts the amount of potatoes collected over the course of the game
	
	private JButton clickupgrade1; // upgrades potatoes per click
	private JButton clickupgrade2;
	private JButton clickupgrade3;
	
	private JButton autoupgrade1; // upgrades auto clickers
	private JButton autoupgrade2;
	private JButton autoupgrade3;
	
	private JButton resetbutton;
		
	// Counters
	
	int potatoCounter = 0; // counts the number of potatoes
	int lifetimepotatoCounter = 0; // counts the amount of potatoes collected over the course of the game
	int incrementer; //the amount of potatoes added (used to activate powerups)
	int autoincrementer; // the amount of potatoes added automatically
	
	// Upgrades
	
	int plusten=0; // +10 potatoes per click
	int plustenprice = 100;
	int plustenqty = 0;
	
	int plushundred=0; // +100 potatoes per click
	int plushundredprice = 1000;
	int plushundredqty = 0;
	
	int plusthousand=0; // +1000 potatoes per click
	int plusthousandprice = 10000;
	int plusthousandqty = 0;
	
	int autoone = 0; // +1 potatoes per second
	int autooneprice = 100;
	int autooneqty = 0;
	
	int autoten = 0; // +10 potatoes per second
	int autotenprice = 1000;
	int autotenqty = 0;
	
	int autohundred = 0; // +100 potatoes per second
	int autohundredprice = 10000;
	int autohundredqty = 0;
	
	// Timer
	
	private Timer timer = new Timer(1000, this);
	
	// Audio
	
    AudioInputStream audio;
    Clip audioclip;
	
	// Main Method / GUI
	
	public PotatoClickGUI() {
		
		potatoCounter = 0;
		
		// creates frame
		
		JFrame frame = new JFrame("Potato Clicker");
		
		// creates scaled imageIcon here
		
		avatar = new ImageIcon("src\\potato.png");
		Image originalPotato = avatar.getImage();
		Image scaled= originalPotato.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
		ImageIcon potato= new ImageIcon (scaled);
		
		//creates panel and potato button
		
		Panel potatoPanel = new Panel();
		potatoPanel.setBounds(300, 200, 500, 200);
		clicker = new JButton();
		clicker.setBackground(Color.gray);
		clicker.setIcon(potato);
		clicker.setBorder(null); //to avoid the blue border on potato
		clicker.addActionListener(this);
		potatoPanel.add(clicker);
		frame.add(potatoPanel);
		
		// CURRENTLY NON-FUNCTIONAL here you can buy autoclickers to click the potato automatically for you
		
		Panel autoupgradePanel = new Panel();
		autoupgradePanel.setBackground(Color.white);
		autoupgradePanel.setBounds(40, 40, 250, 200);
		autoupgradePanel.setLayout(new GridLayout(4,1));
		
		JLabel autoupgradelabel = new JLabel("Clickers:");
		autoupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		autoupgradelabel.setForeground(Color.white);
		autoupgradePanel.add(autoupgradelabel);
		autoupgradePanel.setBackground(Color.gray);
	
		
		autoupgrade1 = new JButton("+1 P/Sec (" + autooneqty + ") [" + autooneprice + "p]");
		autoupgrade1.addActionListener(this);
		autoupgradePanel.add(autoupgrade1);
		autoupgrade2 = new JButton("+10 P/Sec (" + autotenqty + ") [" + autotenprice + "p]");
		autoupgrade2.addActionListener(this);
		autoupgradePanel.add(autoupgrade2);
		autoupgrade3 = new JButton("+100 P/Sec (" + autohundredqty + ") [" + autohundredprice + "p]");
		autoupgrade3.addActionListener(this);
		autoupgradePanel.add(autoupgrade3);
		
		// CURRENTLY NON-FUNCTIONAL here you can buy upgrades that give you more potatoes per click
		
		Panel clickupgradePanel = new Panel();
		clickupgradePanel.setBackground(Color.white);
		clickupgradePanel.setBounds(40, 300, 250, 200);
		clickupgradePanel.setLayout(new GridLayout(4,1));
		
		JLabel clickupgradelabel = new JLabel("Upgrades:");
		clickupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		clickupgradelabel.setForeground(Color.white);
		clickupgradePanel.add(clickupgradelabel);
		clickupgradePanel.setBackground(Color.gray);
		
		// some dummy clickers, will make them work later
		
		clickupgrade1 = new JButton("10 Potatoes (" + plustenqty + ") [" + plustenprice + "p]");
		clickupgrade1.addActionListener(this);
		clickupgradePanel.add(clickupgrade1);
		clickupgrade2 = new JButton("100 Potatoes (" + plushundredqty + ") [" + plushundredprice + "p]");
		clickupgrade2.addActionListener(this);
		clickupgradePanel.add(clickupgrade2);
		clickupgrade3 = new JButton("1000 Potatoes (" + plusthousandqty + ") [" + plusthousandprice + "p]");
		clickupgrade3.addActionListener(this);
		clickupgradePanel.add(clickupgrade3);
		
		// counter counts the number of potatoes gathered
		
		counter=new JLabel(potatoCounter +" potatoes");
		counter.setForeground(Color.white);
		counter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		counter.setBounds(450,60,500,100);
		
		// counter counts the number of potatoes gathered throughout the game
		
		lifetimecounter=new JLabel("total potatoes gathered: " + lifetimepotatoCounter);
		lifetimecounter.setForeground(Color.white);
		lifetimecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		lifetimecounter.setBounds(350,500,400,100);
		
		// displays potatoes gathered per second as the player idles
		
		autoincrementer = autoone + autoten + autohundred;
		idlecounter=new JLabel(autoincrementer + " Potatoes/Sec");
		idlecounter.setForeground(Color.white);
		idlecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		idlecounter.setBounds(450,100,200,100);
		
		// resets the player's progress
		
		resetbutton = new JButton("Reset");
		resetbutton.addActionListener(this);
		resetbutton.setBounds(40,20,300,20);
		
		// adds all the GUI to our frame
		
		frame.add(resetbutton);
		frame.add(counter);
		frame.add(idlecounter);
		frame.add(lifetimecounter);
		frame.add(autoupgradePanel);
		frame.add(clickupgradePanel);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		frame.setFocusable(true);
		frame.setSize(1000,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// starts our timer
		
		timer.start();
			
	}
	
	public static void main (String args[]) {
		new PotatoClickGUI();
	}
	
	// sets up our variables (will work on this later)
	
	public void setup(int i) {
		plusten=0; // +10 potatoes per click
	    plustenprice = 100;
	    plustenqty = 0;
	    clickupgrade1.setText("10 Potatoes (" + plustenqty + ") [" + plustenprice + "p]");
		plushundred=0; // +100 potatoes per click
		plushundredprice = 1000;
		plushundredqty = 0;
	    clickupgrade2.setText("100 Potatoes (" + plushundredqty + ") [" + plushundredprice + "p]");
		plusthousand=0; // +1000 potatoes per click
		plusthousandprice = 10000;
		plusthousandqty = 0;
	    clickupgrade3.setText("1000 Potatoes (" + plusthousandqty + ") [" + plusthousandprice + "p]");
		autoone = 0; // +1 potatoes per second
		autooneprice = 100;
		autooneqty = 0;
		autoupgrade1.setText("+1 P/Sec (" + autooneqty + ") [" + autooneprice + "p]");
		autoten = 0; // +10 potatoes per second
		autotenprice = 1000;
		autotenqty = 0;
		autoupgrade2.setText("+10 P/Sec (" + autotenqty + ") [" + autotenprice + "p]");
		autohundred = 0; // +100 potatoes per second
		autohundredprice = 10000;
		autohundredqty = 0;
		autoupgrade3.setText("+100 P/Sec (" + autohundredqty + ") [" + autohundredprice + "p]");
		incrementer = 0;
		autoincrementer = autoone + autoten + autohundred;
		idlecounter.setText(autoincrementer + " Potatoes/Sec");
		potatoCounter = 0;
		counter.setText(potatoCounter+ " potatoes");
		lifetimepotatoCounter = i;
		lifetimecounter.setText("total potatoes gathered: " + lifetimepotatoCounter);
		timer.start();
	}
	
	// plays audio on click
	
    public void playaudio(String i) {
        try {
            audio = AudioSystem.getAudioInputStream(new File(i));
            audioclip = AudioSystem.getClip();
            audioclip.open(audio);
            audioclip.start();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// When the player clicks the potato
		
		if (e.getSource() == clicker) {
			playaudio("tick.wav");
			
			incrementer= 1 + plusten + plushundred + plusthousand;
			potatoCounter+=incrementer; // increases potatoes counted on click
			lifetimepotatoCounter+=incrementer; // increases lifetime potatoes on click
			
			// updates GUI
			
			counter.setText(potatoCounter+ " potatoes");
			lifetimecounter.setText("total potatoes gathered: " + lifetimepotatoCounter);
			
		}
		
		// timer updates every 1000 milliseconds (one second)
		if (e.getSource() == timer) {
			int temp = autoone + autoten + autohundred;
			potatoCounter += temp;
			lifetimepotatoCounter += temp;
			idlecounter.setText(temp + " Potatoes/Sec");
			counter.setText(potatoCounter+ " potatoes");
			lifetimecounter.setText("total potatoes gathered: " + lifetimepotatoCounter);
		}
		
		// when the player clicks the reset button
		if (e.getSource() == resetbutton) {
			playaudio("tick.wav");
			timer.stop();
			setup(lifetimepotatoCounter);
		}
		
		// When the player clicks the upgrade buttons
		
		if (e.getSource() == clickupgrade1 && potatoCounter >= plustenprice) {
			playaudio("orb.wav");
			potatoCounter -= plustenprice;
			
			counter.setText(potatoCounter+ " potatoes");
			
			plustenqty += 1;
			plustenprice+=100;
			clickupgrade1.setText("10 Potatoes (" + plustenqty + ") [" + plustenprice + "p]");
			
			plusten+=10;
		}
		if (e.getSource() == clickupgrade2 && potatoCounter >= plushundredprice) {
			playaudio("orb.wav");
			potatoCounter -= plushundredprice;
			
			counter.setText(potatoCounter+ " potatoes");
			
			plushundredqty += 1;
			plushundredprice+=1000;
			clickupgrade2.setText("100 Potatoes (" + plushundredqty + ") [" + plushundredprice + "p]");
			
			plushundred+=100;
		}
		if (e.getSource() == clickupgrade3 && potatoCounter >= plusthousandprice) {
			playaudio("orb.wav");
			potatoCounter -= plusthousandprice;
			
			counter.setText(potatoCounter+ " potatoes");
			
			plusthousandqty += 1;
			plusthousandprice+=10000;
			clickupgrade3.setText("1000 Potatoes (" + plusthousandqty + ") [" + plusthousandprice + "p]");
			

			plusthousand+=1000;
		}
		
		// When the player clicks the clicker buttons
		
		if (e.getSource() == autoupgrade1 && potatoCounter >= autooneprice) {
			playaudio("orb.wav");
			potatoCounter -= autooneprice;
			autoone+=1;
			
			autooneqty += 1;
			autooneprice+=10;
			autoupgrade1.setText("+1 P/Sec (" + autooneqty + ") [" + autooneprice + "p]");
		}
		if (e.getSource() == autoupgrade2 && potatoCounter >= autotenprice) {
			playaudio("orb.wav");
			potatoCounter -= autotenprice;
			autoten+=10;
			
			autotenqty += 1;
			autotenprice+=100;
			autoupgrade2.setText("+10 P/Sec (" + autotenqty + ") [" + autotenprice + "p]");
		}
		if (e.getSource() == autoupgrade3 && potatoCounter >= autohundredprice) {
			playaudio("orb.wav");
			potatoCounter -= autohundredprice;
			autohundred+=100;
			
			autohundredqty += 1;
			autohundredprice+=1000;
			autoupgrade3.setText("+100 P/Sec (" + autohundredqty + ") [" + autohundredprice + "p]");
			
		}
	}
}
