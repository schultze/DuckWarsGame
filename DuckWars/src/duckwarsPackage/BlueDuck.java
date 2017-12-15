package duckwarsPackage;

import javax.swing.*;

public class BlueDuck extends Duck {

	public BlueDuck (JFrame passedJFrame, int passedHitsRequired, int passedDirectionChangeProbability) {
		super(passedJFrame, passedHitsRequired, passedDirectionChangeProbability); // calls Duck's Constructor
		duckImageName[UP]="blueDuckUP.png";
		duckImageName[DOWN]="blueDuckUP.png"; //update image as DOWN.png
		duckImageName[LEFT]="blueDuckUP.png"; // update image as LEFT.png
		duckImageName[RIGHT]="blueDuckUP.png"; // update image as RIGHT.png
		
		for (int i = UP; i <= RIGHT; i++){// up =0, right = 3
			duckImage[i] = new ImageIcon (duckImageName[i]);
        }
        
        // movement is arbitrarily based on size of image
        horizontalMovement = duckImage[RIGHT].getIconWidth()/ 10; // arbitrary 1/10th of width
        verticalMovement = duckImage[UP].getIconHeight()/ 10; // arbitrary 1/10th of height;
		
	}
	
	public void move(){
        // change direction?
       if (Math.random()*100 < directionChangeProbability) // Math.random gives 0 to .9999
    	   duckDirection = (int) Math.floor(Math.random()*NUMBER_OF_DIRECTIONS); 
       
       if (duckDirection == LEFT)
           xPos -= horizontalMovement;
       else if (duckDirection == RIGHT)
           xPos += horizontalMovement;            
       else if (duckDirection == UP)
           yPos -= verticalMovement;
       else if (duckDirection == DOWN)
           yPos += verticalMovement;
       
       drawDuck();          
       
       //hit edge of window and need to turn around?
       if (duckDirection == UP && duckAtTopEdge()){
    	   duckDirection = DOWN;    
       }
       else if (duckDirection == DOWN && duckAtBottomEdge()){
    	   duckDirection = UP;  
       }
       else if (duckDirection == LEFT && duckAtLeftEdge()){
    	   duckDirection = RIGHT;   
       }
       else if (duckDirection == RIGHT && duckAtRightEdge()){
    	   duckDirection = LEFT;    
       }
   }
}