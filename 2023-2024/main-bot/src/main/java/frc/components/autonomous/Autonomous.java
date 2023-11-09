package frc.components.autonomous;

import frc.components.IComponent;
import frc.state_machine.StateMachine;

/**
 * Autonomous component.
 * <p>
 * This class is used for autonomous mode.
 * It sets up a state machine and excutes the states contained.
 * </p>
 * <p>
 * If you want to add a new state, see the constructor.
 * {@link frc.components.autonomous.Autonomous#Autonomous()}
 * <p>
 * This class implements the
 * {@link frc.components.IComponent IComponent} interface.
 * </p>
 *
 * @see frc.state_machine.StateMachine
 */
public class Autonomous implements IComponent {


    /**
     * State machine object
     */
    StateMachine stateMachine = new StateMachine();

    /**
     * Autonomous constructor.
     * <p>
     * This is where new states are added to the state machine.
     * Any state added here will be run during autonomous in
     * the order they are added.
     * </p>
     * <p>
     * You can add states by using the following code:
     *
     * <pre>
     * stateMachine.addState(new StateName());
     * </pre>
     *
     * <i>Any added states must also exist in this directory as separate
     * classes.</i>
     * </p>
     *
     * @see frc.components.autonomous.DriveState1
     */
    public Autonomous() {
        stateMachine.addState(new DriveState1());
    }

    @Override
    public void update() {
        stateMachine.run();
    }
}
