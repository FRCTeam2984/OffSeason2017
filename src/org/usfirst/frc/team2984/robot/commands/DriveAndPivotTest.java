package org.usfirst.frc.team2984.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndPivotTest extends CommandGroup {

    public DriveAndPivotTest() {
    	addSequential(new MoveLinearly(50, 1));
    	addSequential(new Pivot(90, true, 1));
    	addSequential(new TurnToAngle(180, 1));
    	addSequential(new Pivot(90, true, 1));
    }
}
