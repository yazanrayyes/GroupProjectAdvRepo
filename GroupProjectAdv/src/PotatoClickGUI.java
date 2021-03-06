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

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import javax.sound.sampled.*;

// Potato Clicker Project V.3 Final by Yazan, Khaled, Ismael
// Final Version 5/11


public class PotatoClickGUI implements ActionListener, WindowListener {

	// GUI Stuff

	JFrame frame; // our frame
	
	private ImageIcon avatar; // the image for the potato
	
	private JButton clicker; // potato clickable area
	private JButton resetbutton; // button to change our image
	private JButton changeAvatar; // button to change out avatar
	private JButton newgamebutton; // button to start a new game
	
	JLabel counter; // counts the amount of potatoes collected
	JLabel idlecounter; // potatoes per second
	JLabel lifetimecounter; // counts the amount of potatoes collected over the course of the game
	
	private JLabel clickupgradelabel; // our labels for the GUI
	private JLabel autoupgradelabel;
	private JLabel leaderboardlabel;

	private JButton clickupgrade1; // upgrades potatoes per click
	private JButton clickupgrade2;
	private JButton clickupgrade3;

	private JButton autoupgrade1; // upgrades auto clickers
	private JButton autoupgrade2;
	private JButton autoupgrade3;
	
	private JTextArea ranking1; // rankings on our leaderboard
	private JTextArea ranking2;
	private JTextArea ranking3;
	private JTextArea ranking4;
	private JTextArea ranking5;

	private String name; // the name of the object being clicked in text
	
	// Timer

	private Timer timer = new Timer(1000, this);
	
	// Leaderboard
	
	ArrayList<Integer> rankingvalues = new ArrayList<Integer>();

	// Audio

	AudioInputStream audio;
	Clip audioclip;
	Clip musicclip;
	Choice musicchoice;
	JButton musicbutton;

