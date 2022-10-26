package frc.robot.systems;

import java.util.Vector;

import frc.robot.actions.Action;
import frc.robot.actions.DriveAction;
import frc.robot.actions.TurnAction;
import frc.robot.actions.WaitAction;
import frc.robot.components.Conveyor;
import frc.robot.components.Intake;
import frc.robot.components.PositionTracker;
import frc.robot.datum.Datum;
import frc.robot.datum.DatumConstant;
import frc.robot.datum.DatumSource;

public class Auto implements System
{
    public DatumSource<Double> delay;

    public final Datum<Double> driveSpeed = new Datum<Double>();
    public final Datum<Double> driveTurn = new Datum<Double>();
    
    public final DatumConstant<Intake.State> intakeState = new DatumConstant<Intake.State>(Intake.State.Neutral);
    public final DatumConstant<Intake.Direction> intakeDirection = new DatumConstant<Intake.Direction>(Intake.Direction.Off);
    public final DatumConstant<Conveyor.Direction> conveyorDirection = new DatumConstant<Conveyor.Direction>(Conveyor.Direction.Off);

    public final Datum<Boolean> launcherActive = new Datum<Boolean>();
    public final DatumConstant<Double> launcherSpeed = new DatumConstant<Double>(1.0);
    public final DatumConstant<Double> turretTurn = new DatumConstant<Double>(0.0);
    public final Datum<Boolean> centerTurrent = new Datum<Boolean>();

    private final PositionTracker positionTracker;

    private final Vector<Action> actions = new Vector<Action>();

    private boolean enabled = false;

    public Auto(PositionTracker posTracker)
    {
        positionTracker = posTracker;

        driveSpeed.set(0.0);
        driveTurn.set(0.0);

        launcherActive.set(false);
        centerTurrent.set(false);
    }

    public void reset()
    {
        actions.clear();

        actions.add(new DriveAction(positionTracker, 6, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -15, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -5.5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, 5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, 5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -5, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -14.4, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, -4, driveSpeed));
        actions.add(new WaitAction(1));
        actions.add(new TurnAction(positionTracker, 90, driveTurn));
        actions.add(new WaitAction(1));
        actions.add(new DriveAction(positionTracker, 5, driveSpeed));
    }

    public void enable()
    {
        enabled = true;
    }
    public void disable()
    {
        enabled = false;
    }

    @Override
    public void execute()
    {
        if(enabled && !actions.isEmpty())
        {
            if(actions.get(0).execute())
            {
                actions.remove(0);
            }
        }
    }
}