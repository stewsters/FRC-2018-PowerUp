package frc.team1091.robot.systems;

import frc.team1091.robot.RobotComponents;

/**
 * This is for sucking a box in.
 */
public class BoxSystem {
    private RobotComponents robotComponents;
    private ElevatorSystem elevatorSystem;

    public BoxSystem(RobotComponents robotComponents, ElevatorSystem elevatorSystem) {
        this.robotComponents = robotComponents;
        this.elevatorSystem = elevatorSystem;
    }

    public void ingestBox(double dt) {
//
//        boolean elevatorIsAtBottom = elevatorSystem.isAtPosition(ElevatorPositions.GROUND_HEIGHT);
//        boolean xButton = robotComponents.xboxController.getRawButton(Xbox.x);
//        if (xButton && elevatorIsAtBottom) {
//            robotComponents.suckerMotor.set(-1);
//        } else
//            robotComponents.suckerMotor.set(0);
    }
}