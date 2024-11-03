package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;

public class HighBasketCommand {

    public class HighBasket extends SequentialCommandGroup {

        public SequentialCommandGroup HighBasket(Robot r) {
            return new SequentialCommandGroup(
                Command.create(r.armSubsystem::highBasket, r.armSubsystem),
                Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem)
            );
        }
    }
}
