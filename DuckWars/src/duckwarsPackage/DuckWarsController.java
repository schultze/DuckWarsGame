package duckwarsPackage;

import javax.swing.*; // all of Class Swing components
import java.awt.*; // for graphics and keyboard listener
import java.awt.event.*; //do I need this for events and listener?
import java.util.*; // for Timer and its Class components

/*
 * Description: Duck Wars is a two-dimensional third-person shooter game.
 * The player controls man’s best friend, a dog, and fights off ducks who claim war
 * among humanity for duck season. The player has an arsenal of firepower to stop the
 * wave of ducks; however, the ducks are able to fire back with their own firepower.
 * The ducks will randomly move on the screen, and if they are successful of taking down
 * man’s loyal companion first than it’s game over. The goal of the player is
 * to defeat all the ducks without dying. Player(s) are able to earn points,
 * which can gain a life when achieving 1,500 points.
 * The maximum lives a player can have is five, but at the start of the game
 * is three lives.
 * 
 * The following credit below is thanked for assisting to make this game work:
 * 
 * Steve Harper for explaining and suggestions on the duck and bullet.
 *
 */

public class DuckWarsController extends TimerTask implements KeyListener {
	
	private JFrame jFrame = new JFrame();
	private Container container;
	public static final int MOVE_IN_MILLISECONDS = 70; // time in milliseconds on timer
	private boolean gameIsReady = false;
	
	
	// the timer
    private java.util.Timer gameTimer = new java.util.Timer();
    private int xKeyOffsetToContentPaneFromJFrame = 0;
    private int yKeyOffsetToContentPaneFromJFrame = 0;
	
	// variables for the dog player
	
	
	private JLabel background = new JLabel(new ImageIcon("background.jpg"));
	
    public static final int DOG_PLAYER = 0; // must be in order of appearance
	public static final int DOG_LIVES = 1;
	 
    public static final int NUMBER_OF_DOGS = 1;// to match the number of game levels blue = 1
    public static final int MAX_NUMBER_OF_DOGS = 1; // used for array sizing (changes the amount per type of duck) 1 duck per type
	
    private int dogPlayer[] = new int [MAX_NUMBER_OF_DOGS]; // changed dogPlayer to an array of integers. Keeps track of maximum amount per type of dog on screen.
    private Dog gameDog[][] = new Dog[MAX_NUMBER_OF_DOGS][NUMBER_OF_DOGS]; 

    // variables for the duck
	public static final int BLUE_DUCK = 0; // must be in order of appearance
	public static final int DUCK_LIVES = 1;
	 
    public static final int NUMBER_OF_DUCK_TYPES = 1;// to match the number of game levels blue = 1
    public static final int MAX_NUMBER_OF_DUCKS = 4; // used for array sizing (changes the amount per type of duck) 1 duck per type
    
    private int duckLevel[] = new int [MAX_NUMBER_OF_DUCKS]; // changed duckLevel to an array of integers. Keeps track of maximum amount per type of duck on screen.
    
    // Class Duck - gameDuck two dimensional array
    private Duck gameDuck[][] = new Duck[MAX_NUMBER_OF_DUCKS][NUMBER_OF_DUCK_TYPES];
    private Duck duck;
    // integers to count hits
    private int countHit = 0;
    
    // only testing an image here.
    //JLabel duckPoo = new JLabel(new ImageIcon("duckDooDoo.png"));
    
	// Constructor
	public DuckWarsController(String frameTitle, int frameX, int frameY, int frameWidth, int frameHeight){
		jFrame = new JFrame(frameTitle);
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.setLocation(frameX, frameY);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jFrame.setSize(800,479);//repeats of the jFrame size.
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false); // if false, the frame is stretched a little to low.
		container = jFrame.getContentPane();
		container.setLayout(null);
		
		int borderWidth = (frameWidth - container.getWidth())/2;  // 2 since border on either side
        xKeyOffsetToContentPaneFromJFrame = borderWidth;
        yKeyOffsetToContentPaneFromJFrame = frameHeight - container.getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
		
		// creates the dog, now the JFrame has been initialized.
		for (int i = 0; i < MAX_NUMBER_OF_DOGS; i++) {
			gameDog[i][DOG_PLAYER] = new Dog(jFrame,1,4);
		}
		
		// creates the duck, now that JFrame has been initialized
        for (int j = 0; j < MAX_NUMBER_OF_DUCKS; j++) { // gameDolphin[] must have an integer for the array. 9-17-17
        gameDuck[j][BLUE_DUCK] = new BlueDuck(jFrame,1,10);// JFrame, hits required 2, % change direction
       // gameDuck[j][2ndDUCK?] = new 2ndDUCK?(jFrame,1,25);// JFrame, hits required 1, % change direction
       // gameDuck[j][3rdDUCK?] = new 3rdDUCK?(jFrame,1,50);// JFrame, hits required 1, % change direction  
        }
        
