package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.util.Motion;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private double angle;
	private double epsilon;
	
	/**
	 * A command to turn to the given angle
	 * @param angle the angle to turn to
	 */
    public TurnToAngle(double angle, double epsilon) {
    	requires(DriveTrain.getInstance());
    	this.angle = angle;
    	this.epsilon = epsilon;
    }

    protected void initialize() {
    }

    protected void execute() {
    	DriveTrain.getInstance().turnToAngle(this.angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DriveTrain.getInstance().isAtAngle(this.angle, this.epsilon);
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
