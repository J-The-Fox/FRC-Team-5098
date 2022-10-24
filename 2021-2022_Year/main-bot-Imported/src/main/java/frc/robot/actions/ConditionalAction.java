package frc.robot.actions;

import frc.robot.datum.DatumSource;

public class ConditionalAction implements Action {
    public Action a1;
    public Action a2;
    public DatumSource<Boolean> ds;

    @Override
	public boolean execute() {
        if(ds.get() == true){
            return a1.execute();
        }
        else{
            return a2.execute();
        }
    }
}
