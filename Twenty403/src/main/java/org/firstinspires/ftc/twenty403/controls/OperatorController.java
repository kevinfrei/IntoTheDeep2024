package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.ArmSubCmds;
import org.firstinspires.ftc.twenty403.commands.HighBasketCommand;
import org.firstinspires.ftc.twenty403.commands.IntakePositionCommand;
import org.firstinspires.ftc.twenty403.commands.JoystickIncDecCommand;
import org.firstinspires.ftc.twenty403.commands.JoystickSlideIncDecCommand;
import org.firstinspires.ftc.twenty403.commands.KidShampooCmds;
import org.firstinspires.ftc.twenty403.commands.LowBasketCommand;
import org.firstinspires.ftc.twenty403.commands.LowSpecimenCommand;
import org.firstinspires.ftc.twenty403.commands.driving.JoystickDriveCommand;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openRetainer;
    public CommandButton closeRetainer;
    public CommandButton eatRetainer;
    public CommandButton biteJaw;
    public CommandButton releaseJaw;
    public CommandButton slurpIntake;
    public CommandButton spitIntake;
    public CommandButton stopIntake;
    public CommandButton suspend;
    public CommandButton SuspendReverse;
    public CommandButton armLowNet;
    public CommandButton armLowSpecimen;
    public CommandButton armHighSpecimen;
    public CommandButton straightWrist;
    public CommandButton armHorizontal;
    public CommandButton armVertical;
    public CommandButton dumpWrist;
    public CommandButton scoopWrist;
    public CommandButton slideIn;
    public CommandButton slideOut;
    public CommandButton slideMax;
    public CommandButton slideMin;
    public Stick armStick;
    public Stick slideStick;
    public CommandButton HighBasket;
    public CommandButton IntakeSample;
    public CommandButton IntakeSpecimen;
    public CommandButton WristInc;
    public CommandButton WristDec;
    public CommandButton IntakePos;
    public CommandButton resetslidePos;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
        openRetainer = gamepad.dpadRight;
        closeRetainer = gamepad.dpadLeft;
        slurpIntake = gamepad.leftBumper;
        spitIntake = gamepad.rightBumper;
        //temp changing the button below from biteJaw to intake :DD
        /*biteJaw = gamepad.ps_cross;
       releaseJaw = gamepad.ps_triangle;*/
        dumpWrist = gamepad.ps_triangle;
        scoopWrist = gamepad.ps_cross;
        slideMax = gamepad.ps_share;
        slideMin = gamepad.ps_options;
        // suspend = gamepad.ps_circle;
        IntakePos = gamepad.dpadDown;
        //        armLowNet = gamepad.dpadLeft;
        //        armLowSpecimen = gamepad.leftStickButton;
        //        armHighSpecimen = gamepad.rightStickButton;

        resetslidePos = gamepad.dpadUp;
        armStick = gamepad.rightStick;
        slideStick = gamepad.leftStick;
        HighBasket = gamepad.ps_circle;
        // armHighSpecimen = gamepad.ps_triangle;
        //armLowSpecimen = gamepad.ps_square;
        armLowNet = gamepad.ps_square;
        /*IntakeSample = gamepad.ps_share;
        IntakeSpecimen = gamepad.ps_options;*/
        //WristDec = gamepad.ps_circle;
        //WristInc = gamepad.ps_square;
    }

    public void BindControls() {
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
            bindKidShampooControls();
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            //bindHangControls();
        }

        if (Setup.Connected.ARMSUBSYSTEM) {
            bindArmControls();
        }
    }

    public void bindKidShampooControls() {
        openRetainer.whenPressed(KidShampooCmds.cmds.OpenRetainer(robot.kidShampooSubsystem));
        closeRetainer.whenPressed(KidShampooCmds.cmds.CloseRetainer(robot.kidShampooSubsystem));
        /* eatRetainer.whenPressed(Command.create(robot.kidShampooSubsystem::eatRetainer, robot.kidShampooSubsystem));
        biteJaw.whenPressed(
            KidShampooCmds.cmds.BiteJaw(robot.kidShampooSubsystem)
        );
        releaseJaw.whenPressed(
                KidShampooCmds.cmds.ReleaseJaw(robot.kidShampooSubsystem)
        );*/
        slurpIntake.whenPressed(KidShampooCmds.cmds.SlurpIntake(robot.kidShampooSubsystem));
        slurpIntake.whenReleased(KidShampooCmds.cmds.StopIntake(robot.kidShampooSubsystem));
        spitIntake.whenPressed(KidShampooCmds.cmds.SpitIntake(robot.kidShampooSubsystem));

        spitIntake.whenReleased(KidShampooCmds.cmds.StopIntake(robot.kidShampooSubsystem));
        dumpWrist.whenPressed(
            //Command.create(robot.kidShampooSubsystem::dumpWrist, robot.kidShampooSubsystem)
            KidShampooCmds.cmds.DumpWrist(robot.kidShampooSubsystem)
        );
        scoopWrist.whenPressed(
            //Command.create(robot.kidShampooSubsystem::scoopWrist, robot.kidShampooSubsystem)
            KidShampooCmds.cmds.ScoopWrist(robot.kidShampooSubsystem)
        );
        /*straightWrist.whenPressed(
            Command.create(robot.kidShampooSubsystem::straightWrist, robot.kidShampooSubsystem)
        );*/
        /*WristInc.whenPressed(
                Command.create(robot.kidShampooSubsystem::wristIncrement, robot.kidShampooSubsystem)
        );
        WristDec.whenPressed(
                Command.create(robot.kidShampooSubsystem::wristDecrement, robot.kidShampooSubsystem)
        );*/
    }

    public void bindArmControls() {
        armLowNet.whenPressed(LowBasketCommand.LowBasket(robot));
        //armLowSpecimen.whenPressed(LowSpecimenCommand.LowSpecimen(robot));
        // armHighSpecimen.whenPressed(HighSpecimenCommand.HighSpecimen(robot));
        HighBasket.whenPressed(HighBasketCommand.HighBasket(robot));
        //IntakeSample.whenPressed(IntakeSampleCommand.IntakeSample(robot));
        // IntakeSpecimen.whenPressed(IntakeSpecimenCommand.IntakeSpecimen(robot));*/
        IntakePos.whenPressed(IntakePositionCommand.IntakePos(robot));
        /*armHorizontal.whenPressed(
            Command.create(robot.armSubsystem::horizontal, robot.armSubsystem)
        );*/
        resetslidePos.whenPressed(
            Command.create(robot.armSubsystem::resetSlideZero, robot.armSubsystem)
        );
        /*armVertical.whenPressed(Command.create(robot.armSubsystem::vertical, robot.armSubsystem));*/
        // slideIn.whenPressed(Command.create(robot.armSubsystem::slideDecrement, robot.armSubsystem));
        // slideOut.whenPressed(Command.create(robot.armSubsystem::slideIncrement, robot.armSubsystem));
        // slideMin.whenPressed(Command.create(robot.armSubsystem::setSlideToZero, robot.armSubsystem));
        //slideMax.whenPressed(Command.create(robot.armSubsystem::slideSpecimen, robot.armSubsystem));*/
        CommandScheduler.scheduleJoystick(new JoystickIncDecCommand(robot.armSubsystem, armStick));

        CommandScheduler.scheduleJoystick(
            new JoystickSlideIncDecCommand(robot.armSubsystem, slideStick)
        );
    }

    public void bindHangControls() {
        //suspend.whenPressed(Command.create(robot.hangSubsystem::suspend, robot.hangSubsystem));

    }
}
