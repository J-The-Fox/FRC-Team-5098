package frc.robot.systems;

import java.util.ArrayList;
import java.util.Timer;

import edu.wpi.first.math.Pair;
import frc.robot.datum.Datum;
import jdk.jfr.Timestamp;

public class MimicAuto implements System
{
    private ArrayList<Pair<Long, Pair<Double, Double>>> learnedPoints = new ArrayList<Pair<Long, Pair<Double, Double>>>();

    public final Datum<Double> driveSpeed = new Datum<Double>();
    public final Datum<Double> driveTurn = new Datum<Double>();

    private long milliOffset = java.lang.System.currentTimeMillis();

    private boolean enabled = false;

    public MimicAuto()
    {
    }

    public void learn(Double power, Double turn)
    {
        if(power == null || turn == null)
        {
            power = 0.0;
            turn = 0.0;
        }
        final var t = java.lang.System.currentTimeMillis() - milliOffset;
        learnedPoints.add(new Pair<Long, Pair<Double, Double>>(t, new Pair<Double, Double>(power, turn)));
    }

    public void reset()
    {
        learnedPoints.clear();
        milliOffset = java.lang.System.currentTimeMillis();
    }

    public void reset2()
    {
        milliOffset = java.lang.System.currentTimeMillis();
    }

    public void enable()
    {
        enabled = true;
    }

    public void disable()
    {
        enabled = false;
    }

    @Override
    public void execute()
    {
        final var timestamp = java.lang.System.currentTimeMillis() - milliOffset;
        if(enabled)
        {
            learnedPoints.removeIf(p -> p == null || p.getFirst() < timestamp);

            if(!learnedPoints.isEmpty())
            {
                final var power = learnedPoints.get(0).getSecond().getFirst();
                final var turn = learnedPoints.get(0).getSecond().getSecond();
                java.lang.System.out.println("Executing T = " + timestamp + " P = " + power + " Turn = " + turn);
                driveSpeed.set(power);
                driveTurn.set(turn);
                return;
            }

            java.lang.System.out.println("Executing T = " + timestamp + " ... list empty");
        }
        
        driveSpeed.set(0.0);
        driveTurn.set(0.0);
    }
}