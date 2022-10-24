package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechAttack3
{
    private final Joystick controller;

    private final int kXAxis = 0;
    private final int kYAxis = 1;
    private final int kSlider = 2;
    private final int kTrigger = 1;
    private final int kButton2 = 2;
    private final int kButton3 = 3;
    private final int kButton4 = 4;
    private final int kButton5 = 5;
    private final int kButton6 = 6;
    private final int kButton7 = 7;
    private final int kButton8 = 8;
    private final int kButton9 = 9;
    private final int kButton10 = 10;
    private final int kButton11 = 11;

    public double getX()
    {
        return controller.getRawAxis(kXAxis);
    }
    public double getY()
    {
        return controller.getRawAxis(kYAxis);
    }
    public double getSlider()
    {
        return controller.getRawAxis(kSlider);
    }
    public boolean getTrigger()
    {
        return controller.getRawButton(kTrigger);
    }
    public boolean getButton2()
    {
        return controller.getRawButton(kButton2);
    }
    public boolean getButton3()
    {
        return controller.getRawButton(kButton3);
    }
    public boolean getButton4()
    {
        return controller.getRawButton(kButton4);
    }
    public boolean getButton5()
    {
        return controller.getRawButton(kButton5);
    }
    public boolean getButton6()
    {
        return controller.getRawButton(kButton6);
    }
    public boolean getButton7()
    {
        return controller.getRawButton(kButton7);
    }
    public boolean getButton8()
    {
        return controller.getRawButton(kButton8);
    }
    public boolean getButton9()
    {
        return controller.getRawButton(kButton9);
    }
    public boolean getButton10()
    {
        return controller.getRawButton(kButton10);
    }
    public boolean getButton11()
    {
        return controller.getRawButton(kButton11);
    }

    public LogitechAttack3(int controllerPort)
    {
        controller = new Joystick(controllerPort);
    }
}