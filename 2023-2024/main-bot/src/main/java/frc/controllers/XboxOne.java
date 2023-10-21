package frc.controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;

public class XboxOne {

    /**
    * The XboxOne and Xbox360 classes may be merged in the future into just a XboxController class
    * Since they are both almost the exact same besides the deadband
    */

    public XboxOne(int ID)
    {
        joystick = new Joystick(ID);
    }

    private Joystick joystick;

    public double deadBand(double value)
    {
        return Math.abs(value) < 0 ? 0 : value; // Set deadband to 0 as this controller doesn't have drift
    }

    public double getLeftStickX()
    {
        return deadBand(joystick.getRawAxis(0));
    }

    public double getLeftStickY()
    {
        return deadBand(joystick.getRawAxis(1));
    }

    public double getRightStickX()
    {
        return deadBand(joystick.getRawAxis(4));
    }

    public double getRightStickY()
    {
        return deadBand(joystick.getRawAxis(5));
    }

    public boolean getA()
    {
        return joystick.getRawButton(1);
    }

    public boolean getB()
    {
        return joystick.getRawButton(2);
    }

    public boolean getX()
    {
        return joystick.getRawButton(3);
    }

    public boolean getY()
    {
        return joystick.getRawButton(4);
    }

    public boolean getLB()
    {
        return joystick.getRawButton(5);
    }

    public boolean getRB()
    {
        return joystick.getRawButton(6);
    }

    public boolean getBack()
    {
        return joystick.getRawButton(7);
    }

    public boolean getStart()
    {
        return joystick.getRawButton(8);
    }

    public boolean getLeftStick()
    {
        return joystick.getRawButton(9);
    }

    public boolean getRightStick()
    {
        return joystick.getRawButton(10);
    }

    public double getLeftTrigger()
    {
        return joystick.getRawAxis(2);
    }

    public double getRightTrigger()
    {
        return joystick.getRawAxis(3);
    }

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
    }

    public void rumbleHigh()
    {
        joystick.setRumble(RumbleType.kRightRumble, 1);
    }

    public void rumbleLow()
    {
        joystick.setRumble(RumbleType.kLeftRumble, 1);
    }

    public void letsGetReadyToRumble()
    {
        joystick.setRumble(RumbleType.kBothRumble, 1);
    }

    public void rumbleOff()
    {
        joystick.setRumble(RumbleType.kBothRumble, 0);
    }
}