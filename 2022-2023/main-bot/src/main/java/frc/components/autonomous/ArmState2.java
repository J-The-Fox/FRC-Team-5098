package frc.components.autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.CommonData;
import frc.components.Claw;
import frc.state_machine.State;

public class ArmState2 extends State
{
    Timer timer = new Timer();

    @Override
    public void onEnter()
    {
        System.out.println("Arm State 2 entered");
        timer.start();
    }

    @Override
    public void onExit()
    {
        System.out.println("Arm State 2 exited");
    }

    @Override
    public boolean run()
    {
        if (timer.hasElapsed(1))
        {
            CommonData.counter++;
            return true;
        }
        else if (timer.hasElapsed(0.5))
        {
            CommonData.clawState = Claw.State.kOpen;
        }

        return false;
    }
}
