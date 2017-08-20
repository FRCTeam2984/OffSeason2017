package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.RobotMap;
import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.subsystems.Gyroscope;
import org.usfirst.frc.team2984.robot.util.Motion;
import org.usfirst.frc.team2984.robot.util.PegAproachCalculator;
import org.usfirst.frc.team2984.robot.util.VisionTarget;
import org.usfirst.frc.team2984.robot.util.VisionTracker;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPeg extends Command {

	private double pegAngle;
	private SideSettableCommand optional;
	
	public DriveToPeg(double pegAngle){
		this(pegAngle, null);
	}
	
    public DriveToPeg(double pegAngle, SideSettableCommand optional) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    	this.pegAngle = pegAngle;
    	this.optional = optional;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!VisionTracker.getInstance().hasTrack()){
    		return;
    	}
    	VisionTarget target = VisionTracker.getInstance().getTarget();
    	double distance = target.getDistance(RobotMap.CAMERA_SPECIFICATION, RobotMap.TARGET_DIMENSION);
    	double compositAngle = target.getRotation(RobotMap.CAMERA_SPECIFICATION) + Gyroscope.getInstance().getAngle();
    	double targetAngle = PegAproachCalculator.getHeadingAngle(distance, compositAngle, pegAngle, RobotMap.PEG_APPROACH_DISTANCE_THRESHOLD);
    	DriveTrain.getInstance().driveAtAngle(targetAngle, 0.5);
    	if(this.optional != null)
    		this.optional.setSide(compositAngle <= pegAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!VisionTracker.getInstance().hasTrack()){
    		System.out.println("Lost It");
    		return true;
    	}
    	VisionTarget target = VisionTracker.getInstance().getTarget();
        return target.getDistance(RobotMap.CAMERA_SPECIFICATION, RobotMap.TARGET_DIMENSION) < RobotMap.PEG_APPROACH_DISTANCE_THRESHOLD;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.getInstance().drive(new Motion(0,0,0));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
