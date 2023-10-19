package frc.components;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import frc.CommonData;
import frc.robot.Utility;
import frc.settings.SwerveDriveSettings;

public class SwerveDrive implements IComponent
{
    final static double maximumSpeed    = (6380 * 0.3191858136) / 60 / 3;
    final static double maximumRotation = (maximumSpeed * 2 * Math.PI) / 2.7389195456 / 2;

    double yawOffset = 0;

    public SwerveDrive(SwerveDriveSettings settings)
    {
        swerveWheels = new SwerveWheel[] {
            new SwerveWheel(settings.FLSwerve),
            new SwerveWheel(settings.FRSwerve),
            new SwerveWheel(settings.BLSwerve),
            new SwerveWheel(settings.BRSwerve)};

        kinematics = new SwerveDriveKinematics(swerveWheels[0].getSwervePos(), swerveWheels[1].getSwervePos(), swerveWheels[2].getSwervePos(), swerveWheels[3].getSwervePos());
    }

    SwerveWheel[] swerveWheels;

    double leftOrRight;
    double forwardOrBack;

    AHRS ahrs = new AHRS(SPI.Port.kMXP);

    SwerveDriveKinematics kinematics;

    public void balance()
    {
        final double maximumTiltAngle = 12.81974281;

        forwardOrBack = Math.abs(CommonData.robotPitch) > 10 ? CommonData.robotPitch / maximumTiltAngle : 0;
        leftOrRight   = Math.abs(CommonData.robotRoll) > 10 ? CommonData.robotRoll / maximumTiltAngle : 0;

        Utility.printLn("" + forwardOrBack);
        final var desiredTranslationSpeedX = forwardOrBack * -maximumSpeed; // This code uses '+x' as toward opposing alliance wall
        final var desiredTranslationSpeedY = 0;                            // This code uses '+y' as toward driver's right
        final var desiredRotationSpeed     = -leftOrRight * maximumRotation;
        var       moduleStates             = kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(desiredTranslationSpeedX, desiredTranslationSpeedY, desiredRotationSpeed, Rotation2d.fromDegrees(ahrs.getYaw() - yawOffset)));

        for (int i = 0; i < 4; i++)
        {
            moduleStates[i].speedMetersPerSecond *= 0.1;
            swerveWheels[i].set(moduleStates[i]);
        }
    }

    public void update()
    {
        CommonData.robotPitch = ahrs.getPitch();
        CommonData.robotRoll  = ahrs.getRoll();

        // Calabrate The NavX
        if (CommonData.calibrate == true)
        {
            CommonData.calibrate = false;
            yawOffset            = ahrs.getYaw();
        }

        if (CommonData.battenDownTheHatches)
        {
            for (int i = 0; i < 4; i++)
            {
                swerveWheels[i].defense();
            }
        }
        else if (CommonData.balance)
        {
            balance();
        }
        else
        {
            final var desiredTranslationSpeedX = CommonData.forwardSpeed * maximumSpeed; // This code uses '+x' as toward opposing alliance wall
            final var desiredTranslationSpeedY = -CommonData.sideSpeed * maximumSpeed;   // This code uses '+y' as toward driver's right
            final var desiredRotationSpeed     = -CommonData.desiredTurn * maximumRotation;
            var       moduleStates             = kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(desiredTranslationSpeedX, desiredTranslationSpeedY, desiredRotationSpeed, Rotation2d.fromDegrees(ahrs.getYaw())));

            for (int i = 0; i < 4; i++)
            {
                swerveWheels[i].set(moduleStates[i]);
            }
        }
    }
}