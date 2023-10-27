package frc.components.autonomous;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import frc.CommonData;
import frc.state_machine.State;

public class DriveState3 extends State {
    NetworkTable      table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tid   = table.getEntry("tid");
    NetworkTableEntry tv    = table.getEntry("tv");
    Timer             timer;
    Timer             timer2;

    @Override
    public void onEnter() {
        System.out.println("Drive State 3 entered");
    }
    @Override
    public void onExit() {
        System.out.println("Drive State 3 exited");
    }

    @Override
    public boolean run() {
        CommonData.forwardSpeed = -0.35;
        CommonData.desiredTurn  = 0;

        if (CommonData.robotPitch > 10 || CommonData.robotRoll > 10) {
            if (timer2 == null) {
                System.out.println("Pitch: " + CommonData.robotPitch + " Roll: " + CommonData.robotRoll);
                timer2 = new Timer();
                timer2.start();
            }
            if (timer2.hasElapsed(1)) {
                System.out.println("Tilted!");
                return true;
            }
        }

        if (timer == null) {
            timer = new Timer();
            timer.start();
        }
        else if (timer.hasElapsed(3)) {
            return true;
        }

        return false;
    }
}
