package frc.state_machine;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a state machine class. It is used to run a set of actions in order.
 * A state will be ran when the state is invalid.
 * The current state will keep running until the state is valid.
 * Once the state is valid, the state will be removed from the list and the next state will be ran.
 * Once the list is empty, the state machine will stop running.
 */

public class StateMachine
{
    State       m_currentState;
    List<State> m_states = new ArrayList<State>();

    public void addState(State state)
    {
        m_states.add(state);
    }

    public void run()
    {
        if (!m_states.isEmpty())
        {
            if (m_currentState == null && m_states.get(0).isValid())
            {
                m_currentState = m_states.get(0);
                m_currentState.onEnter();
                m_states.remove(0);
            }
        }

        if (m_currentState != null)
        {
            if (m_currentState.run())
            {
                m_currentState.onExit();
                m_currentState = null;
            }
        }
    }
}
