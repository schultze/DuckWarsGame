package duckwarsPackage;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DuckDooDoo {
    protected JLabel dooDooJLabel; // the container for the Image (duck's Doo-Doo)
    protected ArrayList duckDooDoos = new ArrayList();
    protected JFrame dooDooJFrame;
    protected ImageIcon dooDooImage = new ImageIcon("duckDooDoo.png");
    protected int horizontalMovement;
    protected int verticalMovement;
    protected int hitsTaken;
    protected int hitsRequired;
    protected int numOfDooDoo;
    protected int xPos;  // top left corner of Image
    protected int yPos;  // top left corner of Image
    protected Duck duck;
    
    // Constructor
    public DuckDooDoo () {

        dooDooJLabel = new JLabel();
        dooDooJLabel.setBounds (10, 10, 10, 10); // arbitrary, will change later
        dooDooJFrame.getContentPane().add(dooDooJLabel);
        dooDooJLabel.setVisible(false);
        dooDooJLabel.setVisible(true);
        numOfDooDoo = 1;
        horizontalMovement = 0;
        verticalMovement = 0;
        hitsTaken = 0;
        xPos = 250; // arbitrary starting point
        yPos = 10; // arbitrary starting point     
    }



	protected void drawDooDoo(){ 
       dooDooJLabel.setIcon(dooDooImage);
       dooDooJLabel.setBounds(xPos,yPos,dooDooImage.getIconHeight(),dooDooImage.getIconHeight());  
       dooDooJLabel.setVisible(true);
    }

    protected void eraseDooDoo(){
    	dooDooJLabel.setVisible(false);
    }

    
    protected boolean intersects(JLabel dooDooJLabel) {
		// TODO Auto-generated method stub
		return false;
	}
    
    protected boolean dooDooAtBottomEdge(){
        return (yPos+dooDooJLabel.getHeight()+verticalMovement > dooDooJFrame.getContentPane().getHeight());
    }
    
   //update this boolean, does doo-doo hit?
    public boolean didDooDooHit(int xDooDoo, int yDooDoo){
    	if ((xPos <= xDooDoo 
                && xDooDoo <= xPos + dooDooImage.getIconWidth())
                && (yPos <= yDooDoo 
                && yDooDoo <= yPos + dooDooImage.getIconHeight())){
                    return true;
            }
            else {
                return false;
            }
    }
    
   public void dooDooHits(){
      hitsTaken++; 
    }
     
    public boolean dooDooRemaining(){
        return hitsTaken >= hitsRequired;
    }
    
    public void dooDooInGame(){   
        hitsTaken = 0;
        drawDooDoo();
    }
    
    public void erase(){
        eraseDooDoo();
    }
    
    public void move() {
    	
     	
    	yPos += verticalMovement;
    
    	drawDooDoo();          
        
        //hits edge of window then should be erased.

        if (dooDooAtBottomEdge()){
     	   eraseDooDoo();
        }
    }


}