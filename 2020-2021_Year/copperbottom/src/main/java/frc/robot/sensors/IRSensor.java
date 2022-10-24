package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class IRSensor
{
    private final DigitalInput di;

    public IRSensor(int channel)
    {
        di = new DigitalInput(channel);
    }

    public boolean get()
    {
        return di.get();
    }
}