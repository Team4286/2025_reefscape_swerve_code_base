// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second

    // Chassis configuration

    // original numbers where 26.5 inchs.
    // our chassis configuration is a bit smaller than revs.

    public static final double kTrackWidth = Units.inchesToMeters(23.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(23.5);
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset =  2.36; //-Math.PI / 2 or 0.79
    public static final double kFrontRightChassisAngularOffset = 0; // 0
    public static final double kBackLeftChassisAngularOffset = 1.57;  //  Math.PI 1.57
    public static final double kBackRightChassisAngularOffset = 0.79; // Math.PI /2 or 2.36

    // SPARK MAX CAN IDs
    public static final int kFrontLeftDrivingCanId = 15; // added
    public static final int kRearLeftDrivingCanId = 12; // added
    public static final int kFrontRightDrivingCanId = 14; //added
    public static final int kRearRightDrivingCanId = 13;//added

    public static final int kFrontLeftTurningCanId = 11;//added
    public static final int kRearLeftTurningCanId = 8;//added
    public static final int kFrontRightTurningCanId = 10;//added
    public static final int kRearRightTurningCanId = 9;//added

    public static final boolean kGyroReversed = false;
  }

  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T,
    // 13T, or 14T. This changes the drive speed of the module (a pinion gear with
    // more teeth will result in a robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 14;

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
    // teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;
  }

  /* Download 2025 path planner: these are the specifications for the digital robot information.
      * In path planner we have adjusted numbers that were not originally in the constants file.
      * 
      * All units in path planner are in the metric system, while our program is mainly written in imperial. 
      * Numbers below have been converted or guessed.
      * 
      * Wheel radius = 0.038
      * Drive Gearing = 4.71
      * True Max = 4.8
      * Bumper Width/length = 0.854
      * 
      * In accurate or we could not determine the number
      * 
      * ROBOT MOI = Wheel COF = 1.1
      * Wheel COF = 1.1
      * 
      * Off set information: 
      * 
      * from one motor to the other it is : 23.5
      * cut it in half: 11.75
      * 
      * Our temporary num(converted into meters): 0.298
   * 
   * We are using an arm this year, so we made a temporary rectangle to represent its location
      * For the arm this year, the elevator is 12 inchs from the edge:
      * converted to meters: 0.3048 (round to 0.305)
      * Rectangle from center: (0.854/2)-0.305
      * rectangle from: 0.122
      *
      * In addition to this, the rectangle is 0.854 in width
   */

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final double kDriveDeadband = 0.1; // was originally 0.05
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class NeoMotorConstants {
    public static final double kFreeSpeedRpm = 5676;
  }
}