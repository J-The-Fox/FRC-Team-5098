package frc;

/**
* Data used by other classes
* <p>
* This is used to store data that needs to be accessed by multiple classes
* </p>
*/
public class CommonData {

    // Swerve Drive Variables //

    /**
    * desiredTurn is the desired angle of the robot
    */
    private static double desiredTurn;

    /**
    * forwardSpeed is the desired forward and backward speed of the robot
    */
    private static double forwardSpeed;

    /**
    * sideSpeed is the desired left and right speed of the robot
    */
    private static double sideSpeed;

    /**
    * battenDownTheHatches is used to determine if the robot should be
      in defensive mode
    */
    private static boolean battenDownTheHatches;

    // NavX Variables //

    /**
    * calibrate is used to determine if the NavX should be calibrated
    */
    private static boolean calibrate;

    // Autonomous Varaibles //

    /**
    * counter is used in the state machine to determine what state the robot
      is in
    */
    private static int counter;

    // Constructor //
    // This is set to private as this is a utility class and
    // should not be instantiated
    private CommonData() {
        throw new UnsupportedOperationException(
            "This is a utility class and cannot be instantiated");
    }

    // Accesor Methods //


    /**
    * Method for returning desiredTurn
    * @return desiredTurn
    */
    public static double getDesiredTurn() {
        return desiredTurn;
    }

    /**
     * Method for setting the desiredTurn
     * @param value  value to set desiredTurn
     */
    public final static void setDesiredTurn(double value) {
        desiredTurn = value;
    }

    /**
    * Method for returning forwardSpeed
    * @return forwardSpeed
    */
    public static double getForwardSpeed() {
        return forwardSpeed;
    }

    /**
    * Method for setting forwardSpeed
    * @param value  value to set forwardSpeed
    */
    public final static void setForwardSpeed(double value) {
        forwardSpeed = value;
    }

    /**
    * Method for returning sideSpeed
    * @return sideSpeed
    */
    public static double getSideSpeed() {
        return sideSpeed;
    }

    /**
    * Method for setting sideSpeed
    * @param value  value to set sideSpeed
    */
    public final static void setSideSpeed(double value) {
        sideSpeed = value;
    }

    /**
    * Method for returning battenDownTheHatches
    * @return battenDownTheHatches
    */
    public static boolean getBattenDownTheHatches() {
        return battenDownTheHatches;
    }

    /**
    * Method for setting battenDownTheHatches
    * @param value  value to set battenDownTheHatches
    */
    public final static void setBattenDownTheHatches(boolean value) {
        battenDownTheHatches = value;
    }

    /**
    * Method for returning calibrate
    * @return calibrate
    */
    public static boolean getCalibrate() {
        return calibrate;
    }

    /**
    * Method for setting calibrate
    * @param value  value to set calibrate
    */
    public final static void setCalibrate(boolean value) {
        calibrate = value;
    }

    /**
    * Method for returning counter
    * @return counter
    */
    public static int getCounter() {
        return counter;
    }

    /**
    * Method for setting counter
    * @param value  value to set counter
    */
    public final static void setCounter(int value) {
        counter = value;
    }
}
