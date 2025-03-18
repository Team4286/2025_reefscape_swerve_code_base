package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;

public class Drive {

    private static final XboxController _controller = new XboxController(OIConstants.kDriverControllerPort);

    private static boolean leftXMove = false;
    private static boolean leftYMove = false;
    private static boolean rightXMove = false;

    private Drive() {}

    public static double getLeftX(){
        double _input = -MathUtil.applyDeadband(_controller.getLeftY(), OIConstants.kDriveDeadband);

        Drive.leftXMove = _input != 0.0;
        
        return _input;
    }
    public static double getLeftY(){
        double _input =  -MathUtil.applyDeadband(_controller.getLeftX(), OIConstants.kDriveDeadband);

        Drive.leftYMove = _input != 0.0;

        return _input;
    }

    public static double getRightX(){
        double _input = -MathUtil.applyDeadband(_controller.getRightX(), OIConstants.kDriveDeadband);

        Drive.rightXMove = _input != 0.0;

        return _input;
    }

    public static SwerveModuleState[] getSwerve(double leftX, double leftY, double rightX){
        double xSpeedDelivered = leftX * DriveConstants.kMaxSpeedMetersPerSecond;
        double ySpeedDelivered = leftY * DriveConstants.kMaxSpeedMetersPerSecond;

        double rotDelivered = rightX * DriveConstants.kMaxAngularSpeed;

        var swerveModuleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(
        new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));
        
        SwerveDriveKinematics.desaturateWheelSpeeds(
        swerveModuleStates, DriveConstants.kMaxSpeedMetersPerSecond);

        swerveModuleStates[0].angle = Rotation2d.fromRadians(swerveModuleStates[0].angle.getRadians() / 2);
        swerveModuleStates[1].angle = Rotation2d.fromRadians(swerveModuleStates[1].angle.getRadians() / 2);
        swerveModuleStates[2].angle = Rotation2d.fromRadians(swerveModuleStates[2].angle.getRadians() / 2);
        swerveModuleStates[3].angle = Rotation2d.fromRadians(swerveModuleStates[3].angle.getRadians() / 2);

        return swerveModuleStates;
    }
}