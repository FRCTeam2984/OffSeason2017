package org.usfirst.frc.team2984.robot.commands;

import org.opencv.core.Point;
import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.util.Motion;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLinearly extends Command {
	
	private Point finalLocation;
	private double eplison;
	
	/**
	 * A command to move a fixed distance linearly, it will move straight forward.
	 * @param distance the distance in feet to move forward
	 */
    public MoveLinearly(double distance, double epsilon) {
    	requires(DriveTrain.getInstance());
    	this.finalLocation = new Point(0, distance);
    	this.eplison = epsilon;
    }

    protected void initialize() {
    	DriveTrain.getInstance().resetPosition();
    }

    protected void execute() {
    	DriveTrain.getInstance().moveToPosition(this.finalLocation);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DriveTrain.getInstance().isAtPosition(this.finalLocation, this.eplison);
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
