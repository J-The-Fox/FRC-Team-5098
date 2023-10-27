package frc.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.CommonData;
import frc.settings.ClawSettings;

/*
 * 'public': This class can be accessed outside of this folder.
 * 'class': This can contain variables and functionality.
 * 'Claw': The name of this particular class. This is treated as a TYPE.
 * 'implements': This class implements an interface, or more than one. This class must implement the methods described
 *               in the interface.
 * 'IComponent': The name of the interface this class implements. This can be more than one, separated by commas.
 */
public class Claw implements IComponent
{

    /*
     * 'public': This enum can be accessed outside this class.
     * 'enum': A conventiently-named list of constants, in lieu of saying '1', '2', '3'... by giving each value a
     *         meaningful name. An enum is actually capable of more than this, but this is its most used form.
     * 'State': The name of this particular enum. This is treated as a TYPE.
     */
    public enum State {
        kOpen,
        kClosed,
    }

    /*
     * 'private': This variable cannot be accessed outside this class, nor by any potential child classes.
     * 'DoubleSolenoid': the TYPE of data this variable is.
     * 'm_solenoid': A custom name for this variable. Uses the convention that member variables begin with 'm_'.
     */
    private DoubleSolenoid m_solenoid;

    /*
     * 'public': This constructor can be accessed outside this class.
     * 'Claw': The same name as the class. A "method-looking" declaration that uses the same name as the class is a
     *         constructor. Constructors do NOT specify a return type, not even 'void'.
     * '(...)': This constructor accepts 3 arguments.
     * '(int _, int _, int _)': Each argument is an 'int'. Generally, arguments need not all be the same type.
     * '(_ pneumaticsModuleId, _ openingPortId, _ closingPortId)': Each argument's name. These are variables.
     */
    public Claw(ClawSettings settings)
    {
        m_solenoid = new DoubleSolenoid(settings.pneumaticsModuleId, PneumaticsModuleType.REVPH, settings.openingPortId, settings.closingPortId);
    }

    /*
     * 'public': This method can be called by code outside this class.
     * 'void': This method does not return anything.
     * 'update': The name of this method.
     * '()': This method accepts 0 arguments.
     */
    public void update()
    {
        // This method will be called periodically.
        // This assumes that 'CommonData.ClawState' updates elsewhere before this is called.
        switch (CommonData.clawState)
        {
        case kOpen:
            m_solenoid.set(Value.kForward); // piston pushes forward -> claw opens
            break;
        case kClosed:
            m_solenoid.set(Value.kReverse); // piston pulls back -> claw closes
        }
    }
}
