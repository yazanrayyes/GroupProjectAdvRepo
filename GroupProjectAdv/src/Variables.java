import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Potato Clicker Project V.3 by Yazan, Khaled, Ismael
//Updated 5/9
//6.3
//Helped by Faris Natsheh

public class Variables {
	
	// Counters

	public static int potatoCounter = 0; // counts the number of potatoes
	public static int lifetimepotatoCounter = 0; // counts the amount of potatoes collected over the course of the game
	public static int incrementer; //the amount of potatoes added (used to activate powerups)
	public static int autoincrementer; // the amount of potatoes added automatically

	// Upgrades

	public static int plusten=0; // +10 potatoes per click
	public static int plustenprice = 100;
	public static int plustenqty = 0;

	public static int plushundred=0; // +100 potatoes per click
	public static int plushundredprice = 1000;
	public static int plushundredqty = 0;

	public static int plusthousand=0; // +1000 potatoes per click
	public static int plusthousandprice = 10000;
	public static int plusthousandqty = 0;

	public static int autoone = 0; // +1 potatoes per second
	public static int autooneprice = 100;
	public static int autooneqty = 0;

	public static int autoten = 0; // +10 potatoes per second
	public static int autotenprice = 1000;
	public static int autotenqty = 0;

	public static int autohundred = 0; // +100 potatoes per second
	public static int autohundredprice = 10000;
	public static int autohundredqty = 0;
	
	public static ArrayList<String> savedvalues = new ArrayList<String>();
	public static ArrayList<Integer> intvalues = new ArrayList<Integer>();
	
	public static String line;
	
	public static void savegame() {
		String savedata = (""+potatoCounter+" "+lifetimepotatoCounter+" "+incrementer
		+" "+autoincrementer+" "+plusten+" "+plustenprice+" "+plustenqty+" "+plushundred+" "+plushundredprice
        +" "+plushundredqty+" "+plusthousand+" "+plusthousandprice+" "+plusthousandqty+" "+autoone+" "+autooneprice+" "
        +autooneqty+" "+autoten+" "+autotenprice+" "+autotenqty+" "+autohundred+" "+autohundredprice+" "+autohundredqty+" ");
		FileOutputStream stream= null;
        try {
            stream= new FileOutputStream("src//savefile.txt");
            stream.write(savedata.getBytes("UTF-8"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
	}
	
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
		plusten= intvalues.get(4);
		plustenprice = intvalues.get(5);
		plustenqty = intvalues.get(6);
		plushundred= intvalues.get(7);
		plushundredprice = intvalues.get(8);
		plushundredqty = intvalues.get(9);
		plusthousand= intvalues.get(10);
		plusthousandprice = intvalues.get(11);
		plusthousandqty = intvalues.get(12);
		autoone = intvalues.get(13);
		autooneprice = intvalues.get(14);
		autooneqty = intvalues.get(15);
		autoten = intvalues.get(16);
		autotenprice = intvalues.get(17);
		autotenqty = intvalues.get(18);
		autohundred = intvalues.get(19);
		autohundredprice = intvalues.get(20);
		autohundredqty = intvalues.get(21);
	}

	public static void resetgame() {
		
		potatoCounter = 0;
		incrementer = 0;
		autoincrementer = 0;
		plusten =0;
		plustenprice = 100;
		plustenqty = 0;
		plushundred = 0;
		plushundredprice = 1000;
		plushundredqty = 0;
		plusthousand = 0;
		plusthousandprice = 10000;
		plusthousandqty = 0;
		autoone = 0;
		autooneprice = 100;
		autooneqty = 0;
		autoten = 0;
		autotenprice = 1000;
		autotenqty = 0;
		autohundred = 0;
		autohundredprice = 10000;
		autohundredqty = 0;
	}
}
