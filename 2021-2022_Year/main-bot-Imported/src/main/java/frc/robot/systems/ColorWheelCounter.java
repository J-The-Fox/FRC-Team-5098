package frc.robot.systems;

//import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public class ColorWheelCounter {

    private final int kWheelSegments = 8;

    private int numberOfChanges = 0;
    private Color lastColor = null;
    //private final ColorMatch colorMatcher;

    //public ColorWheelCounter(ColorMatch colorMatcher) {
    //    this.colorMatcher = colorMatcher;
    //}

    public void observeColor(Color currentColor) {
    //    var matchedColor = colorMatcher.matchClosestColor(currentColor).color;

    //    if(matchedColor != lastColor)
        {
    //        lastColor = matchedColor;
            ++numberOfChanges;
        }
    }

    public double discRotations() {
        return numberOfChanges / kWheelSegments;
    }
}