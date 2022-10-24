package frc.robot.systems;

import java.util.Vector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.components.PositionTracker;
import frc.robot.datum.DatumSource;

public class ArcadeDrive implements System {
    public DatumSource<Double> power;
    public DatumSource<Double> turn;
    public DatumSource<Boolean> creep;
    
    private final Iterable<TalonFX> leftMotors;
    private final Iterable<TalonFX> rightMotors;

    private final PositionTracker posTracker;

    private final AHRS gyro;

    private boolean straightLatch = false;
    private double straightHeading = 0.0;

    public ArcadeDrive(int[] leftIds, int[] rightIds, AHRS gyro)
    {
        var vl = new Vector<TalonFX>();
        for(int id : leftIds)
        {
            vl.add(new TalonFX(id));
        }
        leftMotors = vl;

        var vr = new Vector<TalonFX>();
        for(int id : rightIds)
        {
            vr.add(new TalonFX(id));
        }
        rightMotors = vr;

        posTracker = new PositionTracker(vl.get(0), vr.get(0), gyro);

        this.gyro = gyro;
    }

    public PositionTracker getTracker()
    {
        return posTracker;
    }

    @Override
    public void execute()
    {
        final double creepFactor = creep.get() ? 6.0 : 1.0;

        final double desiredTurn = turn.get();
        final double desiredPower = power.get();

        final boolean shouldDrive = Math.abs(desiredPower) > 0.1;
        final boolean shouldTurn = Math.abs(desiredTurn) > 0.1;
        final double currentHeading = gyro.getYaw();

        double leftPower;
        double rightPower;
        //if(!shouldTurn && shouldDrive)
        //{
        //    if(!straightLatch)
        //    {
        //        straightHeading = currentHeading;
        //        straightLatch = true;
        //    }
//
        //    final double error = (straightHeading - currentHeading) * 0.05;
//
        //    leftPower = error - desiredPower;
        //    rightPower = error + desiredPower;
        //}
        //else
        {
            straightLatch = false;

            leftPower = desiredTurn - desiredPower;
            rightPower = desiredTurn + desiredPower;
        }

        final var adjustedLeftPower = leftPower / creepFactor;
        final var adjustedRightPower = rightPower / creepFactor;
        
        leftMotors.forEach(motor -> motor.set(ControlMode.PercentOutput, adjustedLeftPower));
        rightMotors.forEach(motor -> motor.set(ControlMode.PercentOutput, adjustedRightPower));

        SmartDashboard.putNumber("Gyro heading", currentHeading);
    }
}