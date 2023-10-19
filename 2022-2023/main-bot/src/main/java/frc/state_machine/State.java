package frc.state_machine;

public abstract class State
{
    public void onEnter()
    {
    }
    public void onExit()
    {
    }

    public boolean isValid()
    {
        return true;
    }

    // Returns 'true' when state is finished
    public abstract boolean run();
}
