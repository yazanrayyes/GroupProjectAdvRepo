
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PotatoClickGUI implements ActionListener {
	
	JFrame frame;
	private JButton potatoButton;
	private ImageIcon potato;
	private JLabel image;
	JLabel counter;
	int potatoCounter = 0;
	
	
	//Done by Yazan, Khaled, Ismail
	// Outline 4/7
	
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
		
		counter=new JLabel(potatoCounter +" potatoes");
		counter.setForeground(Color.white);
		counter.setFont(new Font("Comic Sans Ms", Font.PLAIN, 32));
		counter.setBounds(400,60,200,100);
		
		frame.add(counter);
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
		counter.setText(potatoCounter+ " potatoes");
	}

}
