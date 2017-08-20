package org.usfirst.frc.team2984.robot.subsystems;

import org.opencv.core.Point;
import org.usfirst.frc.team2984.robot.RobotMap;
import org.usfirst.frc.team2984.robot.util.MathUtil;
import org.usfirst.frc.team2984.robot.util.Motion;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	
	private CANTalon frontLeft;
	private CANTalon frontRight;
	private CANTalon backLeft;
	private CANTalon backRight;
	
	private Gyroscope gyro;
	
	private double powerAccumLeft;
	private double powerAccumRight;
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			CANTalon frontLeft = new CANTalon(RobotMap.FRONT_LEFT_MOTOR_ID);
			CANTalon frontRight = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR_ID);
			CANTalon rearLeft = new CANTalon(RobotMap.REAR_LEFT_MOTOR_ID);
			CANTalon rearRight = new CANTalon(RobotMap.REAR_RIGHT_MOTOR_ID);
			
			instance = new DriveTrain(frontLeft, frontRight, rearLeft, rearRight, Gyroscope.getInstance());
		}
		return instance;
	}

	/**
	 * @param frontLeft
	 * @param frontRight
	 * @param backLeft
	 * @param backRight
	 */
	public DriveTrain(CANTalon frontLeft, CANTalon frontRight, CANTalon backLeft, CANTalon backRight, Gyroscope gyro) {
		super("drive-train");
		
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.backLeft = backLeft;
		this.backRight = backRight;
		
		this.gyro = gyro;
	}
	
	
	public void drive(Motion m){
		drive(m, true, true);
	}
	
	public void drive(Motion m, boolean leftEnabled, boolean rightEnabled){
		double left = m.getY() + m.getRotation();
		double right = m.getY() - m.getRotation();
		if(leftEnabled){
			this.frontLeft.set(left);
			this.backLeft.set(left);
		} else {
			this.frontLeft.set(0);
			this.backLeft.set(0);
		}
		if(rightEnabled){
			this.frontRight.set(right);
			this.backRight.set(right);
		} else {
			this.frontRight.set(0);
			this.backRight.set(0);
		}
	}
	
	public void drive(double left, double right){
		this.frontLeft.set(left);
		this.frontRight.set(right);
		this.backLeft.set(left);
		this.backRight.set(right);
	}
	
	/**
	 * resets the position of the drive train, any move to position commands will be based on this new reference.
	 */
	public void resetPosition(){
		this.backLeft.setEncPosition(0);
		this.gyro.calibrate(0);
	}

    public void initDefaultCommand() {
    }

    /**
     * moves to a given position based on a point without regard to the final angle of the robot
     * @param point a point representing the final position of the robot in x, y space, Y is forward from the robot
     */
	public void moveToPosition(Point point) {
		double distanceTravled = this.convertEncPositionToInches(this.backLeft.getEncPosition());
		double distanceToGo = Math.sqrt(point.x*point.x + point.y*point.y);
		double distanceLeft = Math.abs(distanceToGo - distanceTravled);
		double power = Math.min(distanceLeft * RobotMap.LINEAR_DRIVE_P, 1);
		Motion m = new Motion(0, power, 0);
		this.drive(m);
	}

	/**
	 * converts the encoder position to inches traveled
	 * @param encPosition the position in encoder ticks
	 * @return the distance traveled in inches
	 */
	private double convertEncPositionToInches(int encPosition) {
		return encPosition*RobotMap.INCHES_PER_TICK_DRIVEN;
	}

	/**
	 * returns whether or not the robot is at the position given, it does not take regard for velocity
	 * @param finalLocation the location to test for
	 * @param epsilon the maximum variation from the final point in inches that is allowed
	 * @return whether or not the drive train has reached the position
	 */
	public boolean isAtPosition(Point finalLocation, double epsilon) {
		double distanceTravled = this.convertEncPositionToInches(this.backLeft.getEncPosition());
		double distanceToGo = Math.sqrt(finalLocation.x*finalLocation.x + finalLocation.y*finalLocation.y);
		double distanceLeft = Math.abs(distanceToGo - distanceTravled);
		return distanceLeft <= epsilon;
	}

	/**
	 * turns to a given angle
	 * @param angle the angle to turn towards
	 */
	public void turnToAngle(double angle) {
		double currentAngle = this.gyro.getAngle();
		double deltaAngle = MathUtil.shortestDeltaAngle(currentAngle, angle);
		double rotationPower = Math.min(Math.max(deltaAngle*RobotMap.ANGULAR_DRIVE_P, -1), 1);
		Motion m = new Motion(0,0,rotationPower);
		this.drive(m);
	}

	/**
	 * returns whether or not the robot is facing a given angle within a given epsilon. ie if the angle of the robot is less than epsilon from the given angle.
	 * @param angle the angle to check for in degrees
	 * @param epsilon the epsilon to account for
	 * @return whether the robot is within epsilon of the given angle
	 */
	public boolean isAtAngle(double angle, double epsilon) {
		double delta = Math.abs(this.gyro.getAngle() - angle);
		return  delta < epsilon;
	}

	/**
	 * drives at an angle, making sure to hold it
	 * @param targetAngle the angle to drive at
	 * @param speed the speed to go (0, 1]
	 */
	public void driveAtAngle(double targetAngle, double speed) {
		double currentAngle = this.gyro.getAngle();
		double deltaAngle = MathUtil.shortestDeltaAngle(currentAngle, targetAngle);
		double rotationPower = Math.min(Math.max(deltaAngle*RobotMap.ANGULAR_DRIVE_P, -1), 1);
		Motion m = new Motion(0,speed,rotationPower);
		this.drive(m);
	}

	/**
	 * pivots to a given angle with the given side, always forward
	 * @param angle the angle to turn towards
	 * @param left if the left side should be used
	 */
	public void pivotToAngle(double angle, boolean left) {
		double currentAngle = this.gyro.getAngle()%360;
		angle = angle %360;
		double deltaAngle = Math.abs(angle - currentAngle);
		double rotationPower = Math.min(Math.max(deltaAngle*RobotMap.ANGULAR_DRIVE_P, -1), 1);
		Motion m = new Motion(0,0,rotationPower);
		this.drive(m, left, !left);
	}
	
	/**
	 * drives the wheels at the given speeds
	 * @param left the speed in inches per second of the left wheel
	 * @param right speed in inches per second of the right wheel
	 */
	public void driveWheelSpeeds(double left, double right){
		double leftActual = this.convertEncPositionToInches(this.backLeft.getEncVelocity());
		double rightActual = leftActual - this.gyro.getRate()*RobotMap.DISTANCE_BETWEEN_WHEELS/180;
		double leftDelta = left - leftActual;
		double rightDelta = right - rightActual;
		this.powerAccumLeft += leftDelta * RobotMap.VELOCITY_P;
		this.powerAccumRight += rightDelta * RobotMap.VELOCITY_P;
		this.powerAccumLeft = Math.min(Math.max(this.powerAccumLeft, -1), 1);
		this.powerAccumRight = Math.min(Math.max(this.powerAccumRight, -1), 1);
		this.drive(this.powerAccumLeft, this.powerAccumRight);
		System.out.println("Driveing at (" + this.powerAccumLeft + ", " + this.powerAccumRight + ") based on wanted (" + left + ", " + right + ") with speeds (" + leftActual + ", " + rightActual + ")");
	}
	
	public void resetAccums(){
		this.powerAccumLeft = 0;
		this.powerAccumRight = 0;
	}
}

