package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Launcher
{
    private final TalonFX leftEar;
    private final TalonFX rightEar;
    private final DigitalInput ballSensor;

    public Launcher(int leftId, int rightId, int sensorId)
    {
        leftEar = new TalonFX(leftId);
        rightEar = new TalonFX(rightId);
        ballSensor = new DigitalInput(sensorId);
    }

    public boolean ballDetected()
    {
        return ballSensor.get();
    }

    public void fire(double power)
    {
        leftEar.set(ControlMode.PercentOutput, power);
        rightEar.set(ControlMode.PercentOutput, -power);
    }
}