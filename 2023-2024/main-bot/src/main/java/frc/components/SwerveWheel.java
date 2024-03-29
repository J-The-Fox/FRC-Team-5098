package frc.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.settings.SwerveWheelSettings;
import frc.settings.SwerveWheelSettings.Location;

public class SwerveWheel
{
    TalonFX  turnMotor;
    TalonFX  driveMotor;
    CANCoder encoder;

    // 0.238125 Meters
    // 0.365125 Meters

    Translation2d modulePos;

    double defensiveAngle;

    Location location;

    public SwerveWheel(SwerveWheelSettings settings)
    {
        turnMotor  = new TalonFX(settings.getTurnID());
        driveMotor = new TalonFX(settings.getDriveID());
        encoder    = new CANCoder(settings.getEncoderID());

        location       = settings.getLocation();
        defensiveAngle = settings.getDefensiveAngle();

        modulePos = new Translation2d(settings.getPosX(), settings.getPosY());
    }

    public void set(SwerveModuleState swerveModuleState) {

        // TODO: Add documentation for this so each value is explained
        swerveModuleState   = SwerveModuleState.optimize(swerveModuleState, Rotation2d.fromDegrees(turnMotor.getSelectedSensorPosition() / 11.3777778));
        double maximumSpeed = (6380 * 0.3191858136) / 60 / 3;

        final double desiredTurnPos = swerveModuleState.angle.getDegrees() * 11.3777778; // conversion from degrees to native encoder value

        turnMotor.set(ControlMode.Position, desiredTurnPos);

        driveMotor.set(ControlMode.PercentOutput, swerveModuleState.speedMetersPerSecond / maximumSpeed);
        // Using Velocity Can Be Used For Better Control But Percent Output Is Better Used For Practice
    }

    public void defense() {

        // TODO: Add documentation for the 11.77777777 value
        turnMotor.set(ControlMode.Position, defensiveAngle * 11.77777777);
        driveMotor.set(ControlMode.PercentOutput, 0);
    }

    public TalonFX getTurnMotor() {
        return turnMotor;
    }

    public TalonFX getDriveMotor() {
        return driveMotor;
    }

    public Translation2d getSwervePos() {
        return modulePos;
    }

    // public void battenDownTheHatches()
    // {
    //     set(defensiveAngle, 0, 0);
    // }
}