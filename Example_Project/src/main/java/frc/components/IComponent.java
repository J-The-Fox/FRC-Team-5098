package frc.components;

public interface IComponent {

    /**
     * This is the interface for all components in this directory.
     * It contains the update method that is called every 20 milliseconds during the robot's operation.
     * This method is used to update the component with new data.
     * 
     * It is called in the Robot.java file in the periodic() method.
     */

    public void update();
}