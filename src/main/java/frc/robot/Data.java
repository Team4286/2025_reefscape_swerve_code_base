package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;

public class Data {
    
    private static final XboxController _controller = new XboxController(OIConstants.kDriverControllerPort);

    private static final ShuffleboardTab _tab = Shuffleboard.getTab("Imperial Robotics: 4286 Robot");

    private static ShuffleboardLayout controllerInfo;
    private static GenericEntry leftJoystickX;
    private static GenericEntry leftJoystickY;
    private static GenericEntry rightJoystckX;
    private static GenericEntry rightJoystckY;

    private static GenericEntry frontLeftWheel;
    private static GenericEntry frontRightWheel;
    private static GenericEntry rearLeftWheel;
    private static GenericEntry rearRightWheel;
    private static GenericEntry gyro;

    //private static ShuffleboardLayout drivetrainOption;
    //public static int drive = 0;

    private Data() {}

    public static void Initialize(){
        Shuffleboard.selectTab("Imperial Robotics: 4286 Robot");
        
        //Controller Info
        controllerInfo = _tab.getLayout("Controller Data", BuiltInLayouts.kList)
                .withPosition(7, 1)
                .withSize(1, 3);
        
        leftJoystickX = controllerInfo.add("Left Joystick X", 0.0).getEntry();
        leftJoystickY = controllerInfo.add("Left Joystick Y", 0.0).getEntry();
        rightJoystckX = controllerInfo.add("Right Joystick X", 0.0).getEntry();
        rightJoystckY = controllerInfo.add("Right Joystick Y", 0.0).getEntry();
        
        //Wheel Info
        frontLeftWheel = _tab.add("Front Left Wheel", 0.0).withPosition(9, 1).withWidget(BuiltInWidgets.kGyro).getEntry();
        frontRightWheel = _tab.add("Front Right Wheel", 0.0).withPosition(11, 1).withWidget(BuiltInWidgets.kGyro).getEntry();
        rearLeftWheel = _tab.add("Rear Left Wheel", 0.0).withPosition(9, 3).withWidget(BuiltInWidgets.kGyro).getEntry();
        rearRightWheel = _tab.add("Rear Right Wheel", 0.0).withPosition(11, 3).withWidget(BuiltInWidgets.kGyro).getEntry();
        gyro = _tab.add("Gyro", 0.0).withPosition(10, 5).withWidget(BuiltInWidgets.kGyro).getEntry();
    }

    public static void Update(){
        //Controller Info
        leftJoystickX.setDouble(MathUtil.applyDeadband(_controller.getLeftX(), OIConstants.kDriveDeadband));
        leftJoystickY.setDouble(MathUtil.applyDeadband(_controller.getLeftY(), OIConstants.kDriveDeadband));
        rightJoystckX.setDouble(MathUtil.applyDeadband(_controller.getRightX(), OIConstants.kDriveDeadband));
        rightJoystckY.setDouble(MathUtil.applyDeadband(_controller.getRightY(), OIConstants.kDriveDeadband));

        //Wheel Info
        frontLeftWheel.setDouble(-DriveSubsystem.states[0].angle.getDegrees());
        frontRightWheel.setDouble(-DriveSubsystem.states[1].angle.getDegrees());
        rearLeftWheel.setDouble(-DriveSubsystem.states[2].angle.getDegrees());
        rearRightWheel.setDouble(-DriveSubsystem.states[3].angle.getDegrees());
        gyro.setDouble(0);      //Use the gyro val
    }
}
