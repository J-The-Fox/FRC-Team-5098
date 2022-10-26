package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Intake
{
    public enum Direction
    {
        In,
        Out,
        Off,
    }

    public enum State
    {
        Retracted,
        Deployed,
        Neutral,
    }

    private State lastState = State.Neutral;
    private State actualState = State.Neutral;

    private final TalonSRX intakeMotor;
    private final TalonSRX deployMotor;
    private final DigitalInput ballSensor;

    private Timer timer = null;

    private final double kIntakeMotorSpeedIn = 0.75;
    private final double kIntakeMotorSpeedOut = 1;
    private final double kDeployMotorSpeed = 0.2;
    private final double kDeployMotorCurrentCutoff = 3;

    public Intake(int ballMotorId, int deployMotorId, int sensorId)
    {
        intakeMotor = new TalonSRX(ballMotorId);
        deployMotor = new TalonSRX(deployMotorId);
        ballSensor = new DigitalInput(sensorId);
    }

    public boolean ballDetected()
    {
        if(ballSensor.get())
        {
            timer = null;
            return true;
        }
        else if(timer == null)
        {
            timer = new Timer();
            timer.start();
        }
        return timer.get() <= 0.1;
    }

    public void set(Direction direction)
    {
        switch(direction)
        {
        case In:
            intakeMotor.set(ControlMode.PercentOutput, kIntakeMotorSpeedIn);
            break;
        case Out:
            intakeMotor.set(ControlMode.PercentOutput, -kIntakeMotorSpeedOut);
            break;
        case Off:
            intakeMotor.set(ControlMode.PercentOutput, 0.0);
            break;
        }
    }

    public void set(State state)
    {
        if(state != lastState)
        {
            lastState = state;
            actualState = state;
        }

        if(deployMotor.getSupplyCurrent() >= kDeployMotorCurrentCutoff)
        {
            actualState = State.Neutral;
        }

        switch(actualState)
        {
        case Retracted:
            deployMotor.set(ControlMode.PercentOutput, -kDeployMotorSpeed);
            break;
        case Deployed:
            deployMotor.set(ControlMode.PercentOutput, kDeployMotorSpeed);
            break;
        case Neutral:
            deployMotor.set(ControlMode.PercentOutput, 0);
            break;
        }
    }
}