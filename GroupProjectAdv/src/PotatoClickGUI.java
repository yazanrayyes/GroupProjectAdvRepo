
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PotatoClickGUI {
	
	JFrame frame;
	private Button potatobutton;
	private ImageIcon potato;
	private JLabel image;
	
	
	//Done by Yazan, Khaled, Ismail
	// Outline 4/7
	
	public PotatoClickGUI() {
		
		JFrame frame = new JFrame();
		
		// Will set image Icon here
		potato = new ImageIcon("C:\\Users\\YazanAlrayyes21\\git\\GroupProjectAdvRepo\\GroupProjectAdv\\src\\potato.png");
		Image originalPotato = potato.getImage();
		Image scaled= originalPotato.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
		ImageIcon potato= new ImageIcon (scaled);
		image = new JLabel(potato);
		
		Panel potatoImage = new Panel();
		potatoImage.setBounds(250, 200, 500, 200);
		potatoImage.add(image);
		frame.add(potatoImage);
		
		
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setFocusable(true);
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
	
	public static void main (String args[]) {
		new PotatoClickGUI();
	}

}
