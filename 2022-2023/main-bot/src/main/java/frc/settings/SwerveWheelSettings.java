package frc.settings;

public class SwerveWheelSettings
{

    public int driveID;
    public int turnID;
    public int encoderID;

    public enum Location {
        front,
        back
    }

    public Location location;

    public double defensiveAngle;

    public double posX;
    public double posY;
}