// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.CommonData;
import frc.components.Arm;
import frc.components.Claw;
import frc.components.IComponent;
import frc.components.SwerveDrive;
import frc.components.autonomous.Autonomous;
import frc.controllers.LogitechF310;
import frc.controllers.Xbox360;
import frc.settings.Settings;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    /*
     * Constants should go here. For example:
     *
     * final int kMainControllerId = 0;
     * final int kAuxControllerId = 1;
     *
     * final int kDriveMotorTopLeftId = 1;
     * final int kDriveMotorTopRightId = 2;
     * ...
     *
     * Each constant should start with 'k'. This is a convention.
     * Each Id should be given its own constant.
     * Each constant should clearly say what it is for.
     */

    public static Settings settings;
    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            var filepath = Filesystem.getDeployDirectory().toPath().resolve("robot_settings.json");
            settings     = mapper.readValue(filepath.toFile(), Settings.class);
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    final int kPneumaticsModuleId = 0;

    final int kClawOpeningPortId = 0;
    final int kClawClosingPortId = 1;

    Xbox360      xbox360      = new Xbox360(settings.controllerID);
    LogitechF310 logitechF310 = new LogitechF310(settings.auxControllerID);

    Autonomous autonomous;

    Compressor compressor = new Compressor(settings.clawSettings.pneumaticsModuleId, PneumaticsModuleType.REVPH);

    // IComponent[] sensorComponents;
    IComponent[] actuatorComponents = new IComponent[] {
        new Claw(settings.clawSettings),
        new Arm(settings.armSettings),
        new SwerveDrive(settings.swerveDrive)

    };

    boolean driverIsAnna = false;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        // compressor.disable();
    }

    @Override
    public void robotPeriodic() {
        // for(var sensor : sensorComponents)
        //{
        //   sensor.update();
        // }

        for (var actuator : actuatorComponents) {
            actuator.update();
        }
    }

    @Override
    public void autonomousInit() {
        autonomous           = new Autonomous();
        CommonData.calibrate = true;
    }

    @Override
    public void autonomousPeriodic() {
        autonomous.update();
    }

    @Override
    public void teleopInit() {
    }

    // 18.75 -> 0.47625
    // 28.75 -> 0.73025

    // 0.238125 Meters
    // 0.365125 Meters

    @Override
    public void teleopPeriodic()
    {
        CommonData.balance              = xbox360.getB();
        CommonData.battenDownTheHatches = xbox360.getX();
        CommonData.sideSpeed            = xbox360.getLeftStickX();
        CommonData.forwardSpeed         = xbox360.getLeftStickY();
        CommonData.desiredTurn          = Utility.snapToEdge(-xbox360.getRightStickX(), -1, 1, 0.9);

        CommonData.desiredShoulderAngle = CommonData.shoulderAngle + (logitechF310.getLeftStickY() * 35000); // 10000
        CommonData.desiredElbowAngle    = CommonData.elbowAngle + (logitechF310.getRightStickY() * 10000);   // 10000

        if (logitechF310.getDUp()) {
            CommonData.desiredShoulderAngle = Arm.Position.kTopShelf.shoulderPos;
            CommonData.desiredElbowAngle    = Arm.Position.kTopShelf.elbowPos;
        }
        else if (logitechF310.getDDown()) {
            CommonData.desiredShoulderAngle = Arm.Position.kMiddleShelfOrHumanPlayer.shoulderPos;
            CommonData.desiredElbowAngle    = Arm.Position.kMiddleShelfOrHumanPlayer.elbowPos;
        }
        else if (logitechF310.getA()) {
            CommonData.desiredShoulderAngle = Arm.Position.kGroundPickup.shoulderPos;
            CommonData.desiredElbowAngle    = Arm.Position.kGroundPickup.elbowPos;
        }
        else if (logitechF310.getB()) {
            CommonData.desiredShoulderAngle = Arm.Position.kHome.shoulderPos;
            CommonData.desiredElbowAngle    = Arm.Position.kHome.elbowPos;
        }
        else if (logitechF310.getY())
        {
            CommonData.desiredShoulderAngle = Arm.Position.kHumanPlayerStation.shoulderPos;
            CommonData.desiredElbowAngle    = Arm.Position.kHumanPlayerStation.elbowPos;
        }

        if (logitechF310.getLeftBumper()) {
            CommonData.clawState = Claw.State.kOpen;
        }
        else if (logitechF310.getRightBumper()) {
            CommonData.clawState = Claw.State.kClosed;
        }
    }

    @Override
    public void disabledInit() {
        CommonData.clawState    = Claw.State.kClosed;
        CommonData.sideSpeed    = 0;
        CommonData.desiredTurn  = 0;
        CommonData.forwardSpeed = 0;
        CommonData.balance      = false;

        autonomous = null;
        xbox360.rumbleOff();

        CommonData.counter = 0;

        // "Zero" out the rest of the variables (as they are added) to safe, sensible default values
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
        CommonData.calibrate = true;
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
