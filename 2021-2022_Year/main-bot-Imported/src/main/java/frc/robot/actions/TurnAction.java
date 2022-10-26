package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.components.PositionTracker;
import frc.robot.datum.DatumSink;

public class TurnAction implements Action {
    private final PositionTracker positionTracker;
    private final double desiredAngle;

    private final DatumSink<Double> turn;

    private boolean hasRunOnce = false;
    Timer timer = null;

    public TurnAction(PositionTracker posTracker, double desiredAngle, DatumSink<Double> turn)
    {
        positionTracker = posTracker;
        this.desiredAngle = desiredAngle;
        this.turn = turn;
    }

    @Override
    public boolean execute()
    {
        if(!hasRunOnce)
        {
            positionTracker.reset();

            hasRunOnce = true;
        }

        double headingError = desiredAngle - positionTracker.getHeading();
        java.lang.System.out.println("Heading Error: " + headingError);
        if(Math.abs(headingError) < 0.5)
        {
            if(timer == null)
            {
                timer = new Timer();
                timer.start();
            }
            else if(timer.get() >= 0.5)
            {
                turn.set(0.0);
                return true;
            }
        }
        else
        {
            timer = null;
        }

        headingError = 1 / (1 + Math.pow(Math.E, -headingError));
        headingError = headingError * 0.70;

        if(headingError > 0)
        {
            turn.set(headingError);
        }
        else
        {
            turn.set(headingError);
        }
        return false;
    }
}
