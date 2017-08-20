package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.util.Motion;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command implements SideSettableCommand{

	private double angle;
	private boolean left;
	private double epsilon;
	
	/**
	 * A command to pivot ie only turn one side of the robot.
	 * @param angle the angle to pivot to
	 * @param left whether the left wheels should be used
	 * @param epsilon the maximum error in the gyro reading to finish the command
	 */
    public Pivot(double angle, boolean left, double epsilon) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    	this.angle = angle;
    	this.left = left;
    	this.epsilon = epsilon;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.getInstance().pivotToAngle(this.angle, this.left);
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

	@Override
	public void setSide(boolean left) {
		this.left = left;
	}
}
