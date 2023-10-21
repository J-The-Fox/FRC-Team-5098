// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Internal Imports //
import java.io.IOException; // Used to catch any input/output exceptions

// External Imports //
import com.fasterxml.jackson.databind.ObjectMapper; // Used to read the robot_settings.json file

// WPILib Imports //
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.CommonData;

// Local Imports //
import frc.components.IComponent;
import frc.components.SwerveDrive;
import frc.controllers.XboxOne; // Controller class
import frc.settings.Settings;

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

    // Read the robot_settings.json file and store it in the settings variable
    public static Settings settings;
    static
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            var filepath = Filesystem.getDeployDirectory().toPath().resolve("robot_settings.json");
            settings = mapper.readValue(filepath.toFile(), Settings.class);
        }
        catch (IOException ex) // Catch any input/output exceptions
        {
            System.out.println(ex.toString());
        }
    }

    // Initialize the controllers
    // NOTE: This is assuming that both controllers are Xbox One controllers. This could easily be changed in the future
    XboxOne controller = new XboxOne(settings.controllerID);
    XboxOne auxController = new XboxOne(settings.auxControllerID);

    // Set up the components
    // NOTE: More components will be added here. Only one here now is the Swerve Drive
    IComponent[] components = new IComponent[] {
        new SwerveDrive(settings.swerveDrive)
    };

    @Override
    public void robotInit() {}

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
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {

        // Update the controller values //

        // Update values for the Swerve Drive
        CommonData.battenDownTheHatches = controller.getX();
        CommonData.sideSpeed            = controller.getLeftStickX();
        CommonData.forwardSpeed         = controller.getLeftStickY();
        CommonData.desiredTurn          = Utility.snapToEdge(-controller.getRightStickX(), -1, 1, 0.9);
    }

    @Override
    public void disabledInit() {

        // "Turn off" all functions of the robot

        CommonData.desiredTurn  = 0; // Set the turn speed to 0
        CommonData.forwardSpeed = 0; // Set the drive speed to 0

        // Turn off the rumble on both controllers
        controller.rumbleOff();
        auxController.rumbleOff();

        CommonData.counter = 0; // Set the counter to 0 (used in autonomous)
    }

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
