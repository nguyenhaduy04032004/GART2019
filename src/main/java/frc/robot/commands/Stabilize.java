/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivebase;
import frc.robot.Constants.Gyro;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Stabilize extends PIDCommand {
  /**
   * Creates a new Stabilize.
   */
  public Stabilize(double distance, Drivebase drive) {
    super(
        // The controller that the command will use
        new PIDController(Gyro.kStableP, Gyro.kStableI, Gyro.kStableD), 
          drive::getTurnRate, 
          0, 
          output -> drive.arcadeDrive(0, output), 
          drive);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
