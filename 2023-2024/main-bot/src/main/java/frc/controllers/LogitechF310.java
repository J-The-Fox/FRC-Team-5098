package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

import java.lang.Math;

/**
* This class is used to create an LogitechF310 controller object.
* <p>
* This class uses the {@code Joystick} class from the wpilibj library.
* </p>
* <p>
* This uses a deadband that can be set if needed. This helps control drift.
* This value can be in a range of 0 to 1. The higher the value, the more the
* stick has to move to register. This value is set in the constructor and is
* set alongside the controller ID. As a note: <i> The deadband is only applied
* to the joystick axes. </i>
* </p>
*
* @see PS4
* @see Xbox
*
* @see edu.wpi.first.wpilibj.Joystick
*/
public class LogitechF310 {

    private Joystick controller;
    private double deadBand;

    public LogitechF310(final int ID, final double deadBandValue) {
        controller = new Joystick(ID);
        deadBand = deadBandValue;
    }

    // Deadband Method //

    /**
    * Method for setting the deadband of the controller on the X and Y axes.
    */
    private double deadBand(double value) {
        return Math.abs(value) < deadBand ? 0 : value;
    }

    // Joystick Methods //

    /**
    * Method for returning the X value of the left joystick.
    * @return  X value of the left joystick
    *
    * @see LogitechF310#getRightX
    * @see LogitechF310#getRightY
    * @see LogitechF310#getLeftY
    */
    public double getLeftX() {
        return deadBand(controller.getRawAxis(0));
    }

    /**
     * Method for returning the Y value of the left joystick.
     * @return  Y value of the left joystick
     * 
     * @see LogitechF310#getRightX
     * @see LogitechF310#getRightY
     * @see LogitechF310#getLeftX
     */
    public double getLeftY() {
        return deadBand(controller.getRawAxis(1));
    }

    /**
     * Method for returning the X value of the right joystick.
     * @return  X value of the right joystick
     * 
     * @see LogitechF310#getRightY
     * @see LogitechF310#getLeftX
     * @see LogitechF310#getLeftY
     */
    public double getRightX() {
        return deadBand(controller.getRawAxis(4));
    }

    /**
     * Method for returning the Y value of the right joystick.
     * @return  Y value of the right joystick
     * 
     * @see LogitechF310#getRightX
     * @see LogitechF310#getLeftX
     * @see LogitechF310#getLeftY
     */
    public double getRightY() {
        return deadBand(controller.getRawAxis(5));
    }

    // Trigger Methods //

    /**
     * Method for returning the value of the left trigger.
     * @return  Value of the left trigger
     * 
     * @see LogitechF310#getRightTrigger
     */
    public double getLeftTrigger() {
        return deadBand(controller.getRawAxis(2));
    }

    /**
     * Method for returning the value of the right trigger.
     * @return  Value of the right trigger
     * 
     * @see LogitechF310#getLeftTrigger
     */
    public double getRightTrigger() {
        return deadBand(controller.getRawAxis(3));
    }

    // Button Methods //

    /**
     * Method for returning the value of the A button.
     * @return  Value of the A button
     * 
     * @see LogitechF310#getBButton
     * @see LogitechF310#getXButton
     * @see LogitechF310#getYButton
     */
    public boolean getAButton() {
        return controller.getRawButton(1);
    }

    /**
     * Method for returning the value of the B button.
     * @return  Value of the B button
     * 
     * @see LogitechF310#getAButton
     * @see LogitechF310#getXButton
     * @see LogitechF310#getYButton
     */
    public boolean getBButton() {
        return controller.getRawButton(2);
    }

    /**
     * Method for returning the value of the X button.
     * @return  Value of the X button
     * 
     * @see LogitechF310#getAButton
     * @see LogitechF310#getBButton
     * @see LogitechF310#getYButton
     */
    public boolean getXButton() {
        return controller.getRawButton(3);
    }

    /**
     * Method for returning the value of the Y button.
     * @return  Value of the Y button
     * 
     * @see LogitechF310#getAButton
     * @see LogitechF310#getBButton
     * @see LogitechF310#getXButton
     */
    public boolean getYButton() {
        return controller.getRawButton(4);
    }

    /**
     * Method for returning the value of the back button.
     * @return  Value of the back button
     * 
     * @see LogitechF310#getStartButton
     */
    public boolean getBackButton() {
        return controller.getRawButton(7);
    }

    /**
     * Method for returning the value of the start button.
     * @return  Value of the start button
     * 
     * @see LogitechF310#getBackButton
     */
    public boolean getStartButton() {
        return controller.getRawButton(8);
    }

    /**
     * Method for returning the value of the left bumper.
     * @return  Value of the left bumper
     * 
     * @see LogitechF310#getRightBumper
     */
    public boolean getLeftBumper() {
        return controller.getRawButton(5);
    }

    /**
     * Method for returning the value of the right bumper.
     * @return  Value of the right bumper
     * 
     * @see LogitechF310#getLeftBumper
     */
    public boolean getRightBumper() {
        return controller.getRawButton(6);
    }

    // D-Pad Methods //

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

    /**
    * Method for returning the value of the D-Pad.
    * @return  value of the D-Pad
    * 
    * @see LogitechF310.dPad
    */
    public dPad getDPad() {
        switch (controller.getPOV()) {
            case 0:
                return dPad.up;
            case 45:
                return dPad.upright;
            case 90:
                return dPad.right;
            case 135:
                return dPad.downright;
            case 180:
                return dPad.down;
            case 225:
                return dPad.downleft;
            case 270:
                return dPad.left;
            case 315:
                return dPad.upleft;
            case 360:
                return dPad.up;
            default:
                return dPad.none;
        }
    }

    // This controller does not have rumble functionality :(
}
