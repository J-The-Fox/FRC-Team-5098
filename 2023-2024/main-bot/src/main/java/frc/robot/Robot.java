// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Imports //
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.CommonData;
import frc.components.IComponent;
import frc.components.SwerveDrive;
import frc.controllers.Xbox;
import frc.controllers.PS4;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.settings.Settings;

/**
* The VM is configured to automatically run this class,
* and to call the functions corresponding to
* each mode, as described in the TimedRobot documentation.
* If you change the name of this class or
* the package after creating this project,
* you must also update the build.gradle file in the
* project.
*/
public class Robot extends TimedRobot {
    /**
    * This function is run when the robot is first started up
    * and should be used for any initialization code.
    */

    // Read the robot_settings.json file and store it in the settings variable
    public static Settings settings;
    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            var filepath = Filesystem.getDeployDirectory().toPath().resolve(
                "robot_settings.json"
                );
            settings = mapper.readValue(filepath.toFile(), Settings.class);
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    // Initialize the controllers
    // NOTE: This is assuming that both controllers are Xbox One controllers. This could easily be changed in the future
    Xbox controller = new Xbox(settings.controllerID, 0);
    PS4 auxController = new PS4(settings.auxControllerID, 0);

    // Set up the components
    // NOTE: More components will be added here. Only one here now is the Swerve Drive
    IComponent[] components = new IComponent[] {
        new SwerveDrive(settings.swerveDrive)
    };

    @Override
    public void robotInit() {
    }

    @Override
    public void robotPeriodic() {

        // For each component in the components array, update it
        // This calls the update method in each of the component classes
        // Since this in the robotPeriodic method, this will apply to all modes (autonomous, teleop, etc.)
        for (var actuator : components)
        {
            actuator.update();
        }
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {

        // Update the controller values //

        // Update values for the Swerve Drive
        CommonData.setBattenDownTheHatches(controller.getXButton());
        CommonData.setSideSpeed(controller.getLeftX());
        CommonData.setForwardSpeed(controller.getLeftY());
        CommonData.setDesiredTurn(Utility.snapToEdge(-controller.getRightX(), -1, 1, 0.9));
    }

    @Override
    public void disabledInit() {

        // "Turn off" all functions of the robot

        CommonData.setDesiredTurn(0); // Set the turn speed to 0
        CommonData.setForwardSpeed(0); // Set the drive speed to 0

        // Turn off the rumble on any controllers
        controller.setRumble(RumbleType.kBothRumble, 0);

        CommonData.setCounter(0); // Set the counter to 0 (used in autonomous)
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void simulationInit() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
