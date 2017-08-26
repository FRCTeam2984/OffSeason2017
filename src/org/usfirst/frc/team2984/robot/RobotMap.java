package org.usfirst.frc.team2984.robot;

import org.usfirst.frc.team2984.robot.util.CameraSpecification;
import org.usfirst.frc.team2984.robot.util.Dimension;

import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int FRONT_LEFT_MOTOR_ID = 14;
	public static final int FRONT_RIGHT_MOTOR_ID = 4;
	public static final int REAR_LEFT_MOTOR_ID = 16;
	public static final int REAR_RIGHT_MOTOR_ID = 1;
	public static final int WINCH_LEFT_MOTOR_ID = 30;
	public static final int WINCH_RIGHT_MOTOR_ID = 20; 
	
	public static final Dimension CAMERA_RESOLUTION = new Dimension(320, 240);
	public static final Dimension CAMERA_FOV = new Dimension(56,41.625);
	public static final double CAMERA_ANGLE = 10;
	public static final CameraSpecification CAMERA_SPECIFICATION = new CameraSpecification(CAMERA_FOV, CAMERA_RESOLUTION, CAMERA_ANGLE);
	public static final double CAMERA_OFFSET = 2.5;
	public static final Dimension TARGET_DIMENSION = new Dimension(10.25, 5);
	
	public static final double VALUE_LOW = 75;

	public static int leftDistanceSensorPort = 3; // TODO: set value
	public static int rightDistanceSensorPort = 1; // TODO: set value
	
	
	public static final double ROBOT_START_ANGLE = 0;
	
	public static final double INCHES_PER_TICK_DRIVEN = 0.016666667;
	
	public static final double LINEAR_DRIVE_P = 0.1;
	public static final double ANGULAR_DRIVE_P = 0.1;
	public static final double PEG_APPROACH_DISTANCE_THRESHOLD = 8;
	public static final double PEG_DISTANCE_DISTANCE_P = 0.4;
	public static final double PEG_MAX_DOCKING_DISTANCE = 15;
	public static final double ANGLE_PREDICTION_P = 0.3;
	
	public static final double DISTANCE_BETWEEN_WHEELS = 20;
	public static final double VELOCITY_P = 0.004;
	
	public static final int FORWARD_CONTROLLER_AXIS = 1;
	public static final int ROTATION_CONTROLLER_AXIS = 3;

}