package frc;

import frc.components.Pneumatics;

/**
 * This is mainly for storing varaiables that are accessed by multiple classes that write and read to the same variable
 * This is useful for storing things like a position of a motor, the location of a sensor, or a constant value
 * This is also used as a means of organization
 */

//  import frc.components.Pneumatics.State;

public class CommonData {

    // Pneumatics states
    public static Pneumatics.State largeSolenoidState;
    public static Pneumatics.State smallSolenoidState;

    // Gyro data from the NavX2
    public static double robotPitch;
    public static double robotRoll;
    public static double robotYaw;

    // Encoder data from the Spark Max and CANCoder
    public static double canCoderPosition;
    public static double sparkMaxEncoderPosition;
}
