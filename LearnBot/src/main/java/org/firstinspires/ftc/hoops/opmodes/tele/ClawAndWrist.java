package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.hoops.ClawAndWristBot;
import org.firstinspires.ftc.hoops.controllers.ClawAndWristController;

@Disabled
@TeleOp(name = "ClawAndWrist")
@SuppressWarnings("unused")
public class ClawAndWrist extends CommandOpMode implements Loggable {

    ClawAndWristBot cawbot;
    ClawAndWristController controller;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        cawbot = new ClawAndWristBot();
        controller = new ClawAndWristController(driverGamepad, cawbot);
    }
}
