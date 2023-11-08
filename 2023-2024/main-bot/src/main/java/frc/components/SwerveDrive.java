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
    private final static double MAX_SPEED    = (6380 * 0.3191858136) / 60 / 3;
    private final static double MAX_ROTATION = (MAX_SPEED * 2 * Math.PI) / 2.7389195456 / 2;

    double yawOffset = 0;

    public SwerveDrive(final SwerveDriveSettings settings) {
        swerveWheels = new SwerveWheel[] {
            new SwerveWheel(settings.FLSwerve),
            new SwerveWheel(settings.FRSwerve),
            new SwerveWheel(settings.BLSwerve),
            new SwerveWheel(settings.BRSwerve)
        };

        kinematics = new SwerveDriveKinematics(swerveWheels[0].getSwervePos(), swerveWheels[1].getSwervePos(), swerveWheels[2].getSwervePos(), swerveWheels[3].getSwervePos());
    }

    SwerveWheel[] swerveWheels;

    double leftOrRight;
    double forwardOrBack;  // This is the forward or back speed

    private AHRS ahrs = new AHRS(SPI.Port.kMXP);

    private SwerveDriveKinematics kinematics;

    public void update() {

        // Calabrate The NavX
        if (CommonData.getCalibrate()) {
            CommonData.setCalibrate(false);
            yawOffset            = ahrs.getYaw();
        }

        // Set to an if statement so the defensive code takes priority over driving
        if (CommonData.getBattenDownTheHatches()) {
            for (int i = 0; i < 4; i++) {
                swerveWheels[i].defense();
            }
        }
        else {
            final var desiredTranslationSpeedX = CommonData.getForwardSpeed() * MAX_SPEED; // This code uses '+x' as toward opposing alliance wall
            final var desiredTranslationSpeedY = -CommonData.getSideSpeed() * MAX_SPEED;   // This code uses '+y' as toward driver's right
            final var desiredRotationSpeed     = -CommonData.getDesiredTurn() * MAX_ROTATION;
            var       moduleStates             = kinematics.toSwerveModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(desiredTranslationSpeedX, desiredTranslationSpeedY, desiredRotationSpeed, Rotation2d.fromDegrees(ahrs.getYaw())));

            for (int i = 0; i < 4; i++) {
                swerveWheels[i].set(moduleStates[i]);
            }
        }
    } // Man that's a lot of curly braces
}
