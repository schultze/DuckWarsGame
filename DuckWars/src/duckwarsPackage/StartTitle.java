package duckwarsPackage;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class StartTitle extends JFrame implements KeyListener {
	/* The variables.*/	
	private Container con;
	private JLabel blinkText = new JLabel(new ImageIcon("pressSpacebar.PNG"));
	private JLabel title = new JLabel(new ImageIcon("startTitle.PNG"));
	
	/* Title's constructor.*/
	public StartTitle() {
		
		setSize(800,450);
		setTitle("Duck Wars");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		con = getContentPane();
		con.setLayout(null);

		Timer timer = new Timer(1250, new Blinker());
		timer.start();
		
		con.add(blinkText);
		blinkText.setBounds(0, 0, 800, 450);
		
		con.add(title);
		title.setBounds(0, 0, 800, 450);
		
		con.setBackground(Color.BLACK);
		
		addKeyListener(this);
	}
	
	/* 2nd inner Class.*/
	private class Blinker implements ActionListener {
		
		private boolean blink = true;
	
	/* Returns the action to the Timer.*/	
	public void actionPerformed(ActionEvent event)
		
	{
		if(blink)
		{
			blinkText.setVisible(true);
		}
		else
		{
			blinkText.setVisible(false);
		}
		blink = !blink;
		}
	}
	
	/* Pressing the SPACEBAR begins the game.*/
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == KeyEvent.VK_SPACE) { // must have a bracket to force user to press a specific key.
			dispose(); // closes the StartTitle so it begins the game.
		new DuckWarsController("Duck Wars", 0, 0, 800, 479); // frameTitle, frameX, frameY, frameWidth, frameHeight);
		}
	}	
	/* Must have these methods to implement the KeyListener.*/
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) {}
	
}