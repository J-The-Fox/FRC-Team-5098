package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Turret
{
    private final int kCenterThreshold = 0;
    private final int kMaxSensorReading = 1950;
    private final int kMinSensorReading = -1500;
    private final int kCenterSensorReading = (kMaxSensorReading + kMinSensorReading) / 2;

    private final TalonSRX motor;

    private final NetworkTable LimelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry targetX = LimelightTable.getEntry("tx");
    private final NetworkTableEntry targetPresent = LimelightTable.getEntry("tv");

    public Turret(int motorID)
    {
        motor = new TalonSRX(motorID);
        motor.setSelectedSensorPosition(0);
    }

    public double getPosition()
    {
        return motor.getSelectedSensorPosition();
    }

    public void turn(double power)
    {
        power = -power;
        final double sensorPosition = getPosition();
        if(((power < 0) && (sensorPosition < kMinSensorReading)) || ((power > 0) && (sensorPosition > kMaxSensorReading)))
        {
            motor.set(ControlMode.PercentOutput, 0);
        }
        else
        {
            motor.set(ControlMode.PercentOutput, power);
        }
    }

    public void track()
    {
        if(targetPresent.getDouble(0.0) == 1)
        {
            turn(targetX.getDouble(0.0) / 10.0);
        }
        else
        {
            motor.set(ControlMode.PercentOutput, 0);
        }
    }

    public void center()
    {
        final double sensorPosition = getPosition();
        final double power = isCentered() ? 0 : -sensorPosition - kCenterSensorReading;
        turn(-power / 100.0);
    }

    public boolean isCentered()
    {
        final double sensorPosition = getPosition();
        return Math.abs(sensorPosition - kCenterSensorReading) < kCenterThreshold;
    }
}