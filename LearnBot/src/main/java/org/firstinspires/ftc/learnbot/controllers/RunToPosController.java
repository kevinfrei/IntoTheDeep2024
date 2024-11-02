package org.firstinspires.ftc.learnbot.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.logger.Loggable;
import org.firstinspires.ftc.learnbot.OnlyMotorRobot;
import org.firstinspires.ftc.learnbot.Setup;
import org.firstinspires.ftc.learnbot.commands.AnalogMotorControlCmd;
import org.firstinspires.ftc.learnbot.commands.EZCmd;
import org.firstinspires.ftc.learnbot.subsystems.RunToPositionSubsystem;

public class RunToPosController implements Loggable {

    public CommandButton goButton;
    public CommandButton stopButton;
    public CommandButton smallIncButton;
    public CommandButton smallDecButton;
    public CommandButton largeIncButton;
    public CommandButton largeDecButton;

    public RunToPosController(CommandGamepad g, RunToPositionSubsystem rtp) {
        this.smallIncButton = g.dpadUp;
        this.smallDecButton = g.dpadDown;
        this.largeIncButton = g.dpadRight;
        this.largeDecButton = g.dpadLeft;
        this.stopButton = g.leftBumper;
        this.goButton = g.rightBumper;
        if (Setup.Connected.MOTOR) {
            smallDecButton.whenPressed(rtp::DecSmallPosition);
            smallIncButton.whenPressed(rtp::IncSmallPosition);
            largeDecButton.whenPressed(rtp::DecLargePosition);
            largeIncButton.whenPressed(rtp::IncLargePosition);
            goButton.whenPressed(rtp::GoToPosition);
            stopButton.whenPressed(rtp::Stop);
        }
    }
}
