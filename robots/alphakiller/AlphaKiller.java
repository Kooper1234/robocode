package alphakiller;
import robocode.Robot;
import robocode.ScannedRobotEvent;


public class AlphaKiller extends Robot {
    public void run(){

        double height = getBattleFieldHeight();
        double width = getBattleFieldWidth();
        double direction = getHeading();
        double gunDirection = getGunHeading();
        turnRight(360-direction);
        ahead()



        while(true){

            

        }
        
    }

    public void onScannedRobot(ScannedRobotEvent e){
        fire(1);
        
    }
}
