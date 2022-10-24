// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Vector;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.datum.DatumBooleanDashboard;
import frc.robot.datum.DatumConstant;
import frc.robot.datum.DatumDoubleDashboard;
import frc.robot.datum.DatumMap;
import frc.robot.datum.DatumReducer;
import frc.robot.systems.ArcadeDrive;
import frc.robot.systems.Auto;
import frc.robot.systems.BallSystem;
import frc.robot.components.Conveyor;
import frc.robot.components.Intake;
import frc.robot.components.Launcher;
import frc.robot.systems.System;
import frc.robot.systems.Teleop;
import frc.robot.components.Turret;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
  AHRS navx = new AHRS();

  // ---------
  // Konstants
  // ---------

  // Konstants - Controller IDs
  private final int kDriveController = 0;
  private final int kAuxController = 1;

  // Konstants - Actuator IDs
  private final int[] kLeftDriveIds = {21, 22};
  private final int[] kRightDriveIds = {23, 24};
  private final int kLeftEarId = 10;
  private final int kRightEarId = 11;
  private final int kTurretId = 3;
  private final int kConveyorId = 5;
  private final int kIntakeId = 6;
  private final int kIntakeDeployId = 1;
  
  // Konstants - Sensor IDs
  private final int kIntakeBallSensorChannel = 4;
  private final int kLauncherBallSensorChannel = 3;

  // ----------
  // Components
  // ----------

  private final Turret turret = new Turret(kTurretId);
  private final Launcher launcher = new Launcher(kLeftEarId, kRightEarId, kLauncherBallSensorChannel);
  private final Conveyor conveyor = new Conveyor(kConveyorId);
  private final Intake intake = new Intake(kIntakeId, kIntakeDeployId, kIntakeBallSensorChannel);

  // -------
  // Systems
  // -------

  private Iterable<System> systems;

  private final Teleop teleop = new Teleop(kDriveController, kAuxController);
  private final BallSystem ballSystem = new BallSystem(turret, launcher, conveyor, intake);
  private final ArcadeDrive driveSystem = new ArcadeDrive(kLeftDriveIds, kRightDriveIds, navx);
  private final Auto auto = new Auto(driveSystem.getTracker());

  // ---------
  // Variables
  // ---------

  private SendableChooser<Integer> stageChooser = new SendableChooser<Integer>();
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit()
  {
    while(navx.isCalibrating())
    {
        Timer.delay(0.1);
    }
    Timer.delay(0.3);
    navx.reset();

    driveSystem.getTracker().reset();

    // Add systems - order is important, as sources of data should be updated
    //  before used by other systems

    Vector<System> s = new Vector<System>();
    s.add(teleop);
    s.add(auto);
    s.add(ballSystem);
    s.add(driveSystem);

    systems = s;

    // Set up SmartDashboard controls

    stageChooser.setDefaultOption("1", 1);
    stageChooser.addOption("2", 2);
    SmartDashboard.setDefaultBoolean("Wheel System/Auto", true);
    SmartDashboard.putData("Wheel System/Stage", stageChooser);
    SmartDashboard.putString("Wheel System/Color", "???");

    SmartDashboard.setDefaultBoolean("Turret System/Auto", true);
    SmartDashboard.setDefaultNumber("Turret System/Speed", 0.0);
    SmartDashboard.setDefaultBoolean("Turret System/Ball", false);
    SmartDashboard.setDefaultBoolean("Turret System/Center", false);
    SmartDashboard.setDefaultBoolean("Turret System/Centered", false);
    SmartDashboard.setDefaultNumber("Turret System/Encoder", 0.0);

    SmartDashboard.setDefaultBoolean("Conveyor System/Auto", true);
    SmartDashboard.setDefaultBoolean("Conveyor System/Ball", false);

    SmartDashboard.setDefaultBoolean("Autonomous Mode/On", true);
    SmartDashboard.setDefaultNumber("Autonomous Mode/Delay", 0.0);
    SmartDashboard.setDefaultNumber("Gyro heading", 0.0);

    SmartDashboard.setPersistent("Wheel System/Auto");
    SmartDashboard.setPersistent("Wheel System/Stage");
    SmartDashboard.setPersistent("Wheel System/Color");

    SmartDashboard.setPersistent("Turret System/Auto");
    SmartDashboard.setPersistent("Turret System/Speed");
    SmartDashboard.setPersistent("Turret System/Ball");
    SmartDashboard.setPersistent("Turret System/Center");
    SmartDashboard.setPersistent("Turret System/Centered");
    SmartDashboard.setPersistent("Turret System/Encoder");

    SmartDashboard.setPersistent("Conveyor System/Auto");
    SmartDashboard.setPersistent("Conveyor System/Ball");

    SmartDashboard.setPersistent("Autonomous Mode/On");
    SmartDashboard.setPersistent("Autonomous Mode/Delay");
    SmartDashboard.setPersistent("Gyro heading");

    ballSystem.autoConveyor = new DatumBooleanDashboard("Conveyor System/Auto");
    ballSystem.autoTurret = new DatumBooleanDashboard("Turret System/Auto");
    ballSystem.centerTurret = new DatumBooleanDashboard("Turret System/Center");
    auto.delay = new DatumDoubleDashboard("Autonomous Mode/Delay");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic()
  {
    systems.forEach(system -> system.execute());
  }

  /**
   * This function is called once each time the robot enters autonomous.
   */
  @Override
  public void autonomousInit()
  {
    auto.reset();

    driveSystem.power = auto.driveSpeed;
    driveSystem.turn = auto.driveTurn;
    driveSystem.creep = new DatumConstant<Boolean>(true);

    ballSystem.conveyorDirection = auto.conveyorDirection;
  
    ballSystem.intakeDirection = auto.intakeDirection;
    ballSystem.intakeState = auto.intakeState;
    ballSystem.launcherActive = auto.launcherActive;
    ballSystem.setLauncherSpeed = auto.launcherSpeed;
    ballSystem.setTurretTurn = auto.turretTurn;
    ballSystem.centerTurret = auto.centerTurrent;

    teleop.disable();
    auto.disable();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic()
  {
  }

  /**
   * This function is called once each time the robot enters operator control.
   */
  @Override
  public void teleopInit()
  {
    driveSystem.power = new DatumMap<Double, Double>(teleop.driveSpeed, d -> Math.pow(d, 3));
    driveSystem.turn = new DatumMap<Double, Double>(teleop.driveTurn, d -> Math.pow(d, 3));
    driveSystem.creep = teleop.creep;

    ballSystem.conveyorDirection = new DatumReducer<Boolean, Conveyor.Direction>(teleop.conveyorIn, teleop.conveyorOut, (in, out) -> {
      return out ? Conveyor.Direction.Out : in ? Conveyor.Direction.In : Conveyor.Direction.Off;
    });
    ballSystem.intakeDirection = new DatumReducer<Boolean, Intake.Direction>(teleop.intakeIn, teleop.intakeOut, (in, out) -> {
      return out ? Intake.Direction.Out : in ? Intake.Direction.In : Intake.Direction.Off;
    });
    ballSystem.intakeState = new DatumReducer<Boolean, Intake.State>(teleop.intakeUp, teleop.intakeDown, (up, down) -> {
      return up ? Intake.State.Retracted : down ? Intake.State.Deployed : Intake.State.Neutral;
    });
    ballSystem.launcherActive = teleop.launcherActivate;
    ballSystem.setLauncherSpeed = teleop.launcherSpeed;
    ballSystem.setTurretTurn = teleop.turretTurn;

    auto.disable();
    teleop.enable();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
  }

  /**
   * This function is called once each time the robot enters test mode.
   */
  @Override
  public void testInit()
  {
    auto.disable();
    teleop.disable();

    driveSystem.power = new DatumConstant<Double>(0.0);
    driveSystem.turn = new DatumConstant<Double>(0.0);
    driveSystem.creep = new DatumConstant<Boolean>(false);

    ballSystem.conveyorDirection = new DatumConstant<Conveyor.Direction>(Conveyor.Direction.Off);
    ballSystem.intakeDirection = new DatumConstant<Intake.Direction>(Intake.Direction.Off);
    ballSystem.intakeState = new DatumConstant<Intake.State>(Intake.State.Neutral);
    ballSystem.launcherActive = new DatumConstant<Boolean>(false);
    ballSystem.setLauncherSpeed = new DatumConstant<Double>(0.0);
    ballSystem.setTurretTurn = new DatumConstant<Double>(0.0);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic()
  {
  }

  /**
   * This function is called once each time the robot enters robot disabled.
   */
  @Override
  public void disabledInit()
  {
    auto.disable();
    teleop.disable();

    driveSystem.power = new DatumConstant<Double>(0.0);
    driveSystem.turn = new DatumConstant<Double>(0.0);
    driveSystem.creep = new DatumConstant<Boolean>(false);

    ballSystem.conveyorDirection = new DatumConstant<Conveyor.Direction>(Conveyor.Direction.Off);
    ballSystem.intakeDirection = new DatumConstant<Intake.Direction>(Intake.Direction.Off);
    ballSystem.intakeState = new DatumConstant<Intake.State>(Intake.State.Neutral);
    ballSystem.launcherActive = new DatumConstant<Boolean>(false);
    ballSystem.setLauncherSpeed = new DatumConstant<Double>(0.0);
    ballSystem.setTurretTurn = new DatumConstant<Double>(0.0);
  }

  /**
   * This function is called periodically during robot disabled.
   */
  @Override
  public void disabledPeriodic()
  {
  }
}
