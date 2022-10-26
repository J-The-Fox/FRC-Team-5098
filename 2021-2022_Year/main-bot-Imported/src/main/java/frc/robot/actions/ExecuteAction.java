package frc.robot.actions;

import java.util.List;

public class ExecuteAction implements Action {
    List<List<Action>> LLA;

    @Override
	public boolean execute() {
        for (List<Action> list : LLA) {
            for (Action action : list) {
                if(action.execute() == true){
                    list.remove(action);
                }        
            }
            if(list.isEmpty()){
                LLA.remove(list);
            }
        }
        if(LLA.isEmpty()){
            return true;
        }
        return false;
    }
}
