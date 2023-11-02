# Controllers

This folder is used to store the controller files that are used to intereact with the controllers we use to control the robot  
Altough this folder is not required, it is useful for organization

There are two main types of controllers that could be used. The standard controller such as an Xbox One, Logitech F310, etc and a joystick controller like the Logitech Attack 3. Both are similar to set up. Same functions and methods, just different IDs

## Set Up

There are few different types of inputs on a controller. Axis input for things like joysticks, Button input for standard buttons and POV input for d-pads.  

A few imports are used to set up a controller class:
- `edu.wpi.first.wpilibj.Joystick` - For getting input from the controller (Required)
- `edu.wpi.first.wpilibj.GenericHID.RumbleType` - For controlling rumble if the controller supports it (Optional)
- `java.lang.Math` - For adding a deadband if the controller drifts (Optional)

**Setting Up The Joystick:**

The Joystick import is responsible for getting all input from the controller. To set it up, you need to define an ID for that controller. (Which is set in the Driver Station by the order of the controllers. So the top one is ID 0, the second one is ID 1, etc.)

The code below is used to set an ID for an Xbox360 controller when an Xbox360 instance is created

> Remember to put the code in your class!

Ex:
```java
public Xbox360(int ID)
    {
        joystick = new Joystick(ID);
    }
```

Now that the joystick is created. It can be used to gather input from the controller.

**Setting Up Buttons:**

To take inputs from buttons on the controller you need to use the `joystick.getRawButton(ID)` method.
We can use this method inside of whatever function, in this case getA(), to get the input of the controller  
The method will return a boolean so the function will also return a boolean

> The ID for the getRawButton method will change depending on the button that you are getting.
> If you don't know the ID for the button, you can look online or check in the Driver Station.  
> In the Driver Station, go to the controller menu and click on the desired controller. Hit the button you want to ID and look which square lights up.
> The position of the sqaure indicates the ID of the button

Ex:
```java
public boolean getA()
    {
        return joystick.getRawButton(1);
    }
```

You can repeat this for however many buttons you have on your controller, naming the functions accordingly

**Setting Up Axis Inputs:**

Setting up inputs for an axis is similar to that of a controller except using the `joystick.getRawAxis(ID)` method.
The method will return a double (from -1 to 1) so the function will also return a double

> Checking the ID of the axis is almost the same for checking the ID of the buttons.  
> Instead of looking at which squares lights up, look at which bar moves up and down when moved

Ex:
```java
public double getLeftStickX()
    {
        return joystick.getRawAxis(0);
    }
```

You can repeat this for each axis you have on your controller, naming the functions accordingly

**Setting Up A D-Pad:**

The D-Pad differs greatly from any other method as it uses a POV to get which position it is in.  
As such, it needs some extra code to get it working

```java
public enum dPad {
        up,
        down,
        left,
        right,
        upright,
        upleft,
        downright,
        downleft,
        none
    }
```

This is an enum that is used for all 9 possible inputs from the D-Pad (including no input).  
It is required for the next bit of code that follows it

```java
public dPad getPad()
    {
        switch (joystick.getPOV())
        {
        case 0:
            return dPad.up;

        case 360:
            return dPad.up;

        case 90:
            return dPad.right;

        case 180:
            return dPad.down;

        case 270:
            return dPad.left;

        case 45:
            return dPad.upright;

        case 135:
            return dPad.downright;

        case 225:
            return dPad.downleft;

        case 315:
            return dPad.upleft;

        default:
            return dPad.none;
        }
    }
```

This bit of code uses a switch statement to see which state the D-Pad is in and ouputs the enum value accordingly.  
This takes a little more work than a standard button or axis but it simplifies the output greatly

<!--- 
[switch statment](link to alternate README or wiki page explaining what this is)
--->

**Rumble Control**

> Not every controller supports rumble. You can check this if the Driver Station reports two extra sliders for controlling rumble manually  
> Note: The Logitech F310 does support rumble but does *not* have the rumble motors  
> Note: Setting up rumble for a controller that does not support rumble will not throw an error. It will be ignored when it tries to run

Since there 2 types of rumble, you need to set up a case for each rumble motor.
This uses the `joystick.setRumble(RumbleType, double)` to use the rumble feature

> Make sure you import `edu.wpi.first.wpilibj.GenericHID.RumbleType` to use rumble

Ex:
```java
public void rumbleHigh() // High
    {
        joystick.setRumble(RumbleType.kRightRumble, 1);
    }

public void rumbleLow() // Low
    {
        joystick.setRumble(RumbleType.kLeftRumble, 1);
    }

public void letsGetReadyToRumble() // Both
    {
        joystick.setRumble(RumbleType.kBothRumble, 1);
    }

public void rumbleOff() // Off
    {
        joystick.setRumble(RumbleType.kBothRumble, 0);
    }
```

> The values can be a double from 0 to 1. You can set your own values however this example just uses 1 and 0 for on and off

**Setting Up A Deadband:**

Setting up a deadband is mainly only used for a controller that has a slight drift to it. This is used to prevent drifting when in use
The deadband uses a custom value that you can change to whatever you may need to fix the drift on your controller with software

Ex:
```java
public double deadBand(double value)
    {
        return Math.abs(value) < 0.175 ? 0 : value;
    }
```

This sets up a deadband function with a deadband of plus or minus 0.175  
This is simplified by using a [ternary operator](https://github.com/J-The-Fox/FRC-Team-5098/wiki/Java-Operators#ternary-operator)

```java
public double getLeftStickX()
    {
        return deadBand(joystick.getRawAxis(0));
    }
```

This is using the deadband funtion created earlier to add a deadband to this axis

> The proccess is similar for both the standard controller and the joystick controller. Just make sure all the IDs are set correctly!