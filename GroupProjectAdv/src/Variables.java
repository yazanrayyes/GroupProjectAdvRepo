import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Potato Clicker Project V.3 Final by Yazan, Khaled, Ismael
//This class handles most of the math and variables
//Updated 5/11
//6.3
//Helped by Faris Natsheh

public class Variables {
	
	// Counters
	
	public static int prestigelevel = 100; // the number of potatoes required for a prestige level
	public static int prestigecounter = 0; // the number of prestige levels the player has
	public static int prestigebonus = 1; // the bonus potatoes the player earns (it multiplies by 2)
	public static int pendingprestige = 1; // the amount of bonus multipliers the player has earned
	
	public static int potatoCounter = 0; // counts the number of potatoes
	public static int lifetimepotatoCounter = 0; // counts the amount of potatoes collected over the course of the game
	public static int incrementer; //the amount of potatoes added (used to activate powerups)
	public static int autoincrementer; // the amount of potatoes added automatically
	
	// Upgrades
	
	// +10 potatoes per click

	public static int plusten=0; // total potatoes gained from this upgrade
	public static int plustenprice = 100; // price of upgrade
	public static int plustenqty = 0; // number of upgrades the player has
	public static int plustenincrement = 10*prestigebonus; // the number of potatoes each purchase adds
	
	// +100 potatoes per click

	public static int plushundred=0; // total potatoes gained from this upgrade
	public static int plushundredprice = 1000; // price of upgrade
	public static int plushundredqty = 0; // number of upgrades the player has
	public static int plushundredincrement = 100*prestigebonus; // the number of potatoes each purchase adds

	// +1000 potatoes per click
	
	public static int plusthousand=0; // total potatoes gained from this upgrade
	public static int plusthousandprice = 10000; // price of upgrade
	public static int plusthousandqty = 0; // number of upgrades the player has
	public static int plusthousandincrement = 1000*prestigebonus; // the number of potatoes each purchase adds
	
	 // +1 potatoes per second

	public static int autoone = 0; // total potatoes gained from this upgrade
	public static int autooneprice = 100; // price of upgrade
	public static int autooneqty = 0; // number of upgrades the player has
	public static int autooneincrement = 1*prestigebonus; // the number of potatoes each purchase adds
	
	 // +10 potatoes per second

	public static int autoten = 0; // total potatoes gained from this upgrade
	public static int autotenprice = 1000; // price of upgrade
	public static int autotenqty = 0; // number of upgrades the player has
	public static int autotenincrement = 10*prestigebonus; // the number of potatoes each purchase adds
	
	 // +100 potatoes per second
	
	public static int autohundred = 0; // total potatoes gained from this upgrade
	public static int autohundredprice = 10000; // price of upgrade
	public static int autohundredqty = 0; // number of upgrades the player has
	public static int autohundredincrement = 100*prestigebonus; // the number of potatoes each purchase adds
	
	public static ArrayList<String> savedvalues = new ArrayList<String>(); // our arraylist of strings grabbed from our save file
	public static ArrayList<Integer> intvalues = new ArrayList<Integer>(); // our arraylist of number values, converted from strings
	
	// High Scores
	
	public static int highscoreone = 1;
	public static int highscoretwo = 0;
	public static int highscorethree = 0;
	public static int highscorefour = 0;
	public static int highscorefive = 0;
	
	
	public static String line; // the string where the game is saved
	
	// saves our game
	//converts every variable into a string, writes it onto a line in a text file
	
	public static void savegame() {
		String savedata = (""+potatoCounter+" "+lifetimepotatoCounter+" "+incrementer
		+" "+autoincrementer+" "+prestigebonus+" "+prestigelevel+" "+prestigecounter+" "+pendingprestige+" "+plusten+" "+plustenprice+" "+plustenqty+" "+plustenincrement+" "
		+plushundred+" "+plushundredprice+" "+plushundredqty+" "+plushundredincrement+" "+plusthousand+" "+plusthousandprice+" "+plusthousandqty+" "+plusthousandincrement+
		" "+autoone+" "+autooneprice+" "+autooneqty+" "+autooneincrement+" "+autoten+" "+autotenprice+" "+autotenqty+" "+autotenincrement+" "+autohundred+" "+autohundredprice+" "
		+autohundredqty+" "+autohundredincrement+" "+highscoreone+" "+highscoretwo+" "+highscorethree+" "+highscorefour+" "+highscorefive+" ");
		
		FileOutputStream stream= null;
        try {
            stream= new FileOutputStream("src//savefile.txt");
            stream.write(savedata.getBytes("UTF-8"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
	}
	
	// loads our game
	// reads the line on our text file, separates each number
	// puts each number into an arraylist, then converts them to integers and sets them to variables
	
	public static void loadgame() {
        try {
            FileReader filereader = new FileReader("src//savefile.txt");
            BufferedReader linereader = new BufferedReader(filereader);
            
            Variables.line = linereader.readLine();
            
            filereader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < Variables.line.length(); i++) {
        	if (Variables.line.indexOf(" ") == i) {
        		savedvalues.add(Variables.line.substring(0,i));
        		Variables.line = Variables.line.substring(i+1);
        		i = 0;
        	}
        }
        
        for (int i = 0; i < Variables.savedvalues.size(); i++) {
        	intvalues.add(Integer.parseInt(Variables.savedvalues.get(i)));
        }
        
		potatoCounter = intvalues.get(0);
		lifetimepotatoCounter = intvalues.get(1);
		incrementer = intvalues.get(2);
		autoincrementer = intvalues.get(3);
		prestigebonus = intvalues.get(4);
		prestigelevel = intvalues.get(5);
		prestigecounter = intvalues.get(6);
		pendingprestige = intvalues.get(7);
		plusten= intvalues.get(8);
		plustenprice = intvalues.get(9);
		plustenqty = intvalues.get(10);
		plustenincrement = intvalues.get(11);
		plushundred= intvalues.get(12);
		plushundredprice = intvalues.get(13);
		plushundredqty = intvalues.get(14);
		plushundredincrement = intvalues.get(15);
		plusthousand= intvalues.get(16);
		plusthousandprice = intvalues.get(17);
		plusthousandqty = intvalues.get(18);
		plusthousandincrement = intvalues.get(19);
		autoone = intvalues.get(20);
		autooneprice = intvalues.get(21);
		autooneqty = intvalues.get(22);
		autooneincrement = intvalues.get(23);
		autoten = intvalues.get(24);
		autotenprice = intvalues.get(25);
		autotenqty = intvalues.get(26);
		autotenincrement = intvalues.get(27);
		autohundred = intvalues.get(28);
		autohundredprice = intvalues.get(29);
		autohundredqty = intvalues.get(30);
		autohundredincrement = intvalues.get(31);
		highscoreone = intvalues.get(32);
		highscoretwo = intvalues.get(33);
		highscorethree = intvalues.get(34);
		highscorefour = intvalues.get(35);
		highscorefive = intvalues.get(36);
	}
	
	// resets our game
	// converts most variables back to their default values (except for prestige, lifetime potatoes and leaderboard)

	public static void resetgame() {
		
		prestigebonus = pendingprestige;
		pendingprestige = 1;
		potatoCounter = 0;
		incrementer = 0;
		autoincrementer = 0;
		plusten =0;
		plustenprice = 100;
		plustenqty = 0;
		plustenincrement *= prestigebonus;
		plushundred = 0;
		plushundredprice = 1000;
		plushundredqty = 0;
		plushundredincrement *= prestigebonus;
		plusthousand = 0;
		plusthousandprice = 10000;
		plusthousandqty = 0;
		plusthousandincrement *= prestigebonus;
		autoone = 0;
		autooneprice = 100;
		autooneqty = 0;
		autooneincrement *= prestigebonus;
		autoten = 0;
		autotenprice = 1000;
		autotenqty = 0;
		autotenincrement *= prestigebonus;
		autohundred = 0;
		autohundredprice = 10000;
		autohundredqty = 0;
		autohundredincrement *= prestigebonus;
	}
	
	// starts a new game
	// resets every variable back to its default value (except for leaderboard)
	
	public static void newgame() {
		
		prestigelevel = 100;
		prestigecounter = 0;
		prestigebonus = 1;
		pendingprestige = 1;
		potatoCounter = 0;
		lifetimepotatoCounter = 0;
		incrementer = 0;
		autoincrementer = 0;
		plusten =0;
		plustenprice = 100;
		plustenqty = 0;
		plustenincrement = 10;
		plushundred = 0;
		plushundredprice = 1000;
		plushundredqty = 0;
		plushundredincrement = 100;
		plusthousand = 0;
		plusthousandprice = 10000;
		plusthousandqty = 0;
		plusthousandincrement = 1000;
		autoone = 0;
		autooneprice = 100;
		autooneqty = 0;
		autooneincrement = 1;
		autoten = 0;
		autotenprice = 1000;
		autotenqty = 0;
		autotenincrement = 10;
		autohundred = 0;
		autohundredprice = 10000;
		autohundredqty = 0;
		autohundredincrement = 100;
		savedvalues = new ArrayList<String>();
		intvalues = new ArrayList<Integer>();
	}
}
