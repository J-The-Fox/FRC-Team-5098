package frc.settings;

/**
 * The settings object.
 * <p>
 * This is used to store the settings from the robot_settings.json file.
 * </p>
 */
public class Settings {

    /**
     * The swerve drive settings.
     */
    private SwerveDriveSettings swerveDrive;

    // These might change to be more dynamic if possible

    /**
     * The controller ID.
     */
    private int controllerID;

    /**
     * The auxiliary controller ID.
     */
    private int auxControllerID;

    // Accessor Methods //

    /**
     * Get the swerve drive settings.
     * @return The swerve drive settings.
     */
    public SwerveDriveSettings getSwerveDriveSettings() {
        return swerveDrive;
    }

    /**
     * Set the swerve drive settings.
     * @param settings  The swerve drive settings.
     */
    public void setSwerveDriveSettings(final SwerveDriveSettings settings) {
        swerveDrive = settings;
    }

    /**
     * Get the controller ID.
     * @return The controller ID.
     */
    public int getControllerID() {
        return controllerID;
    }

    /**
     * Set the controller ID.
     * @param id  The controller ID.
     */
    public void setControllerID(final int id) {
        controllerID = id;
    }

    /**
     * Get the auxiliary controller ID.
     * @return The auxiliary controller ID.
     */
    public int getAuxControllerID() {
        return auxControllerID;
    }

    /**
     * Set the auxiliary controller ID.
     * @param id  The auxiliary controller ID.
     */
    public void setAuxControllerID(final int id) {
        auxControllerID = id;
    }
}
