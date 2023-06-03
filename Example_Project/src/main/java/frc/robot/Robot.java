// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

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

  @Override
  public void robotInit() {
    // This is ran when the robot is first turned on. This is where you should add any initialization code.
  }

  @Override
  public void robotPeriodic() {
    // This is ran every 20ms while the robot is on. This is where you should add any code that needs to run constantly.
  }

  @Override
  public void autonomousInit() {
    // This is ran when the robot is first put into autonomous mode. 
    // This is where you should add any code that needs to run when the robot is first put into autonomous mode.
  }

  @Override
  public void autonomousPeriodic() {
    // This is ran every 20ms while the robot is in autonomous mode.
  }

  @Override
  public void teleopInit() {
    // This is ran when the robot is first put into teleop mode. 
    // This is where you should add any code that needs to run when the robot is first put into teleop mode.
  }

  @Override
  public void teleopPeriodic() {
    // This is ran every 20ms while the robot is in teleop mode.
  }

  @Override
  public void disabledInit() {
    // This is ran when the robot is first put into disabled mode.
  }

  @Override
  public void disabledPeriodic() {
    // This is ran every 20ms while the robot is in disabled mode.
  }

  @Override
  public void testInit() {
    // This is ran when the robot is first put into test mode.
  }

  @Override
  public void testPeriodic() {
    // This is ran every 20ms while the robot is in test mode.
  }

  @Override
  public void simulationInit() {
    // This is ran when the robot is first put into simulation mode.
  }

  @Override
  public void simulationPeriodic() {
    // This is ran every 20ms while the robot is in simulation mode.
  }
}
