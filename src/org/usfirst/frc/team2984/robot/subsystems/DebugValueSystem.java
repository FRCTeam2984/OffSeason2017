package org.usfirst.frc.team2984.robot.subsystems;

import org.usfirst.frc.team2984.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebugValueSystem extends Subsystem {

	private static DebugValueSystem instance;
	
	private int index;
	private int size = 3;
	private double delta = 0.001;
	
	public static DebugValueSystem getInstance() {
		if (instance == null) {
			
			instance = new DebugValueSystem();
		}
		return instance;
	}
	
	public DebugValueSystem(){
		this.index = 0;
	}
	
	public void rotate(){
		this.index++;
		SmartDashboard.putString("Updating Value: ", getString());
	}
	
	private String getString(){
		switch(index){
		case 0:
			return "Linear Drive P";
		case 1:
			return "Angular Drive P";
		case 2:
			return "Velocity P";
		}
		return "error";
	}
	
	public void increment(){
		switch(index){
		case 0:
			RobotMap.LINEAR_DRIVE_P += delta;
			SmartDashboard.putNumber(getString(), RobotMap.LINEAR_DRIVE_P);
			break;
		case 1:
			RobotMap.ANGULAR_DRIVE_P += delta;
			SmartDashboard.putNumber(getString(), RobotMap.ANGULAR_DRIVE_P);
			break;
		case 2:
			RobotMap.VELOCITY_P += delta;
			SmartDashboard.putNumber(getString(), RobotMap.VELOCITY_P);
			break;
		}
	}
	
	public void decrement(){
		delta = -delta;
		this.increment();
		delta = -delta;
	}
	
	
	@Override
	protected void initDefaultCommand() {

	}

}
