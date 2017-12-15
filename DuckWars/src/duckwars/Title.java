package duckwars;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 * This is not the best way to handle a title; however, the purpose is to blink a text like a video game in the 1980s.
 */
public class Title implements KeyListener {
	private JFrame frame = new JFrame();
	public static final int WIDTH = 806;
	public static final int HEIGHT = 725;
	private JLabel blinkerText = new JLabel(new ImageIcon("pressSpacebar.PNG"));
	private JLabel startTitle = new JLabel(new ImageIcon("startTitle.PNG"));
	private ImageIcon icon = new ImageIcon("icon.PNG");
	private Timer timer;
	private Blinker blinker = new Blinker();
	private File intro = new File("titleScreen.wav");
	//The constructor of Title.
	public Title () {
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Duck Wars");
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Container con = frame.getContentPane();
		con.setLayout(null);
		
		timer = new Timer(950, blinker);
		timer.start();
		frame.add(blinkerText);
		blinkerText.setBounds(-10, 0, WIDTH, HEIGHT);
		frame.add(startTitle);
		startTitle.setBounds(-10, 0, WIDTH, HEIGHT);
		
		con.setBackground(Color.BLACK);
		
		frame.addKeyListener(this);
		
		Sound.playClip(intro);
	}	
//The inner class.
private class Blinker implements ActionListener {
	private boolean blink = true;
		
		public void actionPerformed(ActionEvent e) {
			if(blink) {
				blinkerText.setVisible(true);
			}
			else {
				blinkerText.setVisible(false);
			}
			blink = !blink;
			}
		}
	//The SPACEBAR key launches the game.
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_SPACE) {
			Sound.stopClip(intro);
			frame.dispose();
			new DuckWarsGame();
		}
	}	
	//Must have these methods to implement the KeyListener.
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) {}
}