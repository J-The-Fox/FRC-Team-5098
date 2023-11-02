package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;

public class LogitechF310 {
    // Imported From Code/2022/stingr/main-bot

    private final int kIdButtonA           = 1;
    private final int kIdButtonB           = 2;
    private final int kIdButtonX           = 3;
    private final int kIdButtonY           = 4;
    private final int kIdButtonBumperLeft  = 5;
    private final int kIdButtonBumperRight = 6;
    private final int kIdAxisleftX         = 0;
    private final int kIdAxisleftY         = 1;
    private final int kIdAxisrightX        = 4;
    private final int kIdAxisrightY        = 5;
    private final int kIdlefttrigger       = 2;
    private final int kIdrighttrigger      = 3;
    private final int kIdDpad              = 0;

    // ...

    private Joystick controller;

    public double deadBand(double value) {
        return Math.abs(value) < 0.2 ? 0 : value;
    }

    public LogitechF310(int id) {
        controller = new Joystick(id);
    }

    public boolean getA() {
        return controller.getRawButton(kIdButtonA);
    }

    public boolean getB() {
        return controller.getRawButton(kIdButtonB);
    }

    public boolean getX() {
        return controller.getRawButton(kIdButtonX);
    }

    public boolean getY() {
        return controller.getRawButton(kIdButtonY);
    }

    public boolean getLeftBumper() {
        return controller.getRawButton(kIdButtonBumperLeft);
    }

    public boolean getRightBumper() {
        return controller.getRawButton(kIdButtonBumperRight);
    }

    public double getLeftStickX() {
        return deadBand(controller.getRawAxis(kIdAxisleftX));
    }

    public double getLeftStickY() {
        return deadBand(controller.getRawAxis(kIdAxisleftY));
    }

    public double getRightStickX() {
        return deadBand(controller.getRawAxis(kIdAxisrightX));
    }

    public double getRightStickY() {
        return deadBand(controller.getRawAxis(kIdAxisrightY));
    }

    public double getLeftTrigger() {
        return controller.getRawAxis(kIdlefttrigger);
    }

    public double getRightTrigger() {
        return controller.getRawAxis(kIdrighttrigger);
    }

    public boolean getDUp() {
        int degrees = controller.getPOV(kIdDpad);
        if (degrees == 315) {
            return true;
        }

        if (degrees == 0) {
            return true;
        }

        if (degrees == 45) {
            return true;
        }

        return false;
    }

    public boolean getDRight() {
        int degrees = controller.getPOV(kIdDpad);
        if (degrees == 45) {
            return true;
        }

        if (degrees == 90) {
            return true;
        }

        if (degrees == 135) {
            return true;
        }

        return false;
    }

    public boolean getDDown() {
        int degrees = controller.getPOV(kIdDpad);
        if (degrees == 135) {
            return true;
        }

        if (degrees == 180) {
            return true;
        }

        if (degrees == 225) {
            return true;
        }

        return false;
    }

    public boolean getDLeft() {
        int degrees = controller.getPOV(kIdDpad);
        if (degrees == 225) {
            return true;
        }

        if (degrees == 270) {
            return true;
        }

        if (degrees == 315) {
            return true;
        }

        return false;
    }
}
