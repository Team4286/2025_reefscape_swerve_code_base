package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;

public class Acceleration {
    private static final double _Loffset = 1.5;
    private static final double _Roffset = 3.8;
    private static final XboxController _controller = new XboxController(OIConstants.kDriverControllerPort);

    private Acceleration() {}

    public static double leftTrigger(double _input){
        try{
            if(_controller == null)
                return 0.0;
    
            if(_input * (1.0 + _controller.getLeftTriggerAxis() * _Loffset) >= 1.0)
                return 1.0;
            else if(_input * (1.0 + _controller.getLeftTriggerAxis() * _Loffset) <= -1.0)
                return -1.0;

            return _input *  (1.0 + _controller.getLeftTriggerAxis() * _Loffset);
        }
        catch(Exception e){
            System.out.println("Exception in left trigger of acceleration class: " + e);
        }
        return 0.0;
    }
    public static double rightTrigger(double _input){
        try{
            if(_controller == null)
                return 0.0;
        
            if(_input * (1.0 + _controller.getRightTriggerAxis() * _Roffset) >= 1.0)
                return 1.0;
            else if(_input * (1.0 + _controller.getRightTriggerAxis() * _Roffset) <= -1.0)
                return -1.0;

            return _input * (1.0 + _controller.getRightTriggerAxis() * _Roffset);
        }
        catch(Exception e){
            System.out.println("Exception in right trigger of acceleration class: " + e);
        }
        return 0.0;
    }
    public static double rightTriggerBrake(double _input){
        if(_controller == null)
            return 0.0;

        double _speed = _input - _controller.getRightTriggerAxis() * _Roffset;
    
        if(_speed < 0.0)
            _speed = 0.0;
            
        return _speed;
    }
}
