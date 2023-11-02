package frc.components;

// Imports //
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.CommonData;
import frc.settings.PneumaticsSettings;

public class Pneumatics implements IComponent{
    
    // Create an enum for the states of the solenoids
    public enum State {
        kOpen,
        kClosed,
    }

    // Create a new solenoid object
    private DoubleSolenoid m_solenoidLarge;
    private DoubleSolenoid m_solenoidSmall;

    public Pneumatics(PneumaticsSettings settings) {
        // Set the settings for the solenoids using the settings in PneumaticsSettings.java
        m_solenoidLarge = new DoubleSolenoid(settings.pneumaticsModuleID, PneumaticsModuleType.REVPH, settings.largeSolenoidForwardChannelID, settings.largeSolenoidReverseChannelID);
        m_solenoidSmall = new DoubleSolenoid(settings.pneumaticsModuleID, PneumaticsModuleType.REVPH, settings.smallSolenoidForwardChannelID, settings.smallSolenoidReverseChannelID);
    };

    public void update(){

        // Note: This assumes that the CommonData values are updated elsewhere

        // Update the solenoid state for the small solenoid
        switch (CommonData.smallSolenoidState)
        {
        case kOpen:
            m_solenoidSmall.set(Value.kForward);
            break;
        case kClosed:
            m_solenoidSmall.set(Value.kReverse);
        }

        // Update the solenoid state for the large solenoid
        switch (CommonData.largeSolenoidState)
        {
        case kOpen:
            m_solenoidLarge.set(Value.kForward);
            break;
        case kClosed:
            m_solenoidLarge.set(Value.kReverse);
        }
    }
}

