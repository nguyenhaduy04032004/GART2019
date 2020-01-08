/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Stabilize;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The example autonomous command.
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  // The robot's subsystems
  public final Drivebase m_drivebase = new Drivebase();

  // The driver's controllers
  private final Joystick m_stick = new Joystick(Ports.kStick);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Set the default drive command to split-stick arcade drive
    m_drivebase.setDefaultCommand(
      // A split-stick arcade command, with forward/backward controlled by the left
      // hand, and turning controlled by the right.
      new RunCommand(() -> m_drivebase
        .arcadeDrive(-m_stick.getRawAxis(Stick.kLYaxis),
                    m_stick.getRawAxis(Stick.kRXaxis)), m_drivebase));
    
    SmartDashboard.putNumber("target angle", 0);
    SmartDashboard.putNumber("P", 0);
    SmartDashboard.putNumber("I", 0);
    SmartDashboard.putNumber("D", 0);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Drive slower when this button is held
    new JoystickButton(m_stick, Stick.kLB)
      .whenPressed(() -> m_drivebase.setMaxOutput(0.3))
      .whenReleased(() -> m_drivebase.setMaxOutput(0.4));
    
    // Turn to 90 degrees when the 'B' button is pressed, with a 5 seconds timeout
    new JoystickButton(m_stick, Stick.kB)
      .whenPressed(new TurnToAngle(SmartDashboard.getNumber("target angle", 0), m_drivebase).withTimeout(5));

    // Turn to -90 degrees when the 'X' button is pressed, with a 5 seconds timeout
    // new JoystickButton(m_stick, Stick.kX)
    //   .whenPressed(new TurnToAngle(-90, m_drivebase).withTimeout(5));

    // Stabilize robot to drive straight with gyro when Right Button is held
    new JoystickButton(m_stick, Stick.kRB)
      .whileHeld(new Stabilize(10000, m_drivebase));
    new JoystickButton(m_stick, Stick.kA)
    .whenPressed(() -> m_drivebase.reset());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
