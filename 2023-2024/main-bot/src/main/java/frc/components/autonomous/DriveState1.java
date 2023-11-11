package frc.components.autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.CommonData;
import frc.state_machine.State;

/**
 * The first drive state during autonomous mode
 * <p>
 * This state drives the robot forward at 35% speed for 3 seconds
 * </p>
 */
public final class DriveState1 extends State {

    /**
     * The forward speed of the robot
     */
    private static final double FORWARD_SPEED = 0.35;

    /**
     * The time the robot drives forward
     */
    private static final int DRIVE_TIME = 3;

    /**
     * Timer object
     */
    Timer timer;

    @Override
    public void onEnter() {
        // Mainly used for debugging
        System.out.println("Drive State 1 entered");
    }
    
    @Override
    public void onExit() {
        // Mainly used for debugging
        System.out.println("Drive State 1 exited");
    }

    @Override
    public boolean run() {

        // Set the values so it drives forward at a speed of 35% in reverse
        CommonData.setForwardSpeed(-FORWARD_SPEED);
        CommonData.setDesiredTurn(0);

        // Set a new timer if one doesn't exist already
        if (timer == null) {
            timer = new Timer();
            timer.start();
        // If the timer has elapses 3 seconds, exit the state
        } else if (timer.hasElapsed(DRIVE_TIME)) {
            return true;
        }

        return false;
    }
}
