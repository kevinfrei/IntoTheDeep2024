package org.firstinspires.ftc.learnbot.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

@Config
public class RunToPositionSubsystem implements Subsystem, Loggable {
    @Log(name = "Current Pos")
    public static double CURRENT_POSITION = 0;

    public static double SMALL_POS_CHANGE = 50;
    public static double LARGE_POS_CHANGE = 500;
    public static double TARGET_POSITION = 1500;

    public static double P_pid = 1.0;
    public static double I_pid = 0.005;
    public static double D_pid = 0.0001;
    public static double F_pid = 0.0;

    public static double POWER_LEVEL = 0.5;

    EncodedMotor<DcMotorEx> m;

    public RunToPositionSubsystem(EncodedMotor<DcMotorEx> motor) {
        m = motor;
        m.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setPIDFCoefficients(P_pid, I_pid, D_pid, F_pid);
    }

    public void GoToPosition() {
        m.setPosition(TARGET_POSITION, POWER_LEVEL);
    }

    public void IncSmallPosition() {
        TARGET_POSITION += SMALL_POS_CHANGE;
    }

    public void IncLargePosition() {
        TARGET_POSITION += LARGE_POS_CHANGE;
    }

    public void DecSmallPosition() {
        TARGET_POSITION -= SMALL_POS_CHANGE;
    }

    public void DecLargePosition() {
        TARGET_POSITION -= LARGE_POS_CHANGE;
    }

    @Override
    public void periodic() {
        CURRENT_POSITION = m.getAsDouble();
    }
}
