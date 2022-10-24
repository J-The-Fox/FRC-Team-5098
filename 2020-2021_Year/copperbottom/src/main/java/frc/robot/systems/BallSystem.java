package frc.robot.systems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.Conveyor;
import frc.robot.components.Intake;
import frc.robot.components.Launcher;
import frc.robot.components.Turret;
import frc.robot.datum.DatumSource;

public class BallSystem implements System
{
    public DatumSource<Double> setTurretTurn;
    public DatumSource<Double> setLauncherSpeed;
    public DatumSource<Boolean> launcherActive;
    public DatumSource<Conveyor.Direction> conveyorDirection;
    public DatumSource<Intake.Direction> intakeDirection;
    public DatumSource<Intake.State> intakeState;
    public DatumSource<Boolean> centerTurret;

    public DatumSource<Boolean> autoConveyor;
    public DatumSource<Boolean> autoTurret;

    private final Turret turret;
    private final Launcher launcher;
    private final Conveyor conveyor;
    private final Intake intake;

    private Timer timer = null;

    public BallSystem(Turret turret, Launcher launcher, Conveyor conveyor, Intake intake)
    {
        this.turret = turret;
        this.launcher = launcher;
        this.conveyor = conveyor;
        this.intake = intake;
    }

    @Override
    public void execute()
    {
        SmartDashboard.putNumber("Turret System/Speed", Math.abs(setLauncherSpeed.get()) * 100);
        SmartDashboard.putBoolean("Conveyor System/Ball", intake.ballDetected());
        SmartDashboard.putBoolean("Turret System/Ball", launcher.ballDetected());
        SmartDashboard.putBoolean("Turret System/Centered", turret.isCentered());
        SmartDashboard.putNumber("Turret System/Encoder", turret.getPosition());

        launcher.fire(launcherActive.get() ? setLauncherSpeed.get() : 0);

        boolean conveyorOverridden = false;

        if(centerTurret.get())
        {
            timer = null;
            if(turret.isCentered())
            {
                SmartDashboard.putBoolean("Turret System/Center", false);
            }
            else
            {
                turret.center();
            }
        }
        else if(autoTurret.get())
        {
            if(launcherActive.get())
            {
                conveyorOverridden = true;
                if((timer == null) && launcher.ballDetected())
                {
                    launcher.fire(0.0);
                    conveyor.set(Conveyor.Direction.Out);
                }
                else
                {
                    if(timer == null)
                    {
                        timer = new Timer();
                        timer.start();
                    }

                    launcher.fire(setLauncherSpeed.get());
                    conveyor.set(timer.get() > 1 ? Conveyor.Direction.In : Conveyor.Direction.Off);
                }
            }
            else
            {
                timer = null;
                launcher.fire(0.0);
                if(Math.abs(setTurretTurn.get()) > 0.75)
                {
                    turret.turn(setTurretTurn.get() > 0 ? 0.25 : -0.25);
                }
                else
                {
                    turret.track();
                }
            }
        }
        else
        {
            timer = null;
            launcher.fire(launcherActive.get() ? setLauncherSpeed.get() : 0.0);
            turret.turn(setTurretTurn.get() > 0.75 ? 0.25 : setTurretTurn.get() < -0.75 ? -0.25 : 0.0);
        }

        if(!conveyorOverridden)
        {
            if(autoConveyor.get() && intake.ballDetected())
            {
                conveyor.set(Conveyor.Direction.In);
                intake.set(Intake.Direction.In);
            }
            else
            {
              conveyor.set(conveyorDirection.get());
              intake.set(intakeDirection.get());
            }
        }
        intake.set(intakeState.get());
    }
}