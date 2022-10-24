package frc.robot.datum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DatumDoubleDashboard implements DatumSource<Double>
{
    private final String key;

    public DatumDoubleDashboard(String key)
    {
        this.key = key;
    }

    @Override
    public Double get()
    {
        return SmartDashboard.getNumber(key, 0.0);
    }
}