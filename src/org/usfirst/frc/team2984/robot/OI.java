package org.usfirst.frc.team2984.robot;

import org.usfirst.frc.team2984.robot.commands.DriveToPeg;
import org.usfirst.frc.team2984.robot.commands.MoveLinearly;
import org.usfirst.frc.team2984.robot.commands.TurnToAngle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	public Joystick stick;

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public OI(){
		this.stick = new Joystick(0);
		new JoystickButton(this.stick, 1).cancelWhenPressed(new MoveLinearly(24, 0.5));
		new JoystickButton(this.stick, 2).cancelWhenPressed(new MoveLinearly(0, 0.5));
		new JoystickButton(this.stick, 3).cancelWhenPressed(new TurnToAngle(90, 0.5));
		new JoystickButton(this.stick, 4).cancelWhenPressed(new TurnToAngle(-90, 0.5));
		new JoystickButton(this.stick, 3).cancelWhenPressed(new DriveToPeg(0));

	}
}
