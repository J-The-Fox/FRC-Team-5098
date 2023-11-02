package frc.components.autonomous;

import frc.CommonData;
// import frc.robot.Utility;
import frc.state_machine.State;

public class ArmState1 extends State {

    private static final double targetShoulderAngle = -75563;
    private static final double targetElbowAngle    = 109928;

    private static final double shoulderThreshold = 500;
    private static final double elbowThreshold    = 500;

    private static final double shoulderPower = 0.2;
    private static final double elbowPower    = 0.2;

    @Override
    public void onEnter() {
        System.out.println("Arm State 1 entered");
    }

    @Override
    public void onExit() {
        System.out.println("Arm State 1 exited");
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
