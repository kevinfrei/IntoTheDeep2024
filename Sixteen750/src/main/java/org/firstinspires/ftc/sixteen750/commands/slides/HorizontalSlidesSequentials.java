package org.firstinspires.ftc.sixteen750.commands.slides;

import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.clawChomp;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.clawOpen;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.horizontalExtend;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.horizontalRetract;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.horizontalSmallExtend;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.smallClose;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.wristPickup;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.wristTransfer;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.ArmTransfer;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.BucketTransfer;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials.SlidesDown;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials.transferBucket;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.sixteen750.Robot;

public class HorizontalSlidesSequentials {

    public static SequentialCommandGroup intake(Robot r) {
        return new SequentialCommandGroup(
            horizontalExtend(r),
            wristTransfer(r),
            clawOpen(r),
            new WaitCommand(1),
            wristPickup(r)
        );
    }
    public static SequentialCommandGroup intakeClawSmall(Robot r) {
        return new SequentialCommandGroup(
                horizontalExtend(r),
                wristTransfer(r),
                smallClose(r),
                new WaitCommand(1),
                wristPickup(r)
        );
    }

    public static SequentialCommandGroup intakeSmall(Robot r) {
        return new SequentialCommandGroup(
            horizontalSmallExtend(r),
            wristTransfer(r),
            clawOpen(r),
            new WaitCommand(1),
            wristPickup(r)
        );
    }

    public static SequentialCommandGroup retract(Robot r) {
        return new SequentialCommandGroup(
            wristTransfer(r),
            horizontalRetract(r),
            clawChomp(r),
            BucketTransfer(r),
            ArmTransfer(r),
            new WaitCommand(0.1),
            clawOpen(r)
        );
    }

    public static SequentialCommandGroup transferring(Robot r) {
        return new SequentialCommandGroup(
            wristTransfer(r),
            horizontalRetract(r),
            clawChomp(r),
            SlidesDown(r),
            new WaitCommand(0.1),
            clawOpen(r)
        );
    }

    public static SequentialCommandGroup open(Robot r) {
        return new SequentialCommandGroup(wristPickup(r), clawOpen(r));
    }
}
