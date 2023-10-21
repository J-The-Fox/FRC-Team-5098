package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;

public class LogitechF310 {

    // Imported From 2021-2022 code
    // Edited to match new controllers

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

    private Joystick joystick;

    // Set a deadband for the controller as our particular controller has some drift
    public double deadBand(double value)
    {
        return Math.abs(value) < 0.2 ? 0 : value;
    }

    public LogitechF310(int id)
    {
        joystick = new Joystick(id);
    }

    public boolean getA()
    {
        return joystick.getRawButton(kIdButtonA);
    }

    public boolean getB()
    {
        return joystick.getRawButton(kIdButtonB);
    }

    public boolean getX()
    {
        return joystick.getRawButton(kIdButtonX);
    }

    public boolean getY()
    {
        return joystick.getRawButton(kIdButtonY);
    }

    public boolean getLeftBumper()
    {
        return joystick.getRawButton(kIdButtonBumperLeft);
    }

    public boolean getRightBumper()
    {
        return joystick.getRawButton(kIdButtonBumperRight);
    }

    public double getLeftStickX()
    {
        return deadBand(joystick.getRawAxis(kIdAxisleftX));
    }

    public double getLeftStickY()
    {
        return deadBand(joystick.getRawAxis(kIdAxisleftY));
    }

    public double getRightStickX()
    {
        return deadBand(joystick.getRawAxis(kIdAxisrightX));
    }

    public double getRightStickY()
    {
        return deadBand(joystick.getRawAxis(kIdAxisrightY));
    }

    public double getLeftTrigger()
    {
        return joystick.getRawAxis(kIdlefttrigger);
    }

    public double getRightTrigger()
    {
        return joystick.getRawAxis(kIdrighttrigger);
    }

    // Swapped dPad to match the other controllers

    public enum dPad {
        up,
        down,
        left,
        right,
        upright,
        upleft,
        downright,
        downleft,
        none
    }

    public dPad getPad()
    {
        switch (joystick.getPOV())
        {
        case 0:
            return dPad.up;

        case 360:
            return dPad.up;

        case 90:
            return dPad.right;

        case 180:
            return dPad.down;

        case 270:
            return dPad.left;

        case 45:
            return dPad.upright;

        case 135:
            return dPad.downright;

        case 225:
            return dPad.downleft;

        case 315:
            return dPad.upleft;

        default:
            return dPad.none;
        }

        // No rumble support :(
    }
}