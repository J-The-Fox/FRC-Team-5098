package stingr;

public final class Utils
{
    public static double rescale(double oldMin, double oldMax, double newMin, double newMax, double oldVal)
    {
        final double oldMid = (oldMax - oldMin) / 2.0 + oldMin;
        if(oldVal > oldMid)
        {
            return oldVal * (oldMax / newMax);
        }
        else if(oldVal < oldMid)
        {
            return oldVal * (oldMin / newMin);
        }
        else
        {
            return oldVal;
        }
    }
}