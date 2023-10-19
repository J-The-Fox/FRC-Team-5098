package frc.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.CommonData;
import frc.robot.Utility;
import frc.settings.ArmSettings;

public class Arm implements IComponent
{
    public enum Position {
        kTopShelf(-85465, 99934),
        kMiddleShelfOrHumanPlayer(-30156, 152244),
        kGroundPickup(10411, 26723),
        kHome(-32405, 941),
        kHumanPlayerStation(-55821, 45126);

        Position(double shoulderPos, double elbowPos)
        {
            this.shoulderPos = shoulderPos;
            this.elbowPos    = elbowPos;
        }

        public double shoulderPos;
        public double elbowPos;
    }

    public Arm(ArmSettings settings)
    {
        shoulderMotor = new TalonFX(settings.shoulderMotorID);
        elbowMotor    = new TalonFX(settings.elbowMotorID);
    }

    TalonFX shoulderMotor;
    TalonFX elbowMotor;

    public void moveToPOS()
    {
        CommonData.shoulderAngle = shoulderMotor.getSelectedSensorPosition();
        CommonData.elbowAngle    = elbowMotor.getSelectedSensorPosition();

        var actualShoulderTarget = Utility.clamp(CommonData.desiredShoulderAngle, -91250, 52915);
        var actualElbowTarget    = Utility.clamp(CommonData.desiredElbowAngle, -645, 189514);

        shoulderMotor.set(ControlMode.Position, actualShoulderTarget);
        elbowMotor.set(ControlMode.Position, actualElbowTarget);
    }

    public void update()
    {
        moveToPOS();
    }
}