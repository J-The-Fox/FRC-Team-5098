package frc.settings;

/**
 * The swerve wheel settings.
 * <p>
 * This is used to store the settings for each swerve wheel.
 * </p>
 */
public class SwerveWheelSettings {

    /**
     * The drive motor ID.
     */
    private int driveID;

    /**
     * The turn motor ID.
     */
    private int turnID;

    /**
     * The encoder ID.
     */
    private int encoderID;

    public enum Location {
        /**
         * Front of the robot
         */
        front,

        /**
         * Back of the robot
         */
        back
    }

    /**
     * The location of the swerve wheel.
     */
    private Location location;

    /**
     * The angle of the swerve wheel to be in during defensive mode.
     */
    private double defensiveAngle;

    /**
     * The location of the swerve wheel in relation to the center of the robot.
     * This is the X position.
     */
    private double posX;

    /**
     * The location of the swerve wheel in relation to the center of the robot.
     * This is the Y position.
     */
    private double posY;

    // Accessor Methods //

    /**
     * Get the drive motor ID.
     *
     * @return The drive motor ID.
     */
    public int getDriveID() {
        return driveID;
    }

    /**
     * Set the drive motor ID.
     *
     * @param id  The drive motor ID.
     */
    public void setDriveID(final int id) {
        driveID = id;
    }

    /**
     * Get the turn motor ID.
     *
     * @return The turn motor ID.
     */
    public int getTurnID() {
        return turnID;
    }

    /**
     * Set the turn motor ID.
     *
     * @param id  The turn motor ID.
     */
    public void setTurnID(final int id) {
        turnID = id;
    }

    /**
     * Get the encoder ID.
     *
     * @return The encoder ID.
     */
    public int getEncoderID() {
        return encoderID;
    }

    /**
     * Set the encoder ID.
     *
     * @param id  The encoder ID.
     */
    public void setEncoderID(final int id) {
        encoderID = id;
    }

    /**
     * Get the location of the swerve wheel.
     *
     * @return The location of the swerve wheel.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set the location of the swerve wheel.
     *
     * @param location  The location of the swerve wheel.
     */
    public void setLocation(final Location loc) {
        location = loc;
    }

    /**
     * Get the angle of the swerve wheel to be in during defensive mode.
     *
     * @return The angle of the swerve wheel to be in during defensive mode.
     */
    public double getDefensiveAngle() {
        return defensiveAngle;
    }

    /**
     * Set the angle of the swerve wheel to be in during defensive mode.
     *
     * @param angle  The angle of the swerve wheel
     */
    public void setDefensiveAngle(final double angle) {
        defensiveAngle = angle;
    }

    /**
     * Get the location of the swerve wheel.
     *
     * @return The location of the swerve wheel on the X axis.
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Set the location of the swerve wheel.
     *
     * @param x  The location of the swerve wheel on the X axis.
     */
    public void setPosX(final double x) {
        posX = x;
    }

    /**
     * Get the location of the swerve wheel.
     *
     * @return The location of the swerve wheel on the Y axis.
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Set the location of the swerve wheel.
     *
     * @param y  The location of the swerve wheel on the Y axis.
     */
    public void setPosY(final double y) {
        posY = y;
    }
}
