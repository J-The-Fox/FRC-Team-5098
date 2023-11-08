package frc.state_machine;

/**
* Abstract class used to create states for the state machine
*/
public abstract class State {

    // Imported from 2022-2023 code

    /**
    * Method called when state is entered
    */
    public void onEnter() {
    }

    /**
    * Method called when state is exited
    */
    public void onExit() {
    }

    /**
     * Method called to check if state is valid
     * @return boolean
     */
    public boolean isValid() {
        return true;
    }

    // Returns 'true' when state is finished
    /**
     * Method called to run state.
     * This method should return true when the state is finished
     */
    public abstract boolean run();
}
