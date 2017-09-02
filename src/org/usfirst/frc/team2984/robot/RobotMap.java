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
	public static final int FRONT_LEFT_MOTOR_ID = 12;
	public static final int FRONT_RIGHT_MOTOR_ID = 10;
	public static final int REAR_LEFT_MOTOR_ID = 23;
	public static final int REAR_RIGHT_MOTOR_ID = 22;
	public static final int WINCH_LEFT_MOTOR_ID = 11;
	public static final int WINCH_RIGHT_MOTOR_ID = 1; 
	
	public static final Dimension CAMERA_RESOLUTION = new Dimension(320, 240);
	public static final Dimension CAMERA_FOV = new Dimension(56,41.625);
	public static final double CAMERA_ANGLE = 10;
	public static final CameraSpecification CAMERA_SPECIFICATION = new CameraSpecification(CAMERA_FOV, CAMERA_RESOLUTION, CAMERA_ANGLE);
	public static final double CAMERA_OFFSET = 2.5;
	public static final Dimension TARGET_DIMENSION = new Dimension(10.25, 5);
	
	public static final double VALUE_LOW = 174;
	public static final double VALUE_HIGH = 205;
	public static final double HUE_LOW = 70;
	public static final double HUE_HIGH = 105;
	public static final double SATURATION_LOW = 50;
	public static final double SATURATION_HIGH = 107;
	public static final Dimension CAMERA_ACTIVE_RANGE = new Dimension(320, 30);
	public static final int CAMERA_ACTIVE_RANGE_VERTICAL_OFFSET = 0;

	public static final double ROBOT_START_ANGLE = 0;
	
	public static final double INCHES_PER_TICK_DRIVEN = 0.016666667;
	
	public static double LINEAR_DRIVE_P = 0.1;
	public static double ANGULAR_DRIVE_P = 0.001;
	public static final double PEG_APPROACH_DISTANCE_THRESHOLD = 8;
	public static final double PEG_DISTANCE_DISTANCE_P = 0.4;
	public static final double PEG_MAX_DOCKING_DISTANCE = 15;
	public static final double ANGLE_PREDICTION_P = 0.3;
	
	public static final double DISTANCE_BETWEEN_WHEELS = 20;
	public static double VELOCITY_P = 0.004;
	
	public static final int FORWARD_CONTROLLER_AXIS = 1;
	public static final int ROTATION_CONTROLLER_AXIS = 3;

}