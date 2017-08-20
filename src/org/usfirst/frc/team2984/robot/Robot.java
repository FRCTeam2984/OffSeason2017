
package org.usfirst.frc.team2984.robot;

import org.usfirst.frc.team2984.robot.commands.GetToStartingPosition;
import org.usfirst.frc.team2984.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2984.robot.subsystems.Gyroscope;
import org.usfirst.frc.team2984.robot.util.VisionTracker;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public Robot(){
		DriveTrain.getInstance();
		Gyroscope.getInstance();
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		VisionTracker.init();
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = new GetToStartingPosition(3);
//		this.autonomousCommand = new DriveAndPivotTest();
//		this.autonomousCommand = new Pivot(180, true, 1);
		
//		double[][] wayPoints = new double[][]{
//			{10D/12, 0},
//			{10D/12, 50D/12},
//			{0, 67D/12}
//		};
//		FalconPathPlanner path = new FalconPathPlanner(wayPoints);
////		path.setPathAlpha(0.3);
////		path.setPathBeta(0.3);
//		path.calculate(3.5, 20/1000D, RobotMap.DISTANCE_BETWEEN_WHEELS/12);
//		FalconLinePlot fig1 = new FalconLinePlot(path.nodeOnlyPath, Color.blue, Color.green);
//		fig1.yGridOn();
//		fig1.xGridOn();
//		fig1.setYLabel("Y (in)");
//		fig1.setXLabel("X (in)");
//		fig1.setTitle("Top Down View of FRC Field (24ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");
//
//		fig1.setYTic(0, 6, 1);
//		fig1.setXTic(0, 1, 1/12D);
//		fig1.addData(path.smoothPath, Color.red, Color.blue);
//		this.autonomousCommand = new FollowPath(path);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
