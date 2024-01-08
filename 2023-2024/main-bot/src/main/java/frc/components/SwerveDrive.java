package frc.components;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import frc.CommonData;
import frc.settings.SwerveDriveSettings;

public class SwerveDrive implements IComponent {

    /**
     * Overall Equation:
     *
     * Motor roations per second:
     * Motor rotations per minute / 60
     *
     * Wheel rotations per second:
     * Motor rotations per second / gear ratio
     *
     * Wheel radius (in ft):
     * Wheel diameter (in ft) / 2
     * or
     * Wheel radius (in inches) / 12
     *
     * Wheel circumference (in ft):
     * Wheel radius (in ft) * 2 * pi
     *
     * Speed (in ft/s):
     * Wheel rotations per second * wheel circumference (in ft)
     *
     * Total Equation:
     * (Motor rotations per minute / 60) / gear ratio * wheel circumference
     * (in ft)
     *
     * Note: The way it is lined out in the code is different but it still
     * functions the same.
     *
     * Note: Phoenix 6 has a Swerve Drive API that can be utilized.
     *       It may be worth looking into.
     *
     * Note: These values are *not* correct for our current frame.
     *       These are for the 2022-2023 robot, they need to be updated.
     */

    /**
     * The Falcon 500's RPM.
     */
    private final static int FALCON_RPM = 6380;

    /**
     * Gear ratio of the swerve modules.
     */
    private final static double GEAR_RATIO = 3;

    /**
     * Wheel circumference in feet.
     */
    private final static double WHEEL_CIRCUMFERENCE = 0.3191858136;

    /**
     * The maximum speed of the robot.
     *
     * <p>
     * 60 is used to convert from minutes to seconds.
     * </p>
     */
    private final static double MAX_SPEED    = (FALCON_RPM * WHEEL_CIRCUMFERENCE) / 60 / GEAR_RATIO;

    /**
     * The maximum rotation of the robot.
     */
    private final static double MAX_ROTATION = (MAX_SPEED * 2 * Math.PI) / 2.7389195456 / 2;

    // The 2.7389195456 value still needs to be documented

    /**
     * The offset of the yaw.
     */
    double yawOffset = 0;

    /**
     * The constructor for the SwerveDrive class.
     * @param settings  The settings for the swerve drive.
     */
    public SwerveDrive(final SwerveDriveSettings settings) {
        swerveWheels = new SwerveWheel[] {
            new SwerveWheel(settings.getFLSwerveSettings()),
            new SwerveWheel(settings.getFRSwerveSettings()),
            new SwerveWheel(settings.getBLSwerveSettings()),
            new SwerveWheel(settings.getBRSwerveSettings())
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
