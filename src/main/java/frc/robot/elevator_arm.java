package frc.robot;
//  some possible needed imports; spark is a must. 

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;


/*
 * 2025 elevator arm code 
 * 
 * This file is for the raising the arm 
 * 
 * 
 */
public class elevator_arm {

    /*
     * constructor needs to assign the can id and complete the motor.
     * 
     * We will need a method to take a button input and go up
     * We need a method that uses a button to go down
     * 
     * 
     * 
     * If possible create a method that slowly resets the robot to encoder zero, this way if we use autonomous we
     * are able to set the robot down to its base posisiton before we move the elevator.
     */


    // creates motor
    private final SparkMax elevatorSpark;

    //private final SparkMax vador = new SparkMax(5, MotorType.kBrushless);

    
    // create absolute encoder ( finds what tooth we are on from zero.)
    private final AbsoluteEncoder positionEncoder;


    public elevator_arm(int sparkID){
    
        // creates motor can id and type
        // object information is SparkMax(int canId, motortype motortype)
        elevatorSpark = new SparkMax(sparkID, MotorType.kBrushless);
        // returns the absoulute encoder
        positionEncoder = elevatorSpark.getAbsoluteEncoder();
       
    }


    /*
     * Method to push motor clockwise.
     * Any speed used must be between 0.0 and 1.0
     */
    public void elevator_move(double setspeed){
        elevatorSpark.set(setspeed);

    }
    

    /*
     * If the motor does not stop on its own, this command will stop it.
     */
    public void stopElevator(){

        elevatorSpark.set(0);
    }

    


    /*
     * If time allows and can be figured out, make an additional method called reset to zero.
     */

     



}
