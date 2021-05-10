import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import javax.sound.sampled.*;

// Potato Clicker Project V.3 by Yazan, Khaled, Ismael
// Updated 5/9

/*
Future Additions:
Save system x
Achievements
Leaderboard
Reset button x
More upgrades
Improved graphics and UI
Improved gameplay
Music x
Optimized code
Avatar change x
 */


public class PotatoClickGUI implements ActionListener, WindowListener {

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
	private JButton changeAvatar;
	private String name;
	private int prestige;
	// Timer

	private Timer timer = new Timer(1000, this);

	// Audio

	AudioInputStream audio;
	Clip audioclip;
	Clip musicclip;
	Choice musicchoice;
	JButton musicbutton;
	
	// Main Method / GUI

	public PotatoClickGUI() {
		
		Variables.potatoCounter = 0;
		name="potatoes";
		prestige=100;
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

		// here you can buy autoclickers to click the potato automatically for you

		Panel autoupgradePanel = new Panel();
		autoupgradePanel.setBackground(Color.white);
		autoupgradePanel.setBounds(40, 40, 250, 200);
		autoupgradePanel.setLayout(new GridLayout(4,1));

		JLabel autoupgradelabel = new JLabel("Clickers:");
		autoupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		autoupgradelabel.setForeground(Color.white);
		autoupgradePanel.add(autoupgradelabel);
		autoupgradePanel.setBackground(Color.gray);

		// our autoclicker upgrade buttons

		autoupgrade1 = new JButton("+1 P/Sec (" + Variables.autooneqty + ") [" + Variables.autooneprice + "p]");
		autoupgrade1.addActionListener(this);
		autoupgradePanel.add(autoupgrade1);
		autoupgrade2 = new JButton("+10 P/Sec (" + Variables.autotenqty + ") [" + Variables.autotenprice + "p]");
		autoupgrade2.addActionListener(this);
		autoupgradePanel.add(autoupgrade2);
		autoupgrade3 = new JButton("+100 P/Sec (" + Variables.autohundredqty + ") [" + Variables.autohundredprice + "p]");
		autoupgrade3.addActionListener(this);
		autoupgradePanel.add(autoupgrade3);

		// here you can buy upgrades that give you more potatoes per click

		Panel clickupgradePanel = new Panel();
		clickupgradePanel.setBackground(Color.white);
		clickupgradePanel.setBounds(40, 300, 250, 200);
		clickupgradePanel.setLayout(new GridLayout(4,1));

		JLabel clickupgradelabel = new JLabel("Upgrades:");
		clickupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		clickupgradelabel.setForeground(Color.white);
		clickupgradePanel.add(clickupgradelabel);
		clickupgradePanel.setBackground(Color.gray);

		// our upgrade buttons

		clickupgrade1 = new JButton("10 "+name+" (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");
		clickupgrade1.addActionListener(this);
		clickupgradePanel.add(clickupgrade1);
		clickupgrade2 = new JButton("100 "+name+" (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");
		clickupgrade2.addActionListener(this);
		clickupgradePanel.add(clickupgrade2);
		clickupgrade3 = new JButton("1000 "+name+" (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");
		clickupgrade3.addActionListener(this);
		clickupgradePanel.add(clickupgrade3);

		// counter counts the number of potatoes gathered

		counter=new JLabel(Variables.potatoCounter +" "+name);
		counter.setForeground(Color.white);
		counter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		counter.setBounds(450,60,500,100);

		// counter counts the number of potatoes gathered throughout the game

		lifetimecounter=new JLabel("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		lifetimecounter.setForeground(Color.white);
		lifetimecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		lifetimecounter.setBounds(350,500,400,100);

		// displays potatoes gathered per second as the player idles

		Variables.autoincrementer = Variables.autoone + Variables.autoten + Variables.autohundred;
		idlecounter=new JLabel(Variables.autoincrementer + " "+name+"/Sec");
		idlecounter.setForeground(Color.white);
		idlecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		idlecounter.setBounds(450,100,200,100);

		// resets the player's progress

		resetbutton = new JButton("Reset");
		resetbutton.addActionListener(this);
		resetbutton.setBounds(40,20,300,20);

		//changes to Onion avatar
		changeAvatar= new JButton("Change Avatar");
		changeAvatar.addActionListener(this);
		changeAvatar.setBounds(40,550,200,20);

		// change the music

		musicchoice = new Choice();
		musicchoice.add("no music");
		musicchoice.add("'Pizza Parlor'");
		musicchoice.add("'Bodies'");
		musicchoice.add("'Among Us'");
		musicchoice.add("'Chug Jug'");
		musicchoice.add("'Jueputa'");
		musicchoice.setBounds(700,20,100,100);

		// sets the music

		musicbutton = new JButton("no music");
		musicbutton.addActionListener(this);
		musicbutton.setBounds(820,20,125,75);

		// adds all the GUI to our frame

		frame.add(musicbutton);
		frame.add(musicchoice);
		frame.add(resetbutton);
		frame.add(changeAvatar);
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
		frame.addWindowListener(this);
		frame.setVisible(true);

		// starts our timer

		timer.start();
		load();

	}

	public static void main (String args[]) {
		new PotatoClickGUI();
	}
	
	public void load() {
		
		Variables.loadgame();
        
        timer.stop();
        
        clickupgrade1.setText("10 "+name+" (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");
		clickupgrade2.setText("100 "+name+" (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");
		clickupgrade3.setText("1000 "+name+" (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");
		autoupgrade1.setText("+1 P/Sec (" + Variables.autooneqty + ") [" + Variables.autooneprice + "p]");
		autoupgrade2.setText("+10 P/Sec (" + Variables.autotenqty + ") [" + Variables.autotenprice + "p]");
		autoupgrade3.setText("+100 P/Sec (" + Variables.autohundredqty + ") [" + Variables.autohundredprice + "p]");
		idlecounter.setText(Variables.autoincrementer + " "+name+"/Sec");
		counter.setText(Variables.potatoCounter+ " "+name);
		lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		
		timer.start();
        
	}
	//Integer.parseInt(
	// sets up our variables (will work on this later)
	
	

	public void reset() {
		
		Variables.resetgame();
		
		timer.stop();
		
		clickupgrade1.setText("10 "+name+" (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");
		clickupgrade2.setText("100 "+name+" (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");
		clickupgrade3.setText("1000 "+name+" (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");
		autoupgrade1.setText("+1 P/Sec (" + Variables.autooneqty + ") [" + Variables.autooneprice + "p]");
		autoupgrade2.setText("+10 P/Sec (" + Variables.autotenqty + ") [" + Variables.autotenprice + "p]");
		autoupgrade3.setText("+100 P/Sec (" + Variables.autohundredqty + ") [" + Variables.autohundredprice + "p]");
		idlecounter.setText(Variables.autoincrementer + " "+name+"/Sec");
		counter.setText(Variables.potatoCounter+ " "+name);
		lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		
		timer.start();
	}
	
	// plays FX audio on click

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

	// plays music audio on click

	public void playmusic(String i) {
		try {
			audio = AudioSystem.getAudioInputStream(new File(i));
			musicclip = AudioSystem.getClip();
			musicclip.open(audio);
			musicclip.start();
			musicclip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void changeicon(String txtname, String imagename, ImageIcon iconname) {
		
		avatar = new ImageIcon(imagename);
		Image originalPotato = avatar.getImage();
		Image scaled= originalPotato.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
		iconname = new ImageIcon (scaled);
		clicker.setIcon(iconname);
		name = txtname;
		counter.setText(Variables.potatoCounter+ " "+name);
		lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		clickupgrade1.setText("10 "+name+" (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");
		clickupgrade2.setText("100 "+name+" (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");
		clickupgrade3.setText("1000 "+name+" (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");
	}
	
	public void checkPrestige() {
		if (Variables.lifetimepotatoCounter>prestige) {
			playaudio("src//prestige.wav");
			counter.setText("NEW LEVEL REACHED!!!");
			lifetimecounter.setText("NEW LEVEL REACHED!!!");
			prestige*=10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// When the player clicks the potato

		if (e.getSource() == clicker) {
			playaudio("src//tick.wav");

			Variables.incrementer= 1 + Variables.plusten + Variables.plushundred + Variables.plusthousand;
			Variables.potatoCounter+=Variables.incrementer; // increases potatoes counted on click
			Variables.lifetimepotatoCounter+=Variables.incrementer; // increases lifetime potatoes on click

			// updates GUI

			counter.setText(Variables.potatoCounter+ " "+name);
			lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
			checkPrestige();
		}

		// timer updates every 1000 milliseconds (one second)
		if (e.getSource() == timer) {
			int temp = Variables.autoone + Variables.autoten + Variables.autohundred;
			Variables.potatoCounter += temp;
			Variables.lifetimepotatoCounter += temp;
			idlecounter.setText(temp + " "+name+"/Sec");
			counter.setText(Variables.potatoCounter+ " "+name);
			lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		}

		// when the player clicks the reset button
		if (e.getSource() == resetbutton) {
			playaudio("src//tick.wav");
			timer.stop();
			reset();
		}

		// When the player clicks the upgrade buttons

		if (e.getSource() == clickupgrade1 && Variables.potatoCounter >= Variables.plustenprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plustenprice;

			counter.setText(Variables.potatoCounter+ " "+name);

			Variables.plustenqty += 1;
			Variables.plustenprice+=100;
			clickupgrade1.setText("10 " + name + " (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");

			Variables.plusten+=10;
		}
		if (e.getSource() == clickupgrade2 && Variables.potatoCounter >= Variables.plushundredprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plushundredprice;

			counter.setText(Variables.potatoCounter+ " "+name);

			Variables.plushundredqty += 1;
			Variables.plushundredprice+=1000;
			clickupgrade2.setText("100 " + name + " (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");

			Variables.plushundred+=100;
		}
		if (e.getSource() == clickupgrade3 && Variables.potatoCounter >= Variables.plusthousandprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plusthousandprice;

			counter.setText(Variables.potatoCounter+ " "+name);

			Variables.plusthousandqty += 1;
			Variables.plusthousandprice+=10000;
			clickupgrade3.setText("1000 " + name + " (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");


			Variables.plusthousand+=1000;
		}

		// When the player clicks the clicker buttons

		if (e.getSource() == autoupgrade1 && Variables.potatoCounter >= Variables.autooneprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autooneprice;
			Variables.autoone+=1;

			Variables.autooneqty += 1;
			Variables.autooneprice+=10;
			autoupgrade1.setText("+1 P/Sec (" + Variables.autooneqty + ") [" + Variables.autooneprice + "p]");
		}
		if (e.getSource() == autoupgrade2 && Variables.potatoCounter >= Variables.autotenprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autotenprice;
			Variables.autoten+=10;

			Variables.autotenqty += 1;
			Variables.autotenprice+=100;
			autoupgrade2.setText("+10 P/Sec (" + Variables.autotenqty + ") [" + Variables.autotenprice + "p]");
		}
		if (e.getSource() == autoupgrade3 && Variables.potatoCounter >= Variables.autohundredprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autohundredprice;
			Variables.autohundred+=100;

			Variables.autohundredqty += 1;
			Variables.autohundredprice+=1000;
			autoupgrade3.setText("+100 P/Sec (" + Variables.autohundredqty + ") [" + Variables.autohundredprice + "p]");

		}

		// when the player selects the change avatar button

		if (e.getSource()==changeAvatar) {
			playaudio("src//tick.wav");
			
			if (name=="potatoes") {
				ImageIcon onion = null;
				changeicon("onions","src\\onion.png",onion);
			}
			else if (name=="onions") {
				ImageIcon crewmate = null;
				changeicon("crewmates","src\\crewmate.png",crewmate);
			}
			else if (name=="crewmates") {
				ImageIcon faris = null;
				changeicon("farises","src\\faris.png",faris);
			}
			else if (name=="farises") {
				ImageIcon troll = null;
				changeicon("trolls","src\\troll.png",troll);
			}
			else if (name=="trolls") {
				ImageIcon namekian = null;
				changeicon("namekians","src\\namekian.png",namekian);
			}
			else if (name=="namekians") {
				ImageIcon potato = null;
				changeicon("potatoes","src\\potato.png",potato);
			}
		}

		// when the player selects the music button

		if (e.getSource()==musicbutton) {
			playaudio("src//tick.wav");
			String choice = musicchoice.getSelectedItem();
			if (musicclip!=null) {
				musicclip.stop();
			}
			if (choice.equals("'Pizza Parlor'")) {
				musicbutton.setText("'Pizza Parlor'");
				playmusic("src\\penguintrack.wav");
			}
			if (choice.equals("'Bodies'")) {
				musicbutton.setText("'Bodies'");
				playmusic("src\\bodiestrack.wav");
			}
			if (choice.equals("'Among Us'")) {
				musicbutton.setText("'Among Us'");
				playmusic("src\\amogustrack.wav");
			}
			if (choice.equals("'Chug Jug'")) {
				musicbutton.setText("'Chug Jug'");
				playmusic("src\\chugjugtrack.wav");
			}
			if (choice.equals("'Jueputa'")) {
				musicbutton.setText("'Jueputa'");
				playmusic("src\\jueputatrack.wav");
			}
			if (choice.equals("no music")) {
				musicbutton.setText("no music");
			}
		}

	}
	
	// Annoying imported methods I can't get rid of

	@Override
	public void windowClosing(WindowEvent e) {
		Variables.savegame();
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}