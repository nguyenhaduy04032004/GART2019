/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.Gyro;
import frc.robot.subsystems.Drivebase;

/**
 * A command that will turn the robot to a specified angle.
 */
public class TurnToAngle extends PIDCommand {
  /**
   * Turns to robot to the specified angle.
   * @param targetAngle The angle to turn to
   * @param drive       The drive subsystem to use
   */
  public TurnToAngle(double targetAngle, Drivebase drive) {
    super(
        new PIDController(Gyro.kTurnP, Gyro.kTurnI, Gyro.kTurnD),
        // Close the loop on yaw
        drive::getYaw,
        // Set target
        targetAngle,
        // This uses the output to turn robot
        output -> drive.arcadeDrive(0, output),
        // Require the drivebase
        drive);
    
    // đặt 2 đầu mốc để quay nhanh hơn
    getController().enableContinuousInput(-180, 180);

    // đặt ngưỡng để dừng sớm hơn
    getController().setTolerance(Gyro.kPositionTolerance, Gyro.kVelocityTolerance);
  }
  @Override
  public void initialize() {
    super.initialize();
    getController().setP(SmartDashboard.getNumber("P",0));
    getController().setI(SmartDashboard.getNumber("I",0));
    getController().setD(SmartDashboard.getNumber("D",0));
    getController().setSetpoint(SmartDashboard.getNumber("target angle",0));
  }
  @Override
  public boolean isFinished() {
    // End when the controller is at the target
    // return getController().atSetpoint();
    return getController().atSetpoint();
  }
}
