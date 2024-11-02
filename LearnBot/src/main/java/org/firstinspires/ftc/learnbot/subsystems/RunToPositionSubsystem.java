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
    public static int CURRENT_POSITION = 0;

    public static int SMALL_POS_CHANGE = 50;
    public static int LARGE_POS_CHANGE = 500;
    @Log(name = "Target Pos")
    public static int TARGET_POSITION = 1500;

    @Log(name = "Loop Count")
    public static int loopCount= 0;
    public static double P_pid = 1.0;
    public static double I_pid = 0.005;
    public static double D_pid = 0.0001;
    public static double F_pid = 0.0;

    public static double POWER_LEVEL = 0.5;

    EncodedMotor<DcMotorEx> m;
    DcMotorEx raw;
    public RunToPositionSubsystem(EncodedMotor<DcMotorEx> motor) {
        m = motor;
        loopCount = 0;
        m.setPIDFCoefficients(P_pid, I_pid, D_pid, F_pid);
        raw = m.getRawMotor(DcMotorEx.class);
        raw.setTargetPosition(CURRENT_POSITION);
        m.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void GoToPosition() {
        raw.setTargetPosition((int)TARGET_POSITION);
//        m.setPosition(TARGET_POSITION, POWER_LEVEL);
    }

    public void Stop() {
        raw.setTargetPosition((int)CURRENT_POSITION);
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
        CURRENT_POSITION = (int)m.getAsDouble();
        loopCount++;
    }
    public void Hang() {
        // DO nothing, just get scheduled
        loopCount += 1000;
    }
}
