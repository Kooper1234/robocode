package alphakiller;

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
                goToPosition(width - 50, height - 50);
            } else {
                goToPosition(50, height - 50);
            }
        } else {
            if (xPos < width / 2) {
                goToPosition(width - 50, 50);
            } else {
                goToPosition(50, 50);
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
}