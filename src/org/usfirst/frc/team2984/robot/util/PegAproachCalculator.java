package org.usfirst.frc.team2984.robot.util;

import org.usfirst.frc.team2984.robot.RobotMap;

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
		double pDockingThreshold = dockingThreshold + RobotMap.PEG_DISTANCE_DISTANCE_P * (distanceToPeg - dockingThreshold);
		pDockingThreshold = Math.min(pDockingThreshold, RobotMap.PEG_MAX_DOCKING_DISTANCE);
		double desiredLocalAngle = getDesiredAngle(distanceToPeg, angleToPerpendicular, pDockingThreshold);
		double returnValue;
		if(left){
			returnValue =  90-desiredLocalAngle + pegAngle;
		} else{
			returnValue =  -(90-desiredLocalAngle) + pegAngle;
		}
		double predictionWeight = Math.min((distanceToPeg - dockingThreshold) * RobotMap.ANGLE_PREDICTION_P, 1);
		return returnValue * predictionWeight + pegAngle * (1-predictionWeight);
	}
	
}
