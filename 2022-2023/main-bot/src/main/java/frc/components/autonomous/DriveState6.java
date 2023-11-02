package frc.components.autonomous;

import frc.CommonData;
import frc.state_machine.State;

public class DriveState6 extends State {
    @Override
    public void onEnter() {
        System.out.println("Drive State 6 entered");
    }
    @Override
    public void onExit() {
        System.out.println("Drive State 6 exited");
    }

    @Override
    public boolean run() {
        CommonData.balance = true;
        return false;
    }
}
