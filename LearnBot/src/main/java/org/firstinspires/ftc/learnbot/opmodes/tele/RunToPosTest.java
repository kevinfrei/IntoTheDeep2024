package org.firstinspires.ftc.learnbot.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.learnbot.Hardware;
import org.firstinspires.ftc.learnbot.OnlyMotorRobot;
import org.firstinspires.ftc.learnbot.Setup;
import org.firstinspires.ftc.learnbot.controllers.MotorController;
import org.firstinspires.ftc.learnbot.controllers.RunToPosController;
import org.firstinspires.ftc.learnbot.subsystems.RunToPositionSubsystem;

@TeleOp(name = "RunToPos1")
@SuppressWarnings("unused")
public class RunToPosTest extends CommandOpMode implements Loggable {

    public Hardware hardware;
    public RunToPositionSubsystem rtp;
    public RunToPosController driver;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        if (Setup.Connected.MOTOR) {
            rtp = new RunToPositionSubsystem(hardware.theMotor);
            driver = new RunToPosController(driverGamepad, rtp);
            CommandScheduler.scheduleJoystick(rtp::Hang);
        }
    }
}
