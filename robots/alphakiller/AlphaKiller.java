package alphakiller;

import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class AlphaKiller extends Robot {

    double gabeDirection;

    public void run() {
        // Move to the nearest corner with 50 distance spaces from the walls
        moveToCorner();

        // Continuous scanning and targeting
        while (true) {
            turnGunLeft(360); // Scan 360 degrees
            ahead(40-Math.random()*20);
            double r = Math.random();
            if(r>=.5){
                turnRight(Math.random()*50);
                ahead(40-Math.random()*20);
            }else if(r<.5){
                turnLeft(Math.random()*50);
                ahead(40-Math.random()*20);
            }
            i
        }
    }

    // Method to move the robot to the nearest corner with some distance from the walls
    private void moveToCorner() {
        double height = getBattleFieldHeight();
        double width = getBattleFieldWidth();
        double yPos = getY();
        double xPos = getX();

        if (yPos < height / 2) {
            if (xPos < width / 2) {
                goToPosition(width - 250, height - 300);
            } else {
                goToPosition(400, height - 300);
            }
        } else {
            if (xPos < width / 2) {
                goToPosition(width - 300, 300);
            } else {
                goToPosition(400, 450);
            }
        }
    }

    // Method to move the robot to a specific position
    private void goToPosition(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double angle = Math.toDegrees(Math.atan2(dx, dy));
        turnRight(normalizeBearing(angle - getHeading()));
        ahead(distance);
    }

    // Method to normalize bearing between -180 and 180 degrees
    private double normalizeBearing(double angle) {
        while (angle > 180) {
            angle -= 360;
        }
        while (angle < -180) {
            angle += 360;
        }
        return angle;
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Lock onto the target's direction
        gabeDirection = getHeading() + e.getBearing();
        // Turn gun towards the target
        turnGunRight(normalizeBearing(gabeDirection - getGunHeading()));
        // Fire at the target
        fire(2);
    }
    public void onHitWall(HitWallEvent h){
        turnRight(180);
    }
}