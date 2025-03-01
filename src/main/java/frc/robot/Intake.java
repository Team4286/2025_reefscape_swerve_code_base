package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
//import com.revrobotics.AbsoluteEncoder;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Intake {

    // temporary class xController

    public XboxController xController;

    // spark max : sparkRotate pivots the overal intake/launch, we need two button key binds, that is a Neo brushless.
    // spark max : sparkLaunch launchs the coral, we will need one key bind that sends power, this is brushed (no encoder)
    private final SparkMax sparkRotate; 
    private final Spark intakeSpark;

    
    
    //private final SparkMax intakeSpark;

    public Intake(int canIntake, int canRotation, XboxController xController){
        
        this.xController=xController;
        // creates motor can id and type
        // object information is SparkMax(int canId, motortype motortype)
        sparkRotate = new SparkMax(canRotation,MotorType.kBrushless );
        // controls intake
        intakeSpark = new Spark(canIntake);
        // returns the absoulute encoder
    }

    /*
     * Handles rotating intake for each level
     */

    // rotates intake
    public void intake_move(double setspeed){
        sparkRotate.set(setspeed);
        //System.out.println(sparkRotate.getOutputCurrent());
    }

    // ensures rotation halts
    public void stopRotation(){
        sparkRotate.set(0);
    }

    //  rotates intake
    public void controlRotation(double stepSpeed){
        
        if(xController.getRightBumperButton()==true){
            intake_move(stepSpeed);
        }
        else if(xController.getLeftBumperButton()==true){
            intake_move((stepSpeed * -1)/2);
        }
        // emergency stop
        else{
            stopRotation();
        }
      
    }


    /*
     * Handles intake information
     */
    
    
    // intake launch/ intake

    public void launch(double setSpeed){
        intakeSpark.set(setSpeed);
    }

    // halts intake

    public void stopIntake(){
        intakeSpark.set(0);
    }

    // handles intake input

    public void controlIntake(double stepSpeed){
        if(xController.getXButton()){
            launch(stepSpeed);
        }

        // go backwards at half speed for intake
        else if(xController.getYButton()){
            launch((stepSpeed*-1)/3);
        }
        else{
            stopIntake();
        }
    }


    /*
     * 
     * SECRET METHOD, ONLY USE IN EMERGENCY
     * don't set to 1: cause that 100% full speed ahead.
     */


     public void ROCKET(){
        if(xController.getYButton()){
            launch(1);
        }
        else{
            stopIntake();
        }
     }

}
