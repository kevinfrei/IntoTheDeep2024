package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    //complete sequentials
    public static SequentialCommandGroup HighBasket(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
                transferVertical(r),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::slideBasketHigh),
                BasketScore(r)
        );
    }
    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
                transferVertical(r),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::slideBasketLow),
                BasketScore(r)
        );
    }
    public static SequentialCommandGroup SlidesDown(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::slideBasketLow),
                new WaitCommand(.1),
                Command.create(r.verticalSlidesSubsystem::slidesDown),
                transferVertical(r)
        );
    }

    //partial sequentials
    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.horizontalSlidesSubsystem::WristVertTransfer),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::bucketServoLift),
                Command.create(r.verticalSlidesSubsystem::armServoTransfer),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::slidesDown),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup BasketScore(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoLift),
                new WaitCommand(0.3),
                Command.create(r.verticalSlidesSubsystem::armServoEmpty),
                new WaitCommand(0.7),
                Command.create(r.verticalSlidesSubsystem::bucketServoEmpty)
        );
    }


}

