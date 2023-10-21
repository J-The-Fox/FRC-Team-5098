package frc.state_machine;

public abstract class State
{

    // Imported from 2022-2023 code

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
