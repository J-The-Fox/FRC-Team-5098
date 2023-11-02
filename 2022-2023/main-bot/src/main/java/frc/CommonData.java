package frc;

import frc.components.Claw;

/*
* 'public': This class can be accessed outside of this folder.
* 'class': This can contain variables and functionality.
* 'CommonData': The name of this class. It is treated as a TYPE.
*/
public class CommonData  {
    /*
    * 'public': This variable can be accessed outside this class.
    * 'static': This variable is the same across the entire class; 
    *           it is not unique to individual intances, or
    *           objects, of this class.
    * 'Claw.State': The type of data this variable is.
    * 'ClawState': The name of this variable.
    */

    // Contructor to prevent instantiation of a utility class
    private CommonData() {
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated");
    }

    public static Claw.State clawState;
    /*
     * This keeps track of the state of claw
     * Either open or closed
     */

    public static double desiredTurn;
    /*
     * This is the desired turn value
     * It is used in the drive class
     */

    public static double forwardSpeed;
    /*
     * This is the desired forward speed
     * It's used to keep track of the speed
     * and set the speed in the drive class
     */

    public static double sideSpeed;
    /*
     * Side speed of the robot
     * This is used when the robot is strafing
     */

    public static boolean balance;
    /*
     * Set to true when the robot is balancing in autonomous
     */

    public static boolean battenDownTheHatches;
    /*
     * This is set to true whenever the robot is in defense mode
     * All wheels are set to an 'x' pattern so the robot can't be pushed
     */

    public static boolean calibrate;
    /*
     * Set to true when the robot is calibrating
     * Set to false otherwise
     */

    public static double shoulderPower;
    /*
     * This is the current power of the shoulder motor
     */
    public static double elbowPower;
    /*
     * This is the current power of the elbow motor
     */

    public static double shoulderAngle;
    /*
     * The current angle of the shoulder motor
     * This is in degrees
     */
    public static double elbowAngle;
    /*
     * The current angle of the elbow motor
     * This is in degrees
     */

    public static double desiredShoulderAngle;
    /* 
     * The desired angle of the shoulder motor
     * This is in degrees
     */
    public static double desiredElbowAngle;
    /*
     * The desired angle of the elbow motor
     * This is in degrees
     */

    public static double robotPitch;
    /*
     * Current pitch of the robot
     * Gathered from the NavX
     */
    public static double robotRoll;
    /*
     * Current roll of the robot
     * Gathered from the NavX
     */

    // Autonomous Varaibles
    public static int counter;
    /*
     * This is a counter that is used in the autonomous class
     * It is used to keep track of the current state of the state machine
     */
}
