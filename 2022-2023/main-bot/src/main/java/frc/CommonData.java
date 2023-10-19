package frc;

import frc.components.Claw;

/*
 * 'public': This class can be accessed outside of this folder.
 * 'class': This can contain variables and functionality.
 * 'CommonData': The name of this class. It is treated as a TYPE.
 */
public class CommonData
{
    /*
     * 'public': This variable can be accessed outside this class.
     * 'static': This variable is the same across the entire class; it is not unique to individual intances, or
     *           objects, of this class.
     * 'Claw.State': The type of data this variable is.
     * 'ClawState': The name of this variable.
     */
    public static Claw.State ClawState;

    public static double desiredTurn;

    public static double forwardSpeed;
    public static double sideSpeed;

    public static boolean balance;

    public static boolean battenDownTheHatches;

    public static boolean calibrate;

    public static double shoulderPower;
    public static double elbowPower;

    public static double shoulderAngle;
    public static double elbowAngle;

    public static double desiredShoulderAngle;
    public static double desiredElbowAngle;

    public static double robotPitch;
    public static double robotRoll;

    // Autonomous Varaibles

    public static int counter;
}
