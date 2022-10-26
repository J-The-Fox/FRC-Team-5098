package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class WheelOfFortune implements System
{
  public enum Mode
  {
    Off,
    Stage1,
    Stage2
  }

  // Konstants
  //private final Color kBlueTarget = ColorMatch.makeColor(.1, .4, .5);
  //private final Color kGreenTarget = ColorMatch.makeColor(.2, .6, .2);
  //private final Color kRedTarget = ColorMatch.makeColor(.6, .3, .1);
  //private final Color kYellowTarget = ColorMatch.makeColor(.3, .6, .1);

  private final int kMinimumRotations = 20;
  private final double kMotorSpeed = 1;

  // Components
  //private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  //private final ColorMatch colorMatcher = new ColorMatch();

  //private final TalonSRX motor;

  // Member variables
  private Mode currentMode = Mode.Off;

  //private final ColorWheelCounter colorWheelCounter;

  //public WheelOfFortune(int motorID)
  //{
  //  colorMatcher.addColorMatch(kRedTarget);
  //  colorMatcher.addColorMatch(kGreenTarget);
  //  colorMatcher.addColorMatch(kBlueTarget);
  //  colorMatcher.addColorMatch(kYellowTarget);

  //  colorWheelCounter = new ColorWheelCounter(colorMatcher);

  //  motor = new TalonSRX(motorID);
  //}
  
  public void setCurrentMode(Mode mode) {
    currentMode = mode;
  }

  @Override
  public void execute() {
    switch(currentMode)
    {
      case Off:
        //motor.set(ControlMode.PercentOutput, 0);
        break;

      case Stage1:
        handleStage1();
        break;

      case Stage2:
        handleStage2();
        break;
    }
  }

  private void handleStage1()
  {
    //colorWheelCounter.observeColor(colorSensor.getColor());

    //if(colorWheelCounter.discRotations() >= kMinimumRotations)
    {
    //  motor.set(ControlMode.PercentOutput, 0);
    }
    //else
    {
    //  motor.set(ControlMode.PercentOutput, kMotorSpeed);
    }
  }

  private void handleStage2()
  {
    final var colorTarget = getTargetColor();

    //final var colorCurrentlySeen = colorMatcher.matchClosestColor(colorSensor.getColor()).color;

    //if(colorCurrentlySeen == colorTarget) {
    //  motor.set(ControlMode.PercentOutput, 0);
    //}
    //else {
    //  motor.set(ControlMode.PercentOutput, kMotorSpeed);
    //}
  }

  private Color getTargetColor()
  {
    switch(DriverStation.getGameSpecificMessage()) {
      case "R": 
      //  return kRedTarget;
      case "G":
      //  return kGreenTarget;
      case "B":
      //  return kBlueTarget;
      case "Y":
      //  return kYellowTarget;
      default:
        return null;
    }
  }
}