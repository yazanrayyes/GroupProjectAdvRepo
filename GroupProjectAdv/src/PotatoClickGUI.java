
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
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
		image = new JLabel(potato);
		frame.add(image);
		
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
			
	}

}
