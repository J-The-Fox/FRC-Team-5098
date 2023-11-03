package frc;

public class CommonData {

    // Swerve Drive Variables
    private static double desiredTurn;
    private static double forwardSpeed;
    private static double sideSpeed;
    private static boolean battenDownTheHatches;

    // NavX Variables
    private static boolean calibrate;

    // Autonomous Varaibles
    private static int counter;

    // Accesor Methods // 
    public static double getDesiredTurn() {
        return desiredTurn;
    }

    public static void setDesiredTurn(double value) {
        desiredTurn = value;
    }

    public static double getForwardSpeed() {
        return forwardSpeed;
    }

    public static void setForwardSpeed(double value) {
        forwardSpeed = value;
    }

    public static double getSideSpeed() {
        return sideSpeed;
    }

    public static void setSideSpeed(double value) {
        sideSpeed = value;
    }

    public static boolean getBattenDownTheHatches() {
        return battenDownTheHatches;
    }

    public static void setBattenDownTheHatches(boolean value) {
        battenDownTheHatches = value;
    }

    public static boolean getCalibrate() {
        return calibrate;
    }

    public static void setCalibrate(boolean value) {
        calibrate = value;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int value) {
        counter = value;
    }
}
