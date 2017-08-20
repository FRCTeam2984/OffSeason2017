package org.usfirst.frc.team2984.robot.util;

public class PegAproachCalculator {
	
	public static double getDesiredAngle(double distanceToPeg, double angleToPerpendicular, double dockingThreshold){
		if(angleToPerpendicular == 90 || distanceToPeg == 0){
			return 0;
		}
		double dpy = Math.sin(Math.toRadians(angleToPerpendicular)) * distanceToPeg;
		double dpx = Math.cos(Math.toRadians(angleToPerpendicular)) * distanceToPeg;
		double desiredDistance = dpy - dockingThreshold;
		return Math.toDegrees(Math.atan(desiredDistance/dpx));
	}
	
	public static double getHeadingAngle(double distanceToPeg, double robotAngle, double pegAngle, double dockingThreshold){
		double angleToPerpendicular = 90 - Math.abs(robotAngle - pegAngle);
		boolean left = robotAngle > pegAngle;
		double desiredLocalAngle = getDesiredAngle(distanceToPeg, angleToPerpendicular, dockingThreshold);
		if(left){
			return 90-desiredLocalAngle + pegAngle;
		} else{
			return -(90-desiredLocalAngle) + pegAngle;
		}
	}
	
}
