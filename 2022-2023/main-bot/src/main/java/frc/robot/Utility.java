package frc.robot;

import java.lang.Math;

public class Utility {
    static int printCounter = 0;

    public static void printLn(String messageString) {
        if (printCounter == 50) {
            System.out.println(messageString);
            printCounter = 0;
        }
        else {
            printCounter++;
        }
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    public static double clamp(double value, double min, double max) {
        return value < min ? min : value > max ? max
                                               : value;
    }

    public static double snapToEdge(double value, double min, double max, double threshold) {
        if (value < -threshold) {
            return min;
        }
        if (value > threshold) {
            return max;
        }
        return value;
    }
}