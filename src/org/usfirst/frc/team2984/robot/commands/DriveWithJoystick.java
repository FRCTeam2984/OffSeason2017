package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.Robot;
import org.usfirst.frc.team2984.robot.RobotMap;
import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.util.Motion;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(DriveTrain.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double forward = Robot.oi.stick.getRawAxis(RobotMap.FORWARD_CONTROLLER_AXIS);
    	double rotation = Robot.oi.stick.getRawAxis(RobotMap.ROTATION_CONTROLLER_AXIS);
    	Motion motion = new Motion(0, forward, rotation);
    	DriveTrain.getInstance().drive(motion);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
