package frc.components;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import frc.CommonData;
import frc.settings.SwerveDriveSettings;

public class SwerveDrive implements IComponent {

    // This needs to be recaulculated for the new swerve drive and chassis
    // TODO: Add documentation for this so each value is explained
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

    public void update()
    {

        // Calabrate The NavX
        if (CommonData.calibrate == true)
        {
            CommonData.calibrate = false;
            yawOffset            = ahrs.getYaw();
        }

        // Set to an if statement so the defensive code takes priority over driving
        if (CommonData.battenDownTheHatches)
        {
            for (int i = 0; i < 4; i++)
            {
                swerveWheels[i].defense();
            }
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