package org.firstinspires.ftc.teamcode.EK10582.auton.modes.oldOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline.SpikePositionsBlue;

@Autonomous(name="TopBlueOld")
@Disabled
public class TopBlueOld extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(true);
        robot.openCV.update(true);
        SpikePositionsBlue pos = SpikePipeline.spikePositionB;
        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case LEFT:
                //left case
                runAction(new AngleMove(-110, 38, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 30, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 30, 0.6));
                break;
            case RIGHT:
                //right case
                runAction(new AngleMove(-90, 31, 0.6));
                sleep(500);
//                runAction(new AngleTurn(-90,0.3, -1));
                runAction(new AngleMove(-40, 23, 0.4));
                sleep(500);
                runAction(new AngleMove(90, 3, 0.4));
                sleep(500);
                runAction(new AngleMove(180, 30, 0.4));
                sleep(500);
                runAction(new AngleMove(115, 43, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 15, 0.6));
                break;
            case MIDDLE:
                //middle case
                runAction(new AngleMove(-90, 47, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 34, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 52, 0.6));
                break;
        }
        sleep(1000);
    }
}
