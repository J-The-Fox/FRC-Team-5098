package frc.controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/**
* This class is used to create an Xbox controller object.
* <p>
* This class uses the {@code XboxController} class from the wpilibj library.
* </p>
* <p>
* This uses a deadband that can be set if needed. This helps control drift.
* This value can be in a range of 0 to 1. The higher the value, the more the
* stick has to move to register. This value is set in the constructor and is
* set alongside the controller ID. As a note: <i> The deadband is only applied
* to the joystick axes. </i>
* </p>
*
* @see LogitechF310
* @see PS4
*
* @see edu.wpi.first.wpilibj.XboxController
*/
public class Xbox {
    
    private XboxController controller;
    private double deadBand;

    public Xbox(final int ID, final double deadBandValue) {
        controller = new XboxController(ID);
        deadBand = deadBandValue;
    }

    // Deadband Method //

    // Although this isn't really needed for newer controllers,
    // it's still here just in case one might develop drift

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
    * @see Xbox#getRightX
    * @see Xbox#getRightY
    * @see Xbox#getLeftY
    *
    */
    public double getLeftX() {
        return deadBand(controller.getLeftX());
    }

    /**
    * Method for returning the Y value of the left joystick.
    * @return  Y value of the left joystick
    *
    * @see Xbox#getRightX
    * @see Xbox#getRightY
    * @see Xbox#getLeftX
    *
    */
    public double getLeftY() {
        return deadBand(controller.getLeftY());
    }

    /**
    * Method for returning the X value of the right joystick.
    * @return  X value of the right joystick
    *
    * @see Xbox#getRightY
    * @see Xbox#getLeftX
    * @see Xbox#getLeftY
    *
    */
    public double getRightX() {
        return deadBand(controller.getRightX());
    }

    /**
    * Method for returning the Y value of the right joystick.
    * @return  Y value of the right joystick
    *
    * @see Xbox#getRightX
    * @see Xbox#getLeftX
    * @see Xbox#getLeftY
    */
    public double getRightY() {
        return deadBand(controller.getRightY());
    }

    // Trigger Methods //

    /**
    * Method for returning the value of the left trigger.
    * @return  value of the left trigger
    *
    * @see Xbox#getRightTrigger
    */
    public double getLeftTrigger() {
        return controller.getLeftTriggerAxis();
    }

    /**
    * Method for returning the value of the right trigger.
    * @return  value of the right trigger
    *
    * @see Xbox#getLeftTrigger
    */
    public double getRightTrigger() {
        return controller.getRightTriggerAxis();
    }

    // Button Methods //

    /**
    * Method for returning the value of the A button.
    * @return  value of the A button
    *  
    * @see Xbox#getBButton
    * @see Xbox#getXButton
    * @see Xbox#getYButton
    */
    public boolean getAButton() {
        return controller.getAButton();
    }

    /**
    * Method for returning the value of the B button.
    * @return  value of the B button
    *  
    * @see Xbox#getAButton
    * @see Xbox#getXButton
    * @see Xbox#getYButton
    */
    public boolean getBButton() {
        return controller.getBButton();
    }

    /**
    * Method for returning the value of the X button.
    * @return  value of the X button
    *  
    * @see Xbox#getAButton
    * @see Xbox#getBButton
    * @see Xbox#getYButton
     */
    public boolean getXButton() {
        return controller.getXButton();
    }

    /**
    * Method for returning the value of the Y button.
    * @return  value of the Y button
    *  
    * @see Xbox#getAButton
    * @see Xbox#getBButton
    * @see Xbox#getXButton
    */
    public boolean getYButton() {
        return controller.getYButton();
    }

    /**
    * Method for returning the value of the start button.
    * @return  value of the start button
    *  
    * @see Xbox#getBackButton
    */
    public boolean getStartButton() {
        return controller.getStartButton();
    }

    /**
    * Method for returning the value of the back button.
    * @return  value of the back button
    *  
    * @see Xbox#getStartButton
    */
    public boolean getBackButton() {
        return controller.getBackButton();
    }

    /**
    * Method for returning the value of the left bumper.
    * @return  value of the left bumper
    *  
    * @see Xbox#getRightBumper
    */
    public boolean getLeftBumper() {
        return controller.getLeftBumper();
    }

    /**
    * Method for returning the value of the right bumper.
    * @return  value of the right bumper
    *  
    * @see Xbox#getLeftBumper
    */
    public boolean getRightBumper() {
        return controller.getRightBumper();
    }

    /**
    * Method for returning the value of the left stick button.
    * @return  value of the left stick button
    * 
    * @see Xbox#getRightStickButton
    */
    public boolean getLeftStickButton() {
        return controller.getLeftStickButton();
    }

    /**
    * Method for returning the value of the right stick button.
    * @return  value of the right stick button
    * 
    * @see Xbox#getLeftStickButton
    */
    public boolean getRightStickButton() {
        return controller.getRightStickButton();
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
    * @see Xbox.dPad
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

    // Rumble Methods //

    /**
    * Method for setting the rumble of the controller.
    * @param type   type of rumble to set
    * @param value  value to set the rumble to
    * 
    * @see edu.wpi.first.wpilibj.GenericHID.RumbleType
    */
    public void setRumble(RumbleType type, double value) {
        controller.setRumble(type, value);
    }
}
