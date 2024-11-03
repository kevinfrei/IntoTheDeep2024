package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.sensor.ColorSensor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class KidShampooSubsystem implements Subsystem, Loggable {

    private Servo retainer, jaw, wrist;

    @Log(name = "jawPosition")
    public double jawPosition = 0;

    @Log(name = "jawTarget")
    public double jawTarget = 0;

    private CRServo intake;
    private ColorSensor colorSensor;
    private Rev2MDistanceSensor rev2MDistanceSensor;

    public static double RETAINER_OPEN_POSITION = -.2;

    public static double RETAINER_CLOSE_POSITION = .1;

    public static double JAW_BITE_POSITION = .1;

    public static double JAW_RELEASE_POSITION = -.1;
    public static double INTAKE_SLURP = -.1;

    public static double INTAKE_SPIT = .1;

    public static double WRIST_SCOOP = .1;
    public static double WRIST_DUMP = -.1;

    @Log(name = "distance value ")
    public double distance_value;

    @Log(name = "color value ")
    public double color_value;

    public KidShampooSubsystem(Hardware hw) {
        intake = hw.intake;
        retainer = hw.retainer;
        wrist = hw.wrist;
        jaw = hw.jaw;
        colorSensor = hw.colorSensor;
        rev2MDistanceSensor = hw.rev2MDistanceSensor;
    }

    public void openRetainer() {
        retainer.setPosition(RETAINER_OPEN_POSITION);
    }

    public void closeRetainer() {
        retainer.setPosition(RETAINER_CLOSE_POSITION);
    }

    public void biteJaw() {
        jawTarget = JAW_BITE_POSITION;
        jaw.setPosition(JAW_BITE_POSITION);
    }

    public void dumpWrist() {
        wrist.setPosition(WRIST_DUMP);
    }

    public void scoopWrist() {
        wrist.setPosition(WRIST_SCOOP);
    }

    public void releaseJaw() {
        jawTarget = JAW_RELEASE_POSITION;
        jaw.setPosition(JAW_RELEASE_POSITION);
    }

    public void slurpIntake() {
        intake.setPower(INTAKE_SLURP);
    }

    public void collectHorizontalSample() {
        // closeRetainer(),
        // scoopWrist(),

    }

    @Override
    public void periodic() {
        distance_value = rev2MDistanceSensor.getDistance(DistanceUnit.CM);
        color_value = colorSensor.rgb();
        jawPosition = jaw.getPosition();
    }

    public void spitIntake() {
        intake.setPower(INTAKE_SPIT);
    }

    public void stopIntake() {
        intake.setPower(0);
    }
}
