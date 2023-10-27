package frc.components.autonomous;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import frc.CommonData;
import frc.state_machine.State;

public class DriveState1 extends State {
    NetworkTable      table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv    = table.getEntry("tv");

    Timer timer;

    @Override
    public void onEnter() {
        System.out.println("Drive State 1 entered");
    }
    @Override
    public void onExit() {
        System.out.println("Drive State 1 exited");
    }

    @Override
    public boolean run() {
        CommonData.forwardSpeed = -0.1;

        boolean targetFound = tv.getDouble(0) == 1;
        if (targetFound) {
            if (timer == null) {
                timer = new Timer();
                timer.start();
            }
            else if (timer.hasElapsed(0.1)) {
                return true;
            }
        }
        else {
            timer = null;
        }
        return false;
    }
}
