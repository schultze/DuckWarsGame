package duckwarsPackage;

import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel; // for JLabel
import java.awt.*;

import javax.swing.ImageIcon; // for ImageIcon
//Remember it's only one dog for now.
public class Dog {
	private Duck duck;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public static final int NUMBER_OF_DIRECTIONS = 4;
    protected JFrame dogJFrame;
    protected JLabel dogJLabel; // the container for the Image (of a dolphin)
    
    protected String dogImageName[] = new String[NUMBER_OF_DIRECTIONS];
    protected ImageIcon dogImage[] = new ImageIcon[NUMBER_OF_DIRECTIONS];
    protected int horizontalMovement;
    protected int verticalMovement;
    protected int hitsTaken;
    protected int hitsRequired;
    protected int directionChangeProbability;
    protected int dogDirection;
    protected int xPos;  // top left corner of Image
    protected int yPos;  // top left corner of Image
    
    // Constructor
    public Dog (JFrame passedJFrame, int passedHitsRequired, int passedDirectionChangeProbability) {
    	
    	dogJFrame = passedJFrame;
        hitsRequired = passedHitsRequired;
        directionChangeProbability = passedDirectionChangeProbability;
        
        dogJLabel = new JLabel();
        dogJLabel.setBounds (10, 10, 10, 10); // arbitrary, will change later
        dogJFrame.getContentPane().add(dogJLabel);
        dogJLabel.setVisible(false);
        dogJLabel.setVisible(true);

        horizontalMovement = 0;
        verticalMovement = 0;
        hitsTaken = 0;
        dogDirection = RIGHT;
        xPos = 350; // arbitrary starting point
        yPos = 350; // arbitrary starting point
        
        // update with adding in images for each direction.
        dogImageName[UP]="dogUP.png";
		dogImageName[DOWN]="dogUP.png"; //update image as DOWN.png
		dogImageName[LEFT]="dogUP.png"; // update image as LEFT.png
		dogImageName[RIGHT]="dogUP.png"; // update image as RIGHT.png
		
		for (int i = UP; i <= RIGHT; i++){// up =0, right = 3
			dogImage[i] = new ImageIcon (dogImageName[i]);
        }
        
        // movement is arbitrarily based on size of image
        horizontalMovement = dogImage[RIGHT].getIconWidth()/ 9; // arbitrary 1/9th of width
        verticalMovement = dogImage[UP].getIconHeight()/ 8; // arbitrary 1/9th of height;
        
    }

	protected void drawDog(){ 
       dogJLabel.setIcon(dogImage[dogDirection]);
       dogJLabel.setBounds(xPos,yPos,dogImage[dogDirection].getIconWidth(),dogImage[dogDirection].getIconHeight());  
       dogJLabel.setVisible(true);
    }

    protected void eraseDog(){
    	dogJLabel.setVisible(false);
    }

	protected boolean dogAtRightEdge(){
        return (xPos+dogJLabel.getWidth()+horizontalMovement > dogJFrame.getContentPane().getWidth());
    }
    
    protected boolean dogAtLeftEdge(){
        return (xPos - horizontalMovement < 0); // horizontalMovement variable is alway positive
    }
    
    protected boolean dogAtTopEdge(){
        return (yPos - verticalMovement < 0); // vertical Movement variable is always positive 
    }
    
    protected boolean dogAtBottomEdge(){
        return (yPos+dogJLabel.getHeight()+verticalMovement > dogJFrame.getContentPane().getHeight());
    }
    
   //update this boolean, is dog hit?
    public boolean isDogHit(int xDogPos, int yDogPos){
    	if ((xPos <= xDogPos 
                && xDogPos <= xPos + dogImage[dogDirection].getIconWidth())
                && (yPos <= yDogPos 
                && yDogPos <= yPos + dogImage[dogDirection].getIconHeight())){
                    return true;
            }
            else {
                return false;
            }
    }
    
   public void dogIsHit(){
      hitsTaken++; 
    }
     
    public boolean dogLivesRemaining(){
        return hitsTaken >= hitsRequired;
    }
    
    public void dogInGame(){   
        hitsTaken = 0;
        drawDog();
    }
    
    public void erase(){
        eraseDog();
    }
    
    public void moveUP(){
    	yPos -= verticalMovement;
    	drawDog();
    	//hits edge of window and needs to stop & turn around.
    	if (dogAtTopEdge() && dogDirection == UP)
    	yPos += verticalMovement;
    	
    }
    
    public void moveLEFT() {
    	xPos -= horizontalMovement;
    	drawDog();
    	//hits edge of window and needs to stop & turn around.
    	if (dogAtLeftEdge()) {
    	xPos += horizontalMovement;
    	}
    }
    
    public void moveDOWN() {
    	yPos += verticalMovement;
    	drawDog();
    	//hits edge of window and needs to stop & turn around.
    	if (dogAtBottomEdge())
    	yPos -= verticalMovement;
    }
    
    public void moveRIGHT() {
    	xPos += horizontalMovement;
    	drawDog();
    	//hits edge of window and needs to stop & turn around.
    	if (dogAtRightEdge())
    	xPos -= horizontalMovement;
    }

}