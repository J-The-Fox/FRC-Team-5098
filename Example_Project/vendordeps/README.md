# Vendor Deps

This folder holds all the vendor dependencies for the hardware of robot.

## Installing, Updating, and Removing

**Installing (Offline)**:  
To add a vendor library that has been installed by an offline installer, press `Ctrl+Shift+P` and type `WPILib` or click on the WPILib icon in the top right to open the WPILib Command Palette and begin typing `Manage Vendor Libraries`, then select it from the menu. Select the option to `Install new libraries (offline)`.

**Installing (Online)**:  
To add a vendor library online, press `Ctrl+Shift+P` and type `WPILib` or click on the WPILib icon in the top right to open the WPILib Command Palette and begin typing `Manage Vendor Libraries`, then select it from the menu. Select the option to `Install new libraries (online)`. Then paste in the URL. Most vendor libraries can be found [here](https://docs.wpilib.org/en/stable/docs/software/vscode-overview/3rd-party-libraries.html#libraries). However you can also install external libraries the same way, just paste in the URL and hit enter

**Updating (Offline)**:  
Since dependencies are version managed on a per-project basis, even when installed offline, you will need to go to Manage Vendor Libraries and select `Check for updates (offline)` for each project you wish to update.

**Updating (Online)**:  
Part of the JSON file that vendors may optionally populate is an online update location. If a library has an appropriate location specified, running `Check for updates (online)` will check if a newer version of the library is available from the remote location.


**Removing**:  
To remove a library dependency from a project, select `Manage Current Libraries` from the Manage Vendor Libraries menu, check the box for any libraries to uninstall and click `OK`. These libraries will be removed as dependencies from the project.


## Files In This `vendordep` Folder

| File                   | Description                                                                                                                                                           |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| NavX.json              | Libraries for NavX-MXP, NavX-Micro, and Sensor Fusion                                                                                                                 |
| PhoenixProAnd5.json    | Contains CANcoder, CANifier, CANdle, Pigeon IMU, Pigeon 2.0, Talon FX, Talon SRX, and Victor SPX Libraries and Phoenix Tuner program for configuring CTRE CAN devices |
| photonlib.json         | Library for PhotonVision CV software                                                                                                                                  |
| REVLib.json            | Library for all REV devices including SPARK MAX and Color Sensor V3                                                                                                   |
| WPILibNewCommands.json | Libraries for WPILib new commands                                                                                                                                     |

More Info can be found in the [Wiki](https://github.com/J-The-Fox/FRC-Team-5098/wiki)

Credit: Info parsed and copied from the [WPILib Docs](https://docs.wpilib.org/en/stable/)