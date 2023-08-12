# Deploy Directory

> Copied from 'example.txt'

Files placed in this directory will be deployed to the RoboRIO into the
'deploy' directory in the home folder. Use the 'Filesystem.getDeployDirectory' wpilib function
to get a proper path relative to the deploy directory.

## Current Files

The file 'robot_settings.json' is stored in this directory as we need to read the settings from it, and so it needs to be deployed to the robot

**robot_settings.json:**
- controllerID: ID for the main drive controller
- auxControllerID: ID for the aux controller
- swerveDrive: Swerve drive settings
    - FLSwerve: Each swerve wheel has one of these. Holds all the settings about each swerve wheel. Only the Front Left is shown as the others are basically copies
        - driveID: ID of the drive motor
        - turnID: ID of the turn motor
        - encoderID: ID of the encoder
        - location: If it's front or back
        - defensiveAngle: Angle it needs to be for it to go into an X
        - posY: Location in relation to the center of the robot on the Y axis
        - posX: Location in relation to the center of the robot on the X axis
- clawSettings: Claw settings
    - pneumaticsModuleId: Pneumatic Hub ID (or Pneumatic Control Module for the CTRE version)
    - openingPortId: Opening solenoid ID
    - closingPortId: Closing solenoid ID
- armSettings: Arm settings
    - shoulderMotorID: ID of the shoulder motor
    - elbowMotorID: ID of the elbow motor