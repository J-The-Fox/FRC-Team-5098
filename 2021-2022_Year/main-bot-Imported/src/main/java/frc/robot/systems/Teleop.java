package frc.robot.systems;

import frc.robot.controllers.LogitechAttack3;
import frc.robot.controllers.LogitechF310;
import frc.robot.datum.Datum;

public class Teleop implements System
{
    public final Datum<Double> driveSpeed = new Datum<Double>();
    public final Datum<Double> driveTurn = new Datum<Double>();
    public final Datum<Boolean> creep = new Datum<Boolean>();
    public final Datum<Double> launcherSpeed = new Datum<Double>();
    public final Datum<Boolean> launcherActivate = new Datum<Boolean>();
    public final Datum<Boolean> conveyorIn = new Datum<Boolean>();
    public final Datum<Boolean> conveyorOut = new Datum<Boolean>();
    public final Datum<Boolean> intakeIn = new Datum<Boolean>();
    public final Datum<Boolean> intakeOut = new Datum<Boolean>();
    public final Datum<Double> turretTurn = new Datum<Double>();
    public final Datum<Boolean> intakeUp = new Datum<Boolean>();
    public final Datum<Boolean> intakeDown = new Datum<Boolean>();

    private final LogitechF310 driveController;
    private final LogitechAttack3 auxController;

    private boolean enabled = false;

    public Teleop(int driveControllerPort, int auxControllerPort)
    {
        driveController = new LogitechF310(driveControllerPort);
        auxController = new LogitechAttack3(auxControllerPort);
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
        if(enabled)
        {
            driveSpeed.set(driveController.getAnalogLeftY());
            driveTurn.set(driveController.getAnalogRightX());
            creep.set(driveController.getAnalogRightTrigger() > 0.3);
            launcherSpeed.set((auxController.getSlider() - 1.0) / 2.0);
            launcherActivate.set(auxController.getButton6());
            conveyorIn.set(auxController.getButton11());
            conveyorOut.set(auxController.getButton10());
            intakeIn.set(driveController.getButtonBackLeft());
            intakeOut.set(driveController.getButtonBackRight());
            turretTurn.set(auxController.getX());
            intakeUp.set(driveController.getButtonY());
            intakeDown.set(driveController.getButtonA());
        }
    }
    
}