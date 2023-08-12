package frc;

/**
 * This is mainly for storing varaiables that are accessed by multiple classes that write and read to the same variable
 * This is useful for storing things like a position of a motor, the location of a sensor, or a constant value
 * This is also used as a means of organization
 */

public class CommonData {

    // Gyro data from the NavX
    public static double robotPitch;
    public static double robotRoll;
    public static double robotYaw;
}
