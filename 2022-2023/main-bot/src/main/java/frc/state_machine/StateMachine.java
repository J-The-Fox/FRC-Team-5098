package frc.state_machine;

import java.util.ArrayList;
import java.util.List;

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