	public PotatoClickGUI() {
		
		rankingvalues.add(Variables.highscoreone); // creating our arraylist of rankings
		rankingvalues.add(Variables.highscoretwo);
		rankingvalues.add(Variables.highscorethree);
		rankingvalues.add(Variables.highscorefour);
		rankingvalues.add(Variables.highscorefive);
		
		name="potatoes"; // our starting image (we can change it in-game)
		
		// creates frame
		
		frame = new JFrame("Potato Clicker");

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

		autoupgradelabel = new JLabel("Clickers");
		autoupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		autoupgradelabel.setForeground(Color.white);
		autoupgradePanel.add(autoupgradelabel);
		autoupgradePanel.setBackground(Color.gray);

		// our autoclicker upgrade buttons

		autoupgrade1 = new JButton();
		autoupgrade1.addActionListener(this);
		autoupgradePanel.add(autoupgrade1);
		autoupgrade2 = new JButton();
		autoupgrade2.addActionListener(this);
		autoupgradePanel.add(autoupgrade2);
		autoupgrade3 = new JButton();
		autoupgrade3.addActionListener(this);
		autoupgradePanel.add(autoupgrade3);

		// here you can buy upgrades that give you more potatoes per click

		Panel clickupgradePanel = new Panel();
		clickupgradePanel.setBackground(Color.white);
		clickupgradePanel.setBounds(40, 300, 250, 200);
		clickupgradePanel.setLayout(new GridLayout(4,1));

		clickupgradelabel = new JLabel("Upgrades");
		clickupgradelabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		clickupgradelabel.setForeground(Color.white);
		clickupgradePanel.add(clickupgradelabel);
		clickupgradePanel.setBackground(Color.gray);

		// our upgrade buttons

		clickupgrade1 = new JButton();
		clickupgrade1.addActionListener(this);
		clickupgradePanel.add(clickupgrade1);
		clickupgrade2 = new JButton();
		clickupgrade2.addActionListener(this);
		clickupgradePanel.add(clickupgrade2);
		clickupgrade3 = new JButton();
		clickupgrade3.addActionListener(this);
		clickupgradePanel.add(clickupgrade3);
		
		// Here's a leaderboard of all our scores and stuff
		
		Panel leaderboardPanel = new Panel();
		leaderboardPanel.setBackground(Color.white);
		leaderboardPanel.setBounds(1000, 120, 150, 300);
		leaderboardPanel.setLayout(new GridLayout(6,1));
		
		leaderboardlabel = new JLabel("Leaderboard");
		leaderboardlabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		leaderboardlabel.setForeground(Color.white);
		leaderboardPanel.add(leaderboardlabel);
		leaderboardPanel.setBackground(Color.gray);
		
		// Different slots in the leaderboard
		
		ranking1 = new JTextArea();
		ranking1.setEditable(false);
		leaderboardPanel.add(ranking1);
		ranking2 = new JTextArea();
		ranking2.setEditable(false);
		leaderboardPanel.add(ranking2);
		ranking3 = new JTextArea();
		ranking3.setEditable(false);
		leaderboardPanel.add(ranking3);
		ranking4 = new JTextArea();
		ranking4.setEditable(false);
		leaderboardPanel.add(ranking4);
		ranking5 = new JTextArea();
		ranking5.setEditable(false);
		leaderboardPanel.add(ranking5);
		
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
		idlecounter=new JLabel(Variables.autoincrementer + " "+name+"/sec" + "  " + Variables.incrementer + " "+name+"/click");
		idlecounter.setForeground(Color.white);
		idlecounter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 16));
		idlecounter.setBounds(400,100,450,100);

		// resets the player's progress

		resetbutton = new JButton("Reset" + " (prestige at " + Variables.prestigelevel + "p) ");
		resetbutton.addActionListener(this);
		resetbutton.setBounds(40,20,300,20);
		
		newgamebutton = new JButton("New Game");
		newgamebutton.addActionListener(this);
		newgamebutton.setBounds(1050,550,120,20);

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
		musicchoice.setBounds(900,20,100,100);

		// sets the music

		musicbutton = new JButton("no music");
		musicbutton.addActionListener(this);
		musicbutton.setBounds(1020,20,125,75);

		// adds all the GUI to our frame

		frame.add(musicbutton);
		frame.add(musicchoice);
		frame.add(resetbutton);
		frame.add(changeAvatar);
		frame.add(counter);
		frame.add(idlecounter);
		frame.add(lifetimecounter);
		frame.add(newgamebutton);
		frame.add(leaderboardPanel);
		frame.add(autoupgradePanel);
		frame.add(clickupgradePanel);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		frame.setFocusable(true);
		frame.setSize(1200,620);
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
	
	// loads our saved game
	
	public void load() {
		
		Variables.loadgame();
        timer.stop();
		update();
		timer.start();
        
	}
	
	// resets our game

	public void reset() {
		Variables.resetgame();
		timer.stop();
		update();
		resetbutton.setText("Reset" + " (prestige at " + Variables.prestigelevel + "p) ");
		timer.start();
	}
	
	// starts a new game
	
	public void startnewgame() {
		sortranking();
		Variables.newgame();
		timer.stop();
		update();
		resetbutton.setText("Reset" + " (prestige at " + Variables.prestigelevel + "p) ");
		autoupgradelabel.setText("Clickers");
		clickupgradelabel.setText("Upgrades");
		timer.start();
	}
	
	// sorts the rankings in our leaderboard
	
	public void sortranking() {
		if (Variables.potatoCounter > rankingvalues.get(4)) {
			rankingvalues.set(4,Variables.potatoCounter);
		}
		
		for (int i = 4; i > 0; i--) {
			int temp = rankingvalues.get(i);
			if (rankingvalues.get(i) > rankingvalues.get(i-1)) {
				rankingvalues.set(i,rankingvalues.get(i-1));
				rankingvalues.set(i-1, temp);
			}
		}
		
		Variables.highscoreone = rankingvalues.get(0);
		Variables.highscoretwo = rankingvalues.get(1);
		Variables.highscorethree = rankingvalues.get(2);
		Variables.highscorefour = rankingvalues.get(3);
		Variables.highscorefive = rankingvalues.get(4);
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
	
	// changes our icon from a potato to other images
	
	public void changeicon(String txtname, String imagename, ImageIcon iconname) {
		
		avatar = new ImageIcon(imagename);
		Image originalPotato = avatar.getImage();
		Image scaled= originalPotato.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
		iconname = new ImageIcon (scaled);
		clicker.setIcon(iconname);
		name = txtname;
		update();
	}
	
	// checks to see if the player qualifies for prestige when resetting the game
	
	public void checkPrestige() {
		if (Variables.potatoCounter >= Variables.prestigelevel) {
			playaudio("src//prestige.wav");
			Variables.prestigecounter++;
			resetbutton.setText("Reset [" + Variables.prestigelevel + "p prestige unlocked]");
			Variables.pendingprestige *= 2;
			Variables.prestigelevel*=100;
		}
	}
	
	// updates our GUI as labels/text changes
	
	public void update() {
		checkPrestige();
		Variables.incrementer= 1 + Variables.plusten + Variables.plushundred + Variables.plusthousand;
		Variables.autoincrementer = Variables.autoone + Variables.autoten + Variables.autohundred;
		counter.setText(Variables.potatoCounter+ " "+name);
		clickupgrade1.setText(Integer.toString(Variables.plustenincrement)+" " + name + " (" + Variables.plustenqty + ") [" + Variables.plustenprice + "p]");
		clickupgrade2.setText(Integer.toString(Variables.plushundredincrement)+" " + name + " (" + Variables.plushundredqty + ") [" + Variables.plushundredprice + "p]");
		clickupgrade3.setText(Integer.toString(Variables.plusthousandincrement)+" " + name + " (" + Variables.plusthousandqty + ") [" + Variables.plusthousandprice + "p]");
		autoupgrade1.setText(Integer.toString(Variables.autooneincrement) + " P/Sec (" + Variables.autooneqty + ") [" + Variables.autooneprice + "p]");
		autoupgrade2.setText(Integer.toString(Variables.autotenincrement) + " P/Sec (" + Variables.autotenqty + ") [" + Variables.autotenprice + "p]");
		autoupgrade3.setText(Integer.toString(Variables.autohundredincrement) + " P/Sec (" + Variables.autohundredprice + "p]");
		rankingvalues.set(0,Variables.highscoreone);
		rankingvalues.set(1,Variables.highscoretwo);
		rankingvalues.set(2,Variables.highscorethree);
		rankingvalues.set(3,Variables.highscorefour);
		rankingvalues.set(4,Variables.highscorefive);
		ranking1.setText("1:"+Integer.toString(rankingvalues.get(0)));
		ranking2.setText("2:"+Integer.toString(rankingvalues.get(1)));
		ranking3.setText("3:"+Integer.toString(rankingvalues.get(2)));
		ranking4.setText("4:"+Integer.toString(rankingvalues.get(3)));
		ranking5.setText("5:"+Integer.toString(rankingvalues.get(4)));
		idlecounter.setText(Variables.autoincrementer + " "+name+"/sec" + "  " + Variables.incrementer + " "+name+"/click");
		counter.setText(Variables.potatoCounter+ " "+name);
		lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		if (Variables.prestigecounter >= 1) {
			clickupgradelabel.setText("Upgrades Lv." + Variables.prestigecounter);
			autoupgradelabel.setText("Clickers Lv." + Variables.prestigecounter);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// When the player clicks the potato

		if (e.getSource() == clicker) {
			playaudio("src//tick.wav");

			Variables.potatoCounter+=Variables.incrementer; // increases potatoes counted on click
			Variables.lifetimepotatoCounter+=Variables.incrementer; // increases lifetime potatoes on click

			// updates GUI

			counter.setText(Variables.potatoCounter+ " "+name);
			lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
			checkPrestige();
		}

		// timer updates every 1000 milliseconds (one second)
		if (e.getSource() == timer) {
			Variables.potatoCounter += Variables.autoincrementer;
			Variables.lifetimepotatoCounter += Variables.autoincrementer;
			idlecounter.setText(Variables.autoincrementer + " "+name+"/sec" + "  " + Variables.incrementer + " "+name+"/click");
			counter.setText(Variables.potatoCounter+ " "+name);
			lifetimecounter.setText("total "+name+" gathered: " + Variables.lifetimepotatoCounter);
		}

		// when the player clicks the reset button, game resets
		if (e.getSource() == resetbutton) {
			playaudio("src//tick.wav");
			reset();
		}
		
		if (e.getSource() == newgamebutton) {
			playaudio("src//tick.wav");
			startnewgame();
		}

		// When the player clicks the upgrade buttons, they get increased potatoes when they click

		if (e.getSource() == clickupgrade1 && Variables.potatoCounter >= Variables.plustenprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plustenprice;
			Variables.plustenqty += 1;
			Variables.plustenprice+=100;
			Variables.plusten+=Variables.plustenincrement;
			update();

		}
		if (e.getSource() == clickupgrade2 && Variables.potatoCounter >= Variables.plushundredprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plushundredprice;
			Variables.plushundredqty += 1;
			Variables.plushundredprice+=1000;
			Variables.plushundred+=Variables.plushundredincrement;
			update();

		}
		if (e.getSource() == clickupgrade3 && Variables.potatoCounter >= Variables.plusthousandprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.plusthousandprice;
			Variables.plusthousandqty += 1;
			Variables.plusthousandprice+=10000;
			Variables.plusthousand+=Variables.plusthousandincrement;
			update();
			
		}

		// When the player clicks the clicker buttons, they get potatoes automatically

		if (e.getSource() == autoupgrade1 && Variables.potatoCounter >= Variables.autooneprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autooneprice;
			Variables.autoone+=Variables.autooneincrement;
			Variables.autooneqty += 1;
			update();
			Variables.autooneprice+=10;

		}
		if (e.getSource() == autoupgrade2 && Variables.potatoCounter >= Variables.autotenprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autotenprice;
			Variables.autoten+=Variables.autotenincrement;
			Variables.autotenqty += 1;
			update();
			Variables.autotenprice+=100;
		}
		if (e.getSource() == autoupgrade3 && Variables.potatoCounter >= Variables.autohundredprice) {
			playaudio("src//orb.wav");
			Variables.potatoCounter -= Variables.autohundredprice;
			Variables.autohundred+=Variables.autohundredincrement;
			Variables.autohundredqty += 1;
			update();
			Variables.autohundredprice+=1000;

		}

		// when the player selects the change avatar button, rotates through different avatars

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

		// when the player selects the music button, changes the music

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
	
	// Annoying imported methods I can't get rid of, ignore these

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