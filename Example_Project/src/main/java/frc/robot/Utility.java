package frc.robot;

import java.lang.Math;

public class Utility
/**
 * This is the Utility Class. It contains methods and functions that don't fit into one specific class.
 * This is where you should add any methods or functions that don't fit into one specific class.
 * 
 * The functions and methods in this class were created for the 2022 - 2023 season.
*/
{
    static int printCounter = 0;

    public static void printLn(String messageString)
    /** 
     * This is a method that prints a message to the console every 50 times it is called.
     * It is used to prevent the console from being flooded with messages as well as to prevent log messages being lost due to the connection speed
    */ 
    {
        if (printCounter == 50)
        {
            System.out.println(messageString);
            printCounter = 0;
        }
        else
        {
            printCounter++;
            // Increment the print counter by 1
        }
    }

    public static double sigmoid(double x)
    /**
     * This is a sigmoid functinon. It is used to smooth out values as they approach 1 and 0.
     * It resembles an S shape.
     */
    {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    public static double clamp(double value, double min, double max)
    /**
     * This is a clamp function. It is used to limit a value between a minimum and a maximum.
     * If the value is above the maximum, it will be set to the maximum.
     * If the value is below the minimum, it will be set to the minimum.
     */
    {
        return value < min ? min : value > max ? max
                                               : value;
    }

    public static double snapToEdge(double value, double min, double max, double threshold)
    {
        if (value < -threshold)
        {
            return min;
        }
        if (value > threshold)
        {
            return max;
        }
        return value;
    }
}