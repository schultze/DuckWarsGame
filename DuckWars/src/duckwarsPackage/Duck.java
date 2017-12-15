package duckwarsPackage;
import javax.swing.*; // for the graphics and its Class components

public class Duck {

	public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public final static int NUMBER_OF_DIRECTIONS = 4;
    protected JFrame duckJFrame;
    protected JLabel duckJLabel; // the container for the Image (of a dolphin)
    
    protected String duckImageName[] = new String[NUMBER_OF_DIRECTIONS];
    protected ImageIcon duckImage[] = new ImageIcon[NUMBER_OF_DIRECTIONS];
    protected int horizontalMovement;
    protected int verticalMovement;
    protected int hitsTaken;
    protected int hitsRequired;
    protected int directionChangeProbability;
    protected int duckDirection=UP;
    protected int xPos=0;  // top left corner of Image
    protected int yPos=0;  // top left corner of Image
    
    // Constructor
    public Duck (JFrame passedJFrame, int passedHitsRequired, int passedDirectionChangeProbability) {
    	
    	duckJFrame = passedJFrame;
        hitsRequired = passedHitsRequired;
        directionChangeProbability = passedDirectionChangeProbability;
        
        duckJLabel = new JLabel();
        duckJLabel.setBounds (10,10,10,10); // arbitrary, will change later
        duckJFrame.getContentPane().add(duckJLabel);
        duckJLabel.setVisible(false);
        duckJLabel.setVisible(true);

        horizontalMovement = 0;
        verticalMovement = 0;
        hitsTaken = 0;
        duckDirection = RIGHT;
        xPos = 250; // arbitrary starting point
        yPos = 10; // arbitrary starting point 
        
    }
    
    protected void drawDuck(){ 
       duckJLabel.setIcon(duckImage[duckDirection]);
       duckJLabel.setBounds(xPos,yPos,duckImage[duckDirection].getIconWidth(),duckImage[duckDirection].getIconHeight());  
       duckJLabel.setVisible(true);
    }
    
    protected void getDuckPos() {
    	duckJLabel.setBounds(xPos,yPos,duckImage[duckDirection].getIconWidth(),duckImage[duckDirection].getIconHeight());
    }

    protected void eraseDuck(){
    	duckJLabel.setVisible(false);
    }   
    
    protected boolean duckAtRightEdge(){
        return (xPos+duckJLabel.getWidth()+horizontalMovement > duckJFrame.getContentPane().getWidth());
    }
    
    protected boolean duckAtLeftEdge(){
        return (xPos - horizontalMovement < 0); // horizontalMovement variable is alway positive
    }
    
    protected boolean duckAtTopEdge(){
        return (yPos - verticalMovement < 0); // vertical Movement variable is always positive 
    }
    
    protected boolean duckAtBottomEdge(){
        return (yPos+duckJLabel.getHeight()+verticalMovement > duckJFrame.getContentPane().getHeight());
    }
    
   //update this boolean, is duck hit?
    public boolean isDuckHit(int xDuckPos, int yDuckPos){
    	if ((xPos <= xDuckPos 
                && xDuckPos <= xPos + duckImage[duckDirection].getIconWidth())
                && (yPos <= yDuckPos 
                && yDuckPos <= yPos + duckImage[duckDirection].getIconHeight())){
                    return true;
            }
            else {
                return false;
            }
        
    }
    
   public void duckIsHit(){
      hitsTaken++; 
    }
     
    public boolean duckLivesRemaining(){
        return hitsTaken >= hitsRequired;
    }
    
    public void duckInGame(){   
        hitsTaken = 0; // Originally 0.
        drawDuck();
    }
    
    public void erase(){
        eraseDuck();
    }
    
    public void move(){
    }
}