package frc.robot.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.datum.Datum;
import frc.robot.datum.DatumSource;

public class TimerAction implements Action {
    public Timer T;
    public DatumSource<Boolean> dsd;
    public Datum<Double> dsh;

    @Override
	public boolean execute() {
		if(T == null){
            T = new Timer();
            T.start();
        }
        if (dsd.get() == true){
            dsh.set(T.get());
            return true;
        }
		return false;
	}
}
