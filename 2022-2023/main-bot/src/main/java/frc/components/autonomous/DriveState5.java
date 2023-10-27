package frc.components.autonomous;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.CommonData;
import frc.robot.Utility;
import frc.state_machine.State;

public class DriveState5 extends State {
    NetworkTable      table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv    = table.getEntry("tv");
    NetworkTableEntry tid   = table.getEntry("tid");
    NetworkTableEntry array = table.getEntry("camerapose_targetspace");

    @Override
    public void onEnter() {
        System.out.println("Drive State 5 entered");
    }
    @Override
    public void onExit() {
        System.out.println("Drive State 5 exited");
    }

    @Override
    public boolean run() {
        if (CommonData.robotPitch > 2 || CommonData.robotRoll > 2) {
            return true;
        }
        else {
            CommonData.forwardSpeed = -0.3;
            CommonData.desiredTurn  = 0;
            return false;
        }
    }
}