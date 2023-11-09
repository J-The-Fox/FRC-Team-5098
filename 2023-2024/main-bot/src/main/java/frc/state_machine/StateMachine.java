package frc.state_machine;

import java.util.ArrayList;
import java.util.List;

/**
 * State Machine class for controlling a state machine.
 * This is mainly used during auto.
 * 
 * @see frc.components.auto
 */
public class StateMachine {

    State       currentState;
    List<State> states = new ArrayList<State>();

    public void addState(final State state) {
        states.add(state);
    }

    /**
    * Method called to run the state machine
    */
    public void run() {
        if (!states.isEmpty()) {
            if (currentState == null && states.get(0).isValid()) {
                currentState = states.get(0);
                currentState.onEnter();
                states.remove(0);
            }
        }

        if (currentState != null) {
            if (currentState.run()) {
                currentState.onExit();
                currentState = null;
            }
        }
    }
}
