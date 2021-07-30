package Decepticons;
import robocode.*;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.HitRobotEvent;
import java.awt.Color;
import java.awt.*;

import robocode.DeathEvent;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Megatron - a robot by (your name here)
 */
public class Megatron extends Robot
{
   int others; // Number of other robots in the game
	static int corner = 0; // Which corner we are currently using
	// static so that it keeps it between rounds.
	boolean stopWhenSeeRobot = false; // See goCorner()
    boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move
	/**
	 * run: Megatron's default behavior
	 */
	public void run() {
	
		// and the next line:
         setBodyColor(new Color(0,0,0));
		setGunColor(new Color(166, 35, 35));
		setRadarColor(new Color(0, 100, 100));
		setBulletColor(new Color(255, 255, 100));
		setScanColor(new Color(255, 200, 200));
		// Initialization of the robot should be put here
moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;
		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		back(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(90);
		turnRight(90);
		// After trying out your robot, try uncommenting the import at the top,
      others = getOthers();
		// Initialize gun turn speed to 3
		;		
         
		// Robot main loop		
	while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
			scan();
		}
		
	}
public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
		
		scan();
}
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(2);
		
		if (peek) {
		
			scan();
			fire(1);		
		}
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like		
		
		scan();
			
		back(10);
			
	}
	public void smartFire(double robotDistance) {
			
		scan();
	}
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like

		// Call scan again, before we turn the gun
		scan();
	}
	public void onDeath(DeathEvent e) {
		// Well, others should never be 0, but better safe than sorry.
		if (others == 0) {
			return;
		}

		// If 75% of the robots are still alive when we die, we'll switch corners.
		if ((others - getOthers()) / (double) others < .75) {
			corner += 90;
			if (corner == 270) {
				corner = -90;
				fire(1);
			}
			out.println("Hello there \n Yall got lucky \n I am gonna distroy that rolling trolly " + corner);
		} else {
			out.println("I am the best Sniper \n So I will still use corner \n thank you very Much" + corner);
			fire(1);
		}
	}	
}