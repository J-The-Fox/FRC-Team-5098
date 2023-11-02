# Deploy Directory

> Copied from 'example.txt'

Files placed in this directory will be deployed to the RoboRIO into the
'deploy' directory in the home folder. Use the 'Filesystem.getDeployDirectory' wpilib function
to get a proper path relative to the deploy directory.

## Current Files

The file `robot_settings.json` is stored in this directory as we need to read the settings from it, and so it needs to be deployed to the robot.
The `robot_settings.json` file works hand in hand with the `frc/settings` directory. Anything in the `robot_settings.json` file must also be in the `Settings.java` file as a type decleration for the setting to be pulled.
This is because of how we implemeted the library to read the json file.
In Python this would be done with the `json` module however Java has no library equivilant so it needs to be used differently. The settings are read and used in the `Robot.java` file.