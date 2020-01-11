/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Ports {
        // CAN motors ports
        public static final int kLeft1 = 4;
        public static final int kLeft2 = 3;
        public static final int kRight1 = 2;
        public static final int kRight2 = 1;

        // Controllers' ports
        public static final int kStick = 0;
    }
    public static final class Gyro {
        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;

        public static final double kStableP = 1;
        public static final double kStableI = 0.5;
        public static final double kStableD = 0;

        public static final double kPositionTolerance = 0;
        public static final double kVelocityTolerance = 0;
    }
    public static final class Stick {
        public static final int kX = 3;
        public static final int kY = 4;
        public static final int kA = 1;
        public static final int kB = 2;
        public static final int kLB = 5;
        public static final int kRB = 6;

        public static final int kLYaxis = 1;
        public static final int kLXaxis = 0;
        public static final int kRYaxis = 5;
        public static final int kRXaxis = 4;
    }
}
