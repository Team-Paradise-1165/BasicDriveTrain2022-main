// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;  

//import javax.lang.model.util.ElementScanner6;
//import java.io.Console;
//Talon imports

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// import edu.wpi.first.cameraserver.CameraServer; 
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; 
//import edu.wpi.first.wpilibj2.command.Command; 
//import edu.wpi.first.wpilibj2.command.CommandBase; 

import edu.wpi.first.wpilibj.Timer;

//No F500 Motors are currently being used. Un-Comment the (3) below when/if they are added
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Robot extends TimedRobot {

//Defining Can IDS
  //Right Drive Motors
  private final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(6);

  //Left Drive Motors
  private final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(3);

//PCM Solenoids IDS
  private final Solenoid leftArmUp = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  private final Solenoid leftArmExhaust = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

  private final Solenoid rightArmUp = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
  private final Solenoid rightArmExhaust = new Solenoid(PneumaticsModuleType.CTREPCM, 3);

//Grouping motors into sides for the differential drive
  private final MotorControllerGroup rightMotorGroup = new MotorControllerGroup(rightMotor1, rightMotor2); 
  private final MotorControllerGroup leftMotorGroup = new MotorControllerGroup(leftMotor1, leftMotor2);

//Creating DriveType
  DifferentialDrive driveTrain = new DifferentialDrive(leftMotorGroup, rightMotorGroup);  

//Collecting inputs from FRC-DriveStation
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

  // makes the cameras appear in the SmartDashboard, String and Int parameters rquired
  // Commented to prevent console spam.  Uncomment when 3 cameras are setup on final robot. Also Uncomment Line(19)
    // CameraServer.startAutomaticCapture("USB Camera 0", 0);
    // CameraServer.startAutomaticCapture("Cool Kid", 1);
    // CameraServer.startAutomaticCapture("Number ", 2);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    Auto oto = new Auto(startTime, time, 2);
    oto.goForward(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    driveTrain.arcadeDrive(-stick.getZ()*(-stick.getThrottle()+1), stick.getY()*(-stick.getThrottle()+1));

    if (stick2.getRawButtonPressed(3) == true) {
      leftArmUp.set(true);
      rightArmUp.set(true);
      leftArmExhaust.set(true);
      rightArmExhaust.set(true);
    }
    if(stick2.getRawButtonReleased(3) == true) {
      leftArmUp.set(false);
      rightArmUp.set(false);
      leftArmExhaust.set(false);
      rightArmExhaust.set(false);
    }
    if (stick2.getRawButtonPressed(2) == true) {
      rightArmExhaust.set(true);
      leftArmExhaust.set(true);
    }
    if(stick2.getRawButtonReleased(2) == true) {
      leftArmExhaust.set(false);
      rightArmExhaust.set(false);
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
