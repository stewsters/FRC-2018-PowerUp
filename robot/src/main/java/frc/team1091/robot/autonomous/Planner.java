package frc.team1091.robot.autonomous;

import com.team1091.math.Vec3;
import com.team1091.planning.EndingPos;
import com.team1091.planning.FieldMeasurement;
import com.team1091.planning.StartingPos;
import edu.wpi.first.wpilibj.DriverStation;
import frc.team1091.robot.RobotComponents;
import frc.team1091.robot.autonomous.commands.*;
import frc.team1091.robot.systems.DriveSystem;
import frc.team1091.robot.systems.ElevatorSystem;
import frc.team1091.robot.systems.PlatformSystem;
import frc.team1091.robot.systems.VisionSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.team1091.planning.PathMakerKt.makePath;

public class Planner {

    // initialize the autonomous with a list of things to do.
    public static Command plan(StartingPos start,
                               DriverStation.Alliance alliance,
                               String gameGoalData,
                               RobotComponents components,
                               DriveSystem driveSystem,
                               VisionSystem visionSystem,
                               PlatformSystem platformSystem,
                               ElevatorSystem elevatorSystem) {

        EndingPos close = gameGoalData.charAt(0) == 'R' ? EndingPos.RIGHT_SWITCH : EndingPos.LEFT_SWITCH;
        EndingPos far = gameGoalData.charAt(1) == 'R' ? EndingPos.RIGHT_SCALE : EndingPos.LEFT_SCALE;

        // TODO: select a far or close goal
        EndingPos end = far;

        List<Vec3> path = makePath(start, end, Arrays.asList(
                // another robot's plan takes this zone.  It would be nice to
//                new Rectangle(Vec2.Companion.get(15, 0), Vec2.Companion.get(15, 10))
        ));

        ArrayList<Command> commandList = getCommandList(components, driveSystem, path);

        commandList.add(new Wait(100));

        // drive up to the target
        commandList.add(new DriveUntilClose(alliance, components, driveSystem, visionSystem));

        // unload box
        commandList.add(new BarfBox(components, platformSystem, elevatorSystem));

//        return new DriveForwards(36.0, components, driveSystem);
//        return new Turn(960.0, components, driveSystem);
        return new CommandList(commandList);
    }

    private static ArrayList<Command> getCommandList(RobotComponents components, DriveSystem driveSystem, List<Vec3> path) {
        // Convert list of points into driving instructions - need to parse out turns.
        ArrayList<Command> commandList = new ArrayList<>();

        // At this point we have a list of positions we want to be at, we need to translate that into a list of commands to get the robot there
        // we can start at 1, since we are are already in our current position.

        double turn = 0;
        double forward = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            Vec3 lastNode = path.get(i);
            Vec3 nextNode = path.get(i + 1);
//            Command command = commandList.get(commandList.size()-1);

            if (lastNode.getZ() != nextNode.getZ()) { // we turned

                if (forward != 0) {
                    // we changed from turn to go forward, save forward
                    commandList.add(new DriveForwards(forward, components, driveSystem));
                    forward = 0;
                }
                turn += 90.0 * ((lastNode.getZ() > nextNode.getZ()) ? -1 : 1);

            } else {//we are going forward/backward

                if (turn != 0) {
                    // we changed from forward to turn, save it
                    commandList.add(new Turn(turn, components, driveSystem));
                    turn = 0;
                }
                forward += FieldMeasurement.Companion.getBlockSize().toInches();

            }
        }

        // If we have a remainder, create an action
        if (turn != 0) {
            commandList.add(new Turn(turn, components, driveSystem));
        }
        if (forward != 0) {
            commandList.add(new DriveForwards(forward, components, driveSystem));
        }
        return commandList;
    }

}
