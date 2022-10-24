package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Conveyor
{
    public enum Direction
    {
        In,
        Out,
        Off,
    }

    private final double kMotorSpeedIn = 0.75;
    private final double kMotorSpeedOut = 0.50;

    private final TalonSRX motor;

    public Conveyor(int motorId)
    {
        motor = new TalonSRX(motorId);
    }

    public void set(Direction direction)
    {
        switch(direction)
        {
        case In:
            motor.set(ControlMode.PercentOutput, -kMotorSpeedIn);
            break;
        case Out:
            motor.set(ControlMode.PercentOutput, kMotorSpeedOut);
            break;
        case Off:
            motor.set(ControlMode.PercentOutput, 0);
            break;
        }
    }
}