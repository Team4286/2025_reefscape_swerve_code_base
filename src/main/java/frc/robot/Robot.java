// this is the main section, when the robot turns on and runs different game modes, it calls to this file.



// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private Timer time = new Timer();

  /*
   * This controller is not the same as the one in the robot container, that controller handles movement.
   * This one should be used for button configuration only.
   * If it is not working, delete this controller and we will figure out later on how to reconfigure it. 
   */
  private XboxController ControllerX = new XboxController(OIConstants.kDriverControllerPort);

  /*
   * Creates robot container
   */
  private RobotContainer m_robotContainer;

  /*
   * Creates elevator object
   */
  private elevator_arm elevator;
  private Intake intake;

  // can ID
  private int elevatorNum = 5;
  private int rotateIntake =  6;
  private int intakeWheels = 6; // not actual can id, need to set be set, 10 is a placeholder 


  //ShuffleboardTab tab = Shuffleboard.getTab("Imperial Robotics: 4286 Robot");

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //get alliance color
    Data.Initialize();
    
    //

    // select the tab we want to update to
    /* 
    Shuffleboard.selectTab("Imperial Robotics: 4286 Robot");
    
    // max robot speed
    tab.addPersistent("Max Motor Speed Per Second", 4.8);
    // viewing controller input
    tab.addPersistent("LeftY",m_robotContainer.m_driverController.getLeftY());
    tab.addPersistent("LeftX",m_robotContainer.m_driverController.getLeftX());
    tab.addPersistent("RightX",m_robotContainer.m_driverController.getRightX());
  
    tab.addPersistent("DesiredRot ",m_robotContainer.m_driverController.getRightX() * DriveConstants.kMaxAngularSpeed);
    */

    elevator = new elevator_arm(elevatorNum,ControllerX);
    intake = new Intake(intakeWheels,rotateIntake, ControllerX);               //Needs can Id  (intake is first, rotation is second)
    
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    time.start();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
    

    if(time.get() < 9){
      elevator.stopElevator();
      intake.stopRotation();
    }
    else if (time.get() < 10){
      elevator.elevator_move(0.2);
      intake.intake_move(-0.1);
    }
    else if (time.get() < 13){
      elevator.elevator_move(0.2);
      intake.stopRotation();
    }
    else{
      elevator.stopElevator();
    }
    
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    time.stop();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

   
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    
    Data.Update();
    if(ControllerX.getLeftStickButton()==true){
      DriveConstants.kMaxSpeedMetersPerSecond = 2.4;
      //DriveConstants.kMaxSpeedMetersPerSecond

    }
    else{
      //DriveConstants.kMaxSpeedMetersPerSecond = 4.8;
      DriveConstants.kMaxSpeedMetersPerSecond = Acceleration.rightTriggerBrake(DriveConstants.kMaxSpeedMetersPerSecondFINAL);
    }
    
    
  /*
   * Checks if button A or button B is pressed
   */
    elevator.controlElevation(0.60);
    

    /*
     * Checks rotation, uses bummpers
     * 
     * Right bumper is positive rotation
     * 
     * Left bumper is negative rotation
     */
    intake.controlRotation(0.2);
    /*
     * Check launch, uses button X for positive, uses y button for negative 
     * 
     */
    intake.controlIntake(0.6);

    /*Use in emergency, ONLY

     * inTake.ROCKET();
     */

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}