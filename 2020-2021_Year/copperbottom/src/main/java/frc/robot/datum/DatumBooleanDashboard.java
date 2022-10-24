package frc.robot.datum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DatumBooleanDashboard implements DatumSource<Boolean>
{
    private final String key;

    public DatumBooleanDashboard(String key)
    {
        this.key = key;
    }

    @Override
    public Boolean get()
    {
        return SmartDashboard.getBoolean(key, false);
    }
}