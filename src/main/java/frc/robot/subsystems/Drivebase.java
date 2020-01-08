/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;

public class Drivebase extends SubsystemBase {
  private final WPI_TalonSRX left1 = new WPI_TalonSRX(Ports.kLeft1);
  private final WPI_TalonSRX left2 = new WPI_TalonSRX(Ports.kLeft2);
  private final WPI_TalonSRX right1 = new WPI_TalonSRX(Ports.kRight1);
  private final WPI_TalonSRX right2 = new WPI_TalonSRX(Ports.kRight2);
  // The motors on the left
  private final SpeedControllerGroup m_leftMotors = 
    new SpeedControllerGroup(left1, left2);

  // The motors on the right
  private final SpeedControllerGroup m_rightMotors = 
    new SpeedControllerGroup(right1, right2);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The NavX sensor
  private final AHRS m_ahrs = new AHRS();

  /**
   * Creates a new Drivebase.
   */
  public Drivebase() {
    setNeutralMode(NeutralMode.Brake);
    setMaxOutput(0.4);
  }

  public void setNeutralMode(NeutralMode neutralMode){
    left1.setNeutralMode(neutralMode);
    left2.setNeutralMode(neutralMode);
    right1.setNeutralMode(neutralMode);
    right2.setNeutralMode(neutralMode);
  }

  /**
   * Drives the robot using arcade controls.
   * @param fwd forward speed
   * @param rot rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the yaw of the robot.
   */
  public void reset() {
    m_ahrs.reset();
  }

  /**
   * Returns the yaw of the robot.
   * @return the robot's yaw in degrees, from -180 to 180
   */
  public double getYaw() {
    return m_ahrs.getYaw();
  }

  /**
   * Returns the turn rate of the robot.
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_ahrs.getRate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}