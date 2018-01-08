package frc.team1091.robot.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team1091.robot.RobotComponents;
import frc.team1091.robot.RobotControlSystems;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ManualDriveTest {

    @Test
    public void testDriveForwards() {

        Joystick joystick = mock(Joystick.class);
        DifferentialDrive drive = mock(DifferentialDrive.class);

        RobotComponents rc = new RobotComponents(joystick, null, null, null, null);
        RobotControlSystems sy = new RobotControlSystems(drive);

        when(joystick.getRawAxis(1)).thenReturn(0.5);

        ManualDriveSystem manualDriveSystem = new ManualDriveSystem(rc, sy);

        manualDriveSystem.drive();

        verify(joystick).getRawAxis(1);
        verify(drive).arcadeDrive(0.5, 0);
    }
}