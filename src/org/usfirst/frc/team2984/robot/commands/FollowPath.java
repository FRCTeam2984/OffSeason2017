package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.util.Motion;

import edu.wpi.first.wpilibj.command.Command;
import usfirst.frc.team2168.robot.FalconPathPlanner;

/**
 *
 */
public class FollowPath extends Command {

	private double[][] leftVels;
	private double[][] rightVels;
	
	private int position = 0;
	
    public FollowPath(FalconPathPlanner path) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    	this.leftVels = path.smoothLeftVelocity;
    	this.rightVels = path.smoothRightVelocity;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getInstance().resetAccums();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.getInstance().driveWheelSpeeds(this.rightVels[this.position][1]*12, this.leftVels[this.position][1]*12);
    	this.position ++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return position >= this.leftVels.length;
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