        // Places in and starts the game with dog player.
        startDogPlayer(DOG_PLAYER);
        
        // Place in game and starts with the first type of duck.
        resetGame(BLUE_DUCK);
        
        // added in the background image.
        container.add(background);
		background.setBounds(0,0,800,450);
        
     // start the timer
        gameTimer.schedule(this, 0, MOVE_IN_MILLISECONDS);
		
		// register this class as a key event listener for the JFrame
        jFrame.addKeyListener(this);
	}
	
	private Dog currentDog(int amountOfDogs){
        return gameDog[amountOfDogs][dogPlayer[amountOfDogs]];
    }
	
	private void startDogPlayer(int strtDogPlayer) {
		for (int i = 0; i < MAX_NUMBER_OF_DOGS; i++) {
    		if (dogPlayer[i] < DOG_LIVES) {
    			currentDog(i).eraseDog();
    		}
    	}
        gameIsReady = false;
        
        for (int i = 0; i < MAX_NUMBER_OF_DOGS; i++) {
        	dogPlayer[i] = strtDogPlayer;
        	currentDog(i).dogInGame();
        }
        gameIsReady = true;
        countHit = 0;
	}
	
	 private Duck currentDuck(int amountOfDucks){
	        return gameDuck[amountOfDucks][duckLevel[amountOfDucks]];
	    }
	
	private void resetGame(int startDuckLevel) {
		for (int i = 0; i < MAX_NUMBER_OF_DUCKS; i++) {
    		if (duckLevel[i] < DUCK_LIVES) {
    			currentDuck(i).eraseDuck();
    		}
    	}
        gameIsReady = false;
        
        for (int i = 0; i < MAX_NUMBER_OF_DUCKS; i++) {
        	duckLevel[i] = startDuckLevel;
        	currentDuck(i).duckInGame();
        }
        gameIsReady = true;
        countHit = 0;
	}
	
	private void duckGotHit(int i){
        currentDuck(i).duckIsHit();
        if (currentDuck (i).duckLivesRemaining()){
            currentDuck (i).eraseDuck();
            duckLevel[i]++;
            if (duckLevel [i] < DUCK_LIVES) { // if not done, go to next level of duck
                currentDuck(i).duckInGame();
            }
        }
    }

	private boolean didDogWin(int countDucks){
    	for (int i = 0; i < MAX_NUMBER_OF_DUCKS; i++) { // for loop to set the certain amount of ducks in the game. duckLevel[] must have an integer for the array.
    		if (duckLevel[i] == DUCK_LIVES) {
    			countDucks++;
    		}
    	}
    	if (countDucks == MAX_NUMBER_OF_DUCKS) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
	// a collision method for dog with duck.
	//private void dogBitesDuck() {}
	// update this some tonight or tomorrow morning.

    private void dogBites() {
    	

    }

	// Timer Task generates the run method.
	// this run() function overrides run() in java.util.TimerTask
    // this is "run" every time the timer expires (yes, they could have picked a better name)
	public void run() {
		
    	for (int i = 0; i < MAX_NUMBER_OF_DUCKS; i++) { // for loop to set the certain amount of dolphins in the game.
    	if (gameIsReady && duckLevel[i] < DUCK_LIVES){ // if the game is ready and the level is ready.
            currentDuck(i).move();
        	}
    	
    	}
    	
      }

	public void keyTyped(KeyEvent e) {
		System.out.println("This is KeyTyped.");
	}

	public void keyPressed(KeyEvent e) {
		
		System.out.println("This is KeyPressed.");		
		System.out.println("getKeyChar: " + e.getKeyChar() + " | getKeyCode: " + e.getKeyCode());
		for (int i = 0; i < MAX_NUMBER_OF_DOGS; i++) { // for loop to set the certain amount of dogs in the game.
	    	if (gameIsReady && duckLevel[i] < DUCK_LIVES){ // if the game is ready and the level is ready.
	    		
	    	}
	    // do I need to be more specific in 4 methods?
		if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP)
			currentDog(i).moveUP();
		
		if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN)
			currentDog(i).moveDOWN();
		
		if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT)
			currentDog(i).moveLEFT();
		
		if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT)
			currentDog(i).moveRIGHT();
		}
		
		
	}

	public void keyReleased(KeyEvent e) {;}
	
	// main
	public static void main(String[] args) {
		// 0,0,800,489 change this back to JFrame if setResizeable is true or non-existent.
		//new DuckWarsController("Duck Wars", 0, 0, 800, 479); // frameTitle, frameX, frameY, frameWidth, frameHeight);
		new StartTitle();
	}
} 