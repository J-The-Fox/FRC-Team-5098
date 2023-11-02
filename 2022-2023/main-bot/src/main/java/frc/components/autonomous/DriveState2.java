package frc.components.autonomous;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.CommonData;
import frc.robot.Utility;
import frc.state_machine.State;

public class DriveState2 extends State {

    NetworkTable      table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv    = table.getEntry("tv");
    NetworkTableEntry array = table.getEntry("camerapose_targetspace");

    @Override
    public void onEnter() {
        System.out.println("Drive State 2 entered");
    }
    @Override
    public void onExit() {
        System.out.println("Drive State 2 exited");
    }

    @Override
    public boolean run() {
        boolean found = tv.getDouble(0) == 1;

        double tx = 0;
        double tz = 0;
        double ry = 0;
        // CommonData.desiredHeading = 0;
        // CommonData.desiredPower   = 0;
        CommonData.desiredTurn = 0;
        if (found) {
            Double[] targetInfo = array.getDoubleArray(new Double[0]);

            tx = targetInfo[0];      // tx
            tz = targetInfo[2] + .5; // tz
            ry = targetInfo[4];      // ry

            tx = Utility.sigmoid(tx * 2) * 2 - 1;
            tz = Utility.clamp(tz, -1.0, 1.0);
            ry = Utility.sigmoid(ry / 10) * 2 - 1;
            Utility.printLn("TX: " + tx + " TZ: " + tz + " RY: " + ry);

            if ((Math.abs(tz) < 0.1) && (Math.abs(tx) < 0.15) && (Math.abs(ry) < 0.6)) {
                CommonData.counter++;
                return true;
            }
            else {
                // Drive Until Center And Lined Up With The April Tag
                double theta   = Math.atan2(tx, tz);
                double degrees = Math.toDegrees(theta) + 180;
                double power   = Math.sqrt(Math.pow(tx, 2) + Math.pow(tz, 2));

                if (tx == 0 && tz == 0) {
                    degrees = 0;
                }

                // CommonData.desiredHeading = degrees;
                // CommonData.desiredPower   = power;
                CommonData.desiredTurn = ry;
            }
        }
        return false;
    }
}
