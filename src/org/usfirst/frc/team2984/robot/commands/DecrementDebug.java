package org.usfirst.frc.team2984.robot.commands;

import org.usfirst.frc.team2984.robot.subsystems.DebugValueSystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DecrementDebug extends InstantCommand {

    public DecrementDebug() {
        super();
        // Use requires() here to declare subsystem dependencies
        requires(DebugValueSystem.getInstance());
    }

    // Called once when the command executes
    protected void initialize() {
    	DebugValueSystem.getInstance().decrement();
    }

}
