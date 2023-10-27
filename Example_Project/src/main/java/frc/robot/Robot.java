// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Imports //
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Compressor;
import frc.CommonData;
import frc.components.IComponent;
import frc.components.ShuffleBoardController;
import frc.settings.Settings;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {

  // Read the robot_settings.json file and put it into the settings object.
  public static Settings settings;
  static {
      ObjectMapper mapper = new ObjectMapper();
      // Try to read the robot_settings.json file and put it into the settings object.
      try {
          var filepath = Filesystem.getDeployDirectory().toPath().resolve("robot_settings.json");
          settings     = mapper.readValue(filepath.toFile(), Settings.class);
      }
      // If there is an error, print the error to the console.
      catch (IOException ex) {
          System.out.println(ex.toString());
      }
  }

  // Create a new compressor object to control the pneumatics.

  // This could possible be moved to the Pneumatics.java component. 
  // I would like to see if it's possbible to see what types of a pnematic module is currently plugged in.
  // However I am not sure if that is possible. I may look into it in the future.
  Compressor compressor = new Compressor(settings.pneumaticsSettings.pneumaticsModuleID, PneumaticsModuleType.REVPH);

  // Create a new IComponent array and add all of the components to it.
  // This is used to call all the update methods in the periodic() method.
  IComponent[] RobotComponents = new IComponent[] {
    new ShuffleBoardController(settings.shuffleBoardSettings),

  };

  @Override
  public void robotInit() {
    // This is ran when the robot is first turned on. This is where you should add any initialization code.
  }

  @Override
  public void robotPeriodic() {
    // This is ran every 20ms while the robot is on. This is where you should add any code that needs to run constantly.

    // This calls all the update methods in the RobotComponents array to update all components.
    for (IComponent component : RobotComponents) {
      component.update();
    }
  }

  @Override
  public void autonomousInit() {
    // This is ran when the robot is first put into autonomous mode. 
    // This is where you should add any code that needs to run when the robot is first put into autonomous mode.
  }

  @Override
  public void autonomousPeriodic() {
    // This is ran every 20ms while the robot is in autonomous mode.

    for (IComponent component : RobotComponents) {
      component.update();
    }
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
