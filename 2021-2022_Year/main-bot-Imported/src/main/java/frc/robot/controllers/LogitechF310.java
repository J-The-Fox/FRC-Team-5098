package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechF310
{
    private Joystick controller;

    private final int kAnalogLeftX = 0;
    private final int kAnalogLeftY = 1;
    private final int kAnalogLeftTrigger = 2;
    private final int kAnalogRightTrigger = 3;
    private final int kAnalogRightX = 4;
    private final int kAnalogRightY = 5;

    private final int kButtonA = 1;
    private final int kButtonB = 2;
    private final int kButtonX = 3;
    private final int kButtonY = 4;
    private final int kButtonBackLeft = 5;
    private final int kButtonBackRight = 6;
    private final int kButtonMenuBack = 7;
    private final int kButtonMenuStart = 8;
    private final int kButtonStickLeft = 9;
    private final int kButtonStickRight = 10;

    private final int kPOV = 0;

    public double getAnalogLeftX()
    {
        return controller.getRawAxis(kAnalogLeftX);
    }
    public double getAnalogLeftY()
    {
        return controller.getRawAxis(kAnalogLeftY);
    }
    public double getAnalogLeftTrigger()
    {
        return controller.getRawAxis(kAnalogLeftTrigger);
    }
    public double getAnalogRightTrigger()
    {
        return controller.getRawAxis(kAnalogRightTrigger);
    }
    public double getAnalogRightX()
    {
        return controller.getRawAxis(kAnalogRightX);
    }
    public double getAnalogRightY()
    {
        return controller.getRawAxis(kAnalogRightY);
    }

    public boolean getButtonA()
    {
        return controller.getRawButton(kButtonA);
    }

    public boolean getButtonB()
    {
        return controller.getRawButton(kButtonB);
    }

    public boolean getButtonX()
    {
        return controller.getRawButton(kButtonX);
    }

    public boolean getButtonY()
    {
        return controller.getRawButton(kButtonY);
    }

    public boolean getButtonBackLeft()
    {
        return controller.getRawButton(kButtonBackLeft);
    }

    public boolean getButtonBackRight()
    {
        return controller.getRawButton(kButtonBackRight);
    }

    public boolean getButtonMenuBack()
    {
        return controller.getRawButton(kButtonMenuBack);
    }

    public boolean getButtonMenuStart()
    {
        return controller.getRawButton(kButtonMenuStart);
    }

    public boolean getButtonStickLeft()
    {
        return controller.getRawButton(kButtonStickLeft);
    }

    public boolean getButtonStickRight()
    {
        return controller.getRawButton(kButtonStickRight);
    }

    public boolean getUp()
    {
        final int pov = controller.getPOV(kPOV);
        return (pov >= 315) || ((pov >= 0) && (pov <= 45));
    }

    public boolean getRight()
    {
        final int pov = controller.getPOV(kPOV);
        return (pov >= 45) || (pov <= 135);
    }

    public boolean getDown()
    {
        final int pov = controller.getPOV(kPOV);
        return (pov >= 135) || (pov <= 225);
    }

    public boolean getLeft()
    {
        final int pov = controller.getPOV(kPOV);
        return (pov >= 225) || (pov <= 315);
    }

    public LogitechF310(int controllerPort)
    {
        controller = new Joystick(controllerPort);
    }
}