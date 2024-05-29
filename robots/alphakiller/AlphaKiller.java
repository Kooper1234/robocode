package alphakiller;
import robocode.Robot;
import robocode.ScannedRobotEvent;


public class AlphaKiller extends Robot {

    double gabeDirection;
    double gunDirection;

    public void run(){

        double height = getBattleFieldHeight();
        double width = getBattleFieldWidth();
        double direction = getHeading();
        gunDirection = getGunHeading();
        double yPos = getY();
        double xPos = getX();

        if (yPos < height/2){
            turnRight(180-direction);
            ahead(height+yPos-50);
            if (xPos < width/2){
                turnRight(90);
                ahead(width-xPos-50);
            }
            else if (xPos >= width/2){
                turnLeft(90);
                ahead(width-xPos-50);
            }
        }
        else if (yPos >= height/2){
            turnRight(360-direction);
            ahead(height-yPos-50);
            if (xPos < width/2){
                turnLeft(90);
                ahead(width-xPos-50);
            }
            else if (xPos >= width/2){
                turnRight(90);
                ahead(width-xPos-50);
            }
        }
        while(true){  
            boolean inRange = false;
            if (((gabeDirection-30)<=(gunDirection)) && ((gabeDirection+30)>=(gunDirection)) && (gabeDirection != 0.0)){
                inRange = true;
                if (gunDirection <= gabeDirection){
                    turnLeft(gabeDirection-gunDirection);
                }
                else if (gunDirection > gabeDirection){
                    turnGunRight(gunDirection-gabeDirection);
                }
            } else {
                inRange = false;
            }
            while (inRange == true){
                turnGunLeft(60);
                turnGunRight(60);
            }
             
            turnGunLeft(360);


        }
        
    }

    public void onScannedRobot(ScannedRobotEvent e){
        fire(1);
        gabeDirection = gunDirection;

        
    }
}
