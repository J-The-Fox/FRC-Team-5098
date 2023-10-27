package frc.components.autonomous;

import frc.CommonData;
import frc.state_machine.State;

public class ArmState3 extends State {
    private static final double targetShoulderAngle = -18610; // Placeholder
    private static final double targetElbowAngle    = -645;   // Placeholder

    private static final double shoulderThreshold = 500;
    private static final double elbowThreshold    = 500;

    private static final double shoulderPower = 0.2;
    private static final double elbowPower    = 0.2;

    @Override
    public void onEnter() {
        System.out.println("Arm State 3 entered");
    }

    @Override
    public void onExit() {
        System.out.println("Arm State 3 exited");
    }

    @Override
    public boolean run() {
        CommonData.desiredShoulderAngle = targetShoulderAngle;
        CommonData.desiredElbowAngle    = targetElbowAngle;

        final double shoulderError = targetShoulderAngle - CommonData.shoulderAngle;
        final double elbowError    = targetElbowAngle - CommonData.elbowAngle;

        if (Math.abs(elbowError) < 400 && Math.abs(shoulderError) < 400) {
            return true;
        }
        else {
            return false;
        }
    }
}
