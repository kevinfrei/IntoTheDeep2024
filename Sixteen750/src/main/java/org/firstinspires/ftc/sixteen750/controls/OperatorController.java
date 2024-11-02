package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;

public class OperatorController {

    public Robot robot;
    public Stick horislidesLeftStick;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristIncrement;
    public CommandButton wristDecrement;
    public CommandButton horislidesExtend;
    public CommandButton horislidesRetract;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.ps_circle;
        closeClaw = gamepad.ps_cross;
        wristTransfer = gamepad.ps_triangle;
        wristPickup = gamepad.ps_square;
        wristIncrement = gamepad.dpadRight;
        wristDecrement = gamepad.dpadLeft;
        horislidesLeftStick = gamepad.leftStick;
        horislidesExtend = gamepad.dpadUp;
        horislidesRetract = gamepad.dpadDown;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            bindHorizontalSlidesControls();
        }
    }

    private void bindHorizontalSlidesControls() {
        openClaw.whenPressed(HorizontalSlidesCommands.clawOpen(robot));
        closeClaw.whenPressed(HorizontalSlidesCommands.clawChomp(robot));
        wristPickup.whenPressed(HorizontalSlidesCommands.wristPickup(robot));
        wristTransfer.whenPressed(HorizontalSlidesCommands.wristTransfer(robot));
        wristIncrement.whenPressed(HorizontalSlidesCommands.wristIncrement(robot));
        wristDecrement.whenPressed(HorizontalSlidesCommands.wristDecrement(robot));
        horislidesExtend.whenPressed(HorizontalSlidesCommands.horizontalExtend(robot));
        horislidesRetract.whenPressed(HorizontalSlidesCommands.horizontalRetract(robot));
    }
}
