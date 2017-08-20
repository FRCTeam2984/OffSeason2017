package org.usfirst.frc.team2984.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetToStartingPosition extends CommandGroup {

    public GetToStartingPosition(int pegPos) {
    	if(pegPos == 1){
        	addSequential(new MoveLinearly(50, 1));
        	addSequential(new TurnToAngle(60, 1));
        	Pivot turn = new Pivot(60, true, 1);
        	addSequential(new DriveToPeg(60, turn));
        	addSequential(turn);
    	} else if(pegPos == 3){
        	addSequential(new MoveLinearly(50, 1));
        	addSequential(new TurnToAngle(-60, 1));
        	Pivot turn = new Pivot(-60, true, 1);
        	addSequential(new DriveToPeg(-60, turn));
        	addSequential(turn);
    	} else if(pegPos == 2){
        	addSequential(new MoveLinearly(20, 1));
        	Pivot turn = new Pivot(0, true, 1);
        	addSequential(new DriveToPeg(0, turn));
        	addSequential(turn);
    	}
    	addSequential(new Done());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

    }
}