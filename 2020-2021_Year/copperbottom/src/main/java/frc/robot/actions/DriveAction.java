package frc.robot.actions;

import frc.robot.components.PositionTracker;
import frc.robot.datum.DatumSink;

public class DriveAction implements Action {
    private final double desiredDistance;
    private final PositionTracker positionTracker;

    private final DatumSink<Double> drive;

    private boolean hasRunOnce = false;

    public DriveAction(PositionTracker posTracker, double desiredDistanceFeet, DatumSink<Double> driveSink)
    {
        positionTracker = posTracker;
        desiredDistance = desiredDistanceFeet * 12;
        drive = driveSink;
    }

    @Override
    public boolean execute()
    {
        if(!hasRunOnce)
        {
            positionTracker.reset();

            hasRunOnce = true;
        }

        if(desiredDistance > 0)
        {
            if(positionTracker.getDistanceInches() >= desiredDistance)
            {
                drive.set(0.0);

                return true;
            }
            else
            {
                drive.set(-1.0);

                return false;
            }
        }
        else if(positionTracker.getDistanceInches() <= desiredDistance)
        {
            drive.set(0.0);

            return true;
        }
        else
        {
            drive.set(1.0);
            
            return false;
        }
    }
}
