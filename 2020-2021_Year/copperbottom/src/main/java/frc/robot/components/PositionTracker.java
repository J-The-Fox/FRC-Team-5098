package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class PositionTracker
{
    private final TalonFX leftMotor;
    private final TalonFX rightMotor;
    private final AHRS gyro;

    private final double kTicksPerInch = 662.05304212168486739469578783151;


    public PositionTracker(TalonFX leftMotor, TalonFX rightMotor, AHRS gyro)
    {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
    }

    public double getDistanceInches()
    {
        final var distLt = (leftMotor.getSelectedSensorPosition()) / kTicksPerInch;
        final var distRt = -(rightMotor.getSelectedSensorPosition()) / kTicksPerInch;

        return (distLt + distRt) / 2;
    }
    
    public double getHeading()
    {
        return gyro.getYaw();
    }

    public void reset()
    {
        leftMotor.setSelectedSensorPosition(0);
        rightMotor.setSelectedSensorPosition(0);
        gyro.zeroYaw();
    }
}
