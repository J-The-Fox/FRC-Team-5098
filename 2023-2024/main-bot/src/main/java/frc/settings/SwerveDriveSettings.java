package frc.settings;

/**
 * The swerve drive settings.
 * <p>
 * This is used to store the settings for the swerve drive.
 * Each wheel has its own settings located in the {@link SwerveWheelSettings}
 * class.
 */
public class SwerveDriveSettings {

    // NOTE: In this change from public to private, the way we currently read
    //       the settings file might be broken. This is because it relies on
    //       the fields being public as it has to be able to access them. These
    //       values have to be the same as what is in the JSON file. Since they
    //       are now private and have accessors, we might have to change the
    //       way we read these values. An alternative might be to use the
    //       SimpleJSON library instead of the Jackson library as it might
    //       allow us to access the fields without them being public.
    //       This needs further testing.
    //
    //       - Jay

    /**
     * The swerve wheel settings for the front left wheel.
     */
    private SwerveWheelSettings flSwerve;

    /**
     * The swerve wheel settings for the front right wheel.
     */
    private SwerveWheelSettings frSwerve;

    /**
     * The swerve wheel settings for the back left wheel.
     */
    private SwerveWheelSettings blSwerve;

    /**
     * The swerve wheel settings for the back right wheel.
     */
    private SwerveWheelSettings brSwerve;

    // Accessor Methods //

    /**
     * Get the swerve wheel settings for the front left wheel.
     * @return The swerve wheel settings for the front left wheel.
     */
    public SwerveWheelSettings getFLSwerveSettings() {
        return flSwerve;
    }

    /**
     * Set the swerve wheel settings for the front left wheel.
     *
     * @param settings  The swerve wheel settings for the front left wheel.
     */
    public void setFLSwerveSettings(final SwerveWheelSettings settings) {
        flSwerve = settings;
    }

    /**
     * Get the swerve wheel settings for the front right wheel.
     *
     * @return The swerve wheel settings for the front right wheel.
     */
    public SwerveWheelSettings getFRSwerveSettings() {
        return frSwerve;
    }

    /**
     * Set the swerve wheel settings for the front right wheel.
     *
     * @param settings  The swerve wheel settings for the front right wheel.
     */
    public void setFRSwerveSettings(final SwerveWheelSettings settings) {
        frSwerve = settings;
    }

    /**
     * Get the swerve wheel settings for the back left wheel.
     *
     * @return The swerve wheel settings for the back left wheel.
     */
    public SwerveWheelSettings getBLSwerveSettings() {
        return blSwerve;
    }

    /**
     * Set the swerve wheel settings for the back left wheel.
     *
     * @param settings  The swerve wheel settings for the back left wheel.
     */
    public void setBLSwerveSettings(final SwerveWheelSettings settings) {
        blSwerve = settings;
    }

    /**
     * Get the swerve wheel settings for the back right wheel.
     *
     * @return The swerve wheel settings for the back right wheel.
     */
    public SwerveWheelSettings getBRSwerveSettings() {
        return brSwerve;
    }

    /**
     * Set the swerve wheel settings for the back right wheel.
     *
     * @param settings  The swerve wheel settings for the back right wheel.
     */
    public void setBRSwerveSettings(final SwerveWheelSettings settings) {
        brSwerve = settings;
    }
}
