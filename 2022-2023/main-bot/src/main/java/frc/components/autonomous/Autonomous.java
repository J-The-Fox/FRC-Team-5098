package frc.components.autonomous;

import frc.components.IComponent;
import frc.state_machine.StateMachine;

public class Autonomous implements IComponent
{
    StateMachine m_stateMachine = new StateMachine();

    public Autonomous()
    {
        // m_driveStateMachine.addState(new DriveState1());
        // m_driveStateMachine.addState(new DriveState2());

        // VV Balancing code -- UNCOMMENT TO TEST THIS CODE AT AN OFFICIAL COMPETITION - COMMENT AGAIN IF IT DOES NOT WORK
        // m_driveStateMachine.addState(new DriveState5());
        // ^^ Balancing code

        m_stateMachine.addState(new ArmState1());   // Move arm to drop box
        m_stateMachine.addState(new ArmState2());   // Drop box
        m_stateMachine.addState(new ArmState3());   // Move arm to reset
        m_stateMachine.addState(new DriveState3()); // Move to balance or taxi
        m_stateMachine.addState(new DriveState6()); // Balance
    }

    @Override
    public void update()
    {
        m_stateMachine.run();
    }
}
