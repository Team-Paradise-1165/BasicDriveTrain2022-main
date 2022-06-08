// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;  

import javax.lang.model.util.ElementScanner6;

//import java.io.Console;

//Talon imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.cameraserver.CameraServer; 

//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; 
//import edu.wpi.first.wpilibj2.command.Command; 
//import edu.wpi.first.wpilibj2.command.CommandBase; 

import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */


  //Making the objects for the motor

  //right motor
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(6);

  //left motor
  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(3);

  //shooter motor
  //private final WPI_TalonSRX shooterMotor = new WPI_TalonSRX(2);

  //winch motors
  //private final WPI_TalonSRX bigWinchMotor = new WPI_TalonSRX(5);
  //private final WPI_TalonSRX smallWinchMotor = new WPI_TalonSRX(2);
  private final WPI_TalonFX bigWinchMotor = new WPI_TalonFX(2);
  private final WPI_TalonFX smallWinchMotor = new WPI_TalonFX(5);
  //hook motor
  private final WPI_TalonSRX NSHook = new WPI_TalonSRX(7);
  private final WPI_TalonSRX AngleHook = new WPI_TalonSRX(8);

  //Making the groups to put in the drive train
  private final MotorControllerGroup rightMotorGroup = new MotorControllerGroup(rightMotor1, rightMotor2); 
  private final MotorControllerGroup leftMotorGroup = new MotorControllerGroup(leftMotor1, leftMotor2);

  //Drive Train
  DifferentialDrive driveTrain = new DifferentialDrive(leftMotorGroup, rightMotorGroup);  

  //created a joystick object
  Joystick stick = new Joystick(0);
  Joystick stick2 = new Joystick(1); 

  public double startTime; 
  public double stt;
  

  @Override
  public void robotInit() {
    //inverting the motors cuz we were forced to
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);

    //turning off the safeties due to complications caused by it
    rightMotor1.setSafetyEnabled(false);
    rightMotor2.setSafetyEnabled(false);
    leftMotor1.setSafetyEnabled(false);
    leftMotor2.setSafetyEnabled(false);

    //makes the cameras appear in the SmartDashboard, String and Int parameters rquired 
    CameraServer.startAutomaticCapture("USB Camera 0", 0);
    CameraServer.startAutomaticCapture("Cool Kid", 1);
    CameraServer.startAutomaticCapture("Number ", 2);
  }



  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
    //stt = Timer.getFPGATimestamp(); 
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    Auto oto = new Auto(startTime, time, 2);

    oto.goForward(leftMotor1, leftMotor2, rightMotor1, rightMotor2);

/*
    if (time - startTime < 3) {
      leftMotor1.set(0.6);
      leftMotor2.set(0.6);
      rightMotor1.set(-0.6);
      rightMotor2.set(-0.6); 
    } else {
      leftMotor1.set(0);
      leftMotor2.set(0);
      rightMotor1.set(0);
      rightMotor2.set(0); 
      if ((time - stt > 3) && (time - stt < 3.5)) {
        leftMotor1.set(0.6);
        leftMotor2.set(0.6); 
        rightMotor1.set(0);
        rightMotor2.set(0);
      } else {
        leftMotor1.set(0);
        leftMotor2.set(0);
        rightMotor1.set(0);
        rightMotor2.set(0); 
      }
    }
*/
/*
    if ((time - stt > 3) && (time - stt < 3.5)) {
      leftMotor1.set(0.6);
      leftMotor2.set(0.6);
      rightMotor1.set(0);
      rightMotor2.set(0);
    } else {
      leftMotor1.set(0);
      leftMotor2.set(0);
      rightMotor1.set(0);
      rightMotor2.set(0); 
    }
    */
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //change to x, y if it doesn't work
    //set slider to 0 if it is something like 0.3
    //do we want to change arcade drive to curvature drive? might make it easier to control - felix
    

   ;
    //if (stick.getRawButtonPressed(1) == true) {
   //   driveTrain.arcadeDrive(-stick.getZ(), stick.getY());
  //  }
   // else{
   //   driveTrain.arcadeDrive(-stick.getZ(), stick.getY());
   // }


    driveTrain.arcadeDrive(-stick.getZ()*(-stick.getThrottle()+1), stick.getY()*(-stick.getThrottle()+1));
    /*
    if (stick2.getRawButtonPressed(1) == true) {
      shooterMotor.set(0.8); 
    } else if(stick2.getRawButtonPressed(2) == true) {
      shooterMotor.set(0.0); 
    }
    */

    
    if (stick2.getRawButtonPressed(1) == true) {
      bigWinchMotor.set(0.2);
      smallWinchMotor.set(0.2); 
    }
    if(stick2.getRawButtonReleased(1)== true){
      bigWinchMotor.set(0.0);
      smallWinchMotor.set(0.0);
      //_talon.set(ControlMode.PercentOutput, .8)
    }  
    if (stick2.getRawButtonPressed(3) == true) {
      bigWinchMotor.set(-0.1);
      smallWinchMotor.set(-0.1); 
    }
    if(stick2.getRawButtonReleased(3)== true){
    bigWinchMotor.set(0.0);
    smallWinchMotor.set(0.0);
    }
   // boolean hasClimbed = false;
    if (stick2.getRawButtonPressed(4) == true) {
      bigWinchMotor.set(-0.4);
      smallWinchMotor.set(-0.4); 
     // hasClimbed = true;
    }
    if (stick2.getRawButtonReleased(4) == true){
    //  if(hasClimbed){
        bigWinchMotor.set(-0.1);
        smallWinchMotor.set(-0.1);
     // }
    }
    if (stick2.getRawButtonPressed(5) == true){
      bigWinchMotor.set(-0.1);
      smallWinchMotor.set(-0.1);
    }
  
    //ns hooks
    if (stick2.getRawButtonPressed(11) == true) {
      NSHook.set(0.6);
    } 
    if (stick2.getRawButtonReleased(11) == true){
      NSHook.set(0.0);
    }
    if (stick2.getRawButtonPressed(10) == true) {
      NSHook.set(-0.6);
    } 
    if (stick2.getRawButtonReleased(10) ==true) {
      NSHook.set(0.0);
    }
    //angle hook
    if (stick2.getRawButtonPressed(6) == true) {
      AngleHook.set(0.5);
    } 
    if (stick2.getRawButtonReleased(6) == true){
      AngleHook.set(0.0);
    }
    if (stick2.getRawButtonPressed(7) == true) {
      AngleHook.set(-0.5);
    } 
    if (stick2.getRawButtonReleased(7) ==true) {
      AngleHook.set(0.0);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
