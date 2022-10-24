package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;

public class WaitAction implements Action {

    private final double desiredWaitTime;
    private Timer timer;

    private boolean hasRunAtLeastOnce = false;

    public WaitAction(double desiredWaitTimeSeconds)
    {
        desiredWaitTime = desiredWaitTimeSeconds;
    }

    @Override
    public boolean execute() {
        if(!hasRunAtLeastOnce)
        {
            timer = new Timer();

            hasRunAtLeastOnce = true;
            timer.start();
        }

        return timer.get() >= desiredWaitTime;
    }
}