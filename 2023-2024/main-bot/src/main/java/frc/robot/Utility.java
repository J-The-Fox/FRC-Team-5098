package frc.robot;

public class Utility
{

    // The Utility.printLn method is used to print a message to the console every 50 times it is called
    // This is to slow down the messages as it overwhlems the roboRIO and causes the console to lag
    static int printCounter = 0;

    public static void printLn(String messageString)
    {
        if (printCounter == 50)
        {
            System.out.println(messageString);
            printCounter = 0;
        }
        else
        {
            printCounter++;
        }
    }

    // The Utility.clamp method is used to clamp a value between a minimum and maximum value
    // If the value is less than the minimum, the minimum is returned
    // If the value is greater than the maximum, the maximum is returned
    public static double clamp(double value, double min, double max)
    {
        /**
        * This is a ternary operator
        * It is a short hand if statement
        * It is the same as:
        *
        * if (value < min)
        * {
        *     return min;
        * }
        * else if (value > max)
        * {
        *     return max;
        * }
        * else
        * {
        *     return value;
        * }
        */

        return value < min ? min : value > max ? max : value;
    }

    // The Utility.snapToEdge method is used to snap a value to the minimum or maximum value if it is within a threshold
    // Although similar to the Utility.clamp method, this method is used to snap a value to the minimum or maximum value if it is within a threshold
    public static double snapToEdge(double value, double min, double max, double threshold)
    {
        return value < -threshold ? min : value > threshold ? max : value;
    }
}