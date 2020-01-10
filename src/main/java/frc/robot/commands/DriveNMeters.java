/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivebase;

public class DriveNMeters extends CommandBase {
  /**
   * Creates a new DriveNMeters.
   */
  private Drivebase m_drivebase;
  private double __Distance = 0;
  public DriveNMeters(Drivebase subsystem, double distance_in_meters) 
  {
    m_drivebase = subsystem;
    __Distance = distance_in_meters;
    addRequirements(m_drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivebase.resetDistanceCount();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    m_drivebase.arcadeDrive(0.8, 0);
    SmartDashboard.putNumber("Distance", m_drivebase.getDistanceCount());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivebase.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return m_drivebase.getDistanceCount() >= __Distance;
  }
}
