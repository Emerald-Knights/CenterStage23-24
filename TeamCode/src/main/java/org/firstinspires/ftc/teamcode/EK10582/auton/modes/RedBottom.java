package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;

@Autonomous(name="RedBottom")
@Config
public class RedBottom extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;
        Pose2d startPos = new Pose2d(-36,-60, Math.toRadians(-90));

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        robot.roadRunner.setPoseEstimate(startPos);

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        switch (pos) {
            case LEFT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(-47,-30, Math.toRadians(-90)))
                        .back(4)
                        .strafeLeft(8)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(10,-8, Math.toRadians(180)))
                        .splineTo(new Vector2d(40,-36), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(52, -30, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineTo(new Vector2d(52, -60))
                        .lineTo(new Vector2d(60, -60))
                        .build();
                break;
            case RIGHT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .splineToLinearHeading(new Pose2d(20, -30, Math.toRadians(-90)), Math.toRadians(0))
                        .forward(4)
                        .strafeRight(8)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(10,-8, Math.toRadians(180)))
                        .splineTo(new Vector2d(40,-36), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(52, -42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineTo(new Vector2d(52, -60))
                        .lineTo(new Vector2d(60, -60))
                        .build();
                break;

            case MIDDLE:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(-36,-30, Math.toRadians(-90)))
                        .forward(4)
                        .strafeRight(8)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(10,-8, Math.toRadians(180)))
                        .splineTo(new Vector2d(40,-36), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(52, -35, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineTo(new Vector2d(52, -60))
                        .lineTo(new Vector2d(60, -60))
                        .build();
                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(1000);
        robot.roadRunner.followTrajectorySequence(traj_toBackboard);
        sleep(1000);
        robot.aprilTags.relocalize();
        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        //runAction(new DepositHousing());
        sleep(1000);
        robot.roadRunner.followTrajectorySequence(traj_park);


//        double distFromAprilTagX, distFromAprilTagForward;
//        Pose2d startPos = new Pose2d(-36,-60, Math.toRadians(0));
//
//        waitForStart();
//
//        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;
//
//        //close opencv and open apriltags
//        robot.openCV.stop();
//
//        telemetry.addData("pos: ", pos);
//        telemetry.update();
//
//        robot.roadRunner.setPoseEstimate(startPos);
//
//        switch (pos) {
//            case LEFT:
//                Trajectory pushPixelLineupL = robot.roadRunner.trajectoryBuilder(startPos)
//                        .lineToLinearHeading(new Pose2d(-35,32, Math.toRadians(180)))
//                        .build();
//                Trajectory pushPixelL = robot.roadRunner.trajectoryBuilder(pushPixelLineupL.end())
//                        .back(4)
//                        .build();
//                TrajectorySequence pushPixelreturnL = robot.roadRunner.trajectorySequenceBuilder(pushPixelL.end())
//                        .forward(4)
//
//                        .splineToConstantHeading(new Vector2d(-60,24), Math.toRadians(180))
//
//                        .build();
//                TrajectorySequence splinetoBoardL = robot.roadRunner.trajectorySequenceBuilder(pushPixelreturnL.end())
//                        .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
//                        .splineToConstantHeading(new Vector2d(40,36), Math.toRadians(0))
//                        .build();
//                robot.roadRunner.followTrajectory(pushPixelLineupL);
//                sleep(200);
//                robot.roadRunner.followTrajectory(pushPixelL);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(pushPixelreturnL);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(splinetoBoardL);
//                sleep(200);
//                robot.aprilTags.update(true);
//                distFromAprilTagX = robot.aprilTags.tagX;
//                distFromAprilTagForward = robot.aprilTags.tagDistance;
//
//                if(distFromAprilTagForward == -1){
//                    distFromAprilTagForward = 27;
//                    distFromAprilTagX = 0;
//                }
//
//                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
//                telemetry.addData("tagx ", robot.aprilTags.tagX);
//                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
//                telemetry.update();
//
//
//                sleep(2000);
//                TrajectorySequence turnToSlides = robot.roadRunner.trajectorySequenceBuilder(splinetoBoardL.end())
//                        .turn(Math.toRadians(-95))
//                        .build();
//
//                Trajectory alignAprilTagL = robot.roadRunner.trajectoryBuilder(turnToSlides.end())
//                        .strafeTo(new Vector2d(20 + distFromAprilTagForward - 2, 28 - distFromAprilTagX - 4))
//                        .build();
//                robot.roadRunner.followTrajectorySequence(turnToSlides);
//                sleep(1000);
//                robot.roadRunner.followTrajectory(alignAprilTagL);
//                //robot.dumper.setPosition(SubsystemConstants.dumperTop);
//
//                Trajectory awayL = robot.roadRunner.trajectoryBuilder(alignAprilTagL.end())
//                        .forward(4)
//                        .build();
//
//                Trajectory parkL = robot.roadRunner.trajectoryBuilder(awayL.end())
//                        .strafeLeft(32)
//                        .build();
//
//                sleep(3000);
//
//                robot.dumper.setPosition(0.5);
//
//                robot.roadRunner.followTrajectory(awayL);
//                robot.roadRunner.followTrajectory(parkL);
//                break;
//
//            case RIGHT:
//                Trajectory pushPixelR1 = robot.roadRunner.trajectoryBuilder(startPos)
//                        .lineToLinearHeading(new Pose2d(-35,32,Math.toRadians(0)))
//                        .build();
//                Trajectory pushPixelR2 = robot.roadRunner.trajectoryBuilder(pushPixelR1.end())
//                        .back(4)
//                        .build();
//                Trajectory pushPixelR3 = robot.roadRunner.trajectoryBuilder(pushPixelR2.end())
//                        .forward(4)
//                        .build();
//                Trajectory pushPixelR4 = robot.roadRunner.trajectoryBuilder(pushPixelR3.end())
//                        .splineToConstantHeading(new Vector2d(-60,12), Math.toRadians(180))
//                        .build();
//
//
//                TrajectorySequence splinetoBoardR = robot.roadRunner.trajectorySequenceBuilder(pushPixelR4.end())
//                        .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
//                        .splineToConstantHeading(new Vector2d(40,36), Math.toRadians(0))
//                        .build();
//
//                robot.roadRunner.followTrajectory(pushPixelR4);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(splinetoBoardR);
//                sleep(200);
//                robot.aprilTags.update(true);
//                distFromAprilTagX = robot.aprilTags.tagX;
//                distFromAprilTagForward = robot.aprilTags.tagDistance;
//
//                if(distFromAprilTagForward == -1){
//                    distFromAprilTagForward = 32;
//                    distFromAprilTagX = -15;
//                }
//
//                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
//                telemetry.addData("tagx ", robot.aprilTags.tagX);
//                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
//                telemetry.update();
//
//
//                sleep(5000);
//                robot.roadRunner.followTrajectorySequence(splinetoBoardR);
//
//
//                Trajectory alignAprilTagR = robot.roadRunner.trajectoryBuilder(splinetoBoardR.end())
//                        .strafeTo(new Vector2d(20 + distFromAprilTagForward + 5, 28 - distFromAprilTagX - 4))
//                        .build();
//                robot.roadRunner.followTrajectory(alignAprilTagR);
//                //robot.dumper.setPosition(SubsystemConstants.dumperTop);
//
//                Trajectory awayR = robot.roadRunner.trajectoryBuilder(alignAprilTagR.end())
//                        .forward(4)
//                        .build();
//
//                Trajectory parkR = robot.roadRunner.trajectoryBuilder(awayR.end())
//                        .strafeLeft(16)
//                        .build();
//
//                sleep(3000);
//
//                robot.dumper.setPosition(0.5);
//                robot.roadRunner.followTrajectory(awayR);
//                robot.roadRunner.followTrajectory(parkR);
//                break;
//
//            default: //case middle
//
//                //declare trajectories
//                Trajectory strafeBack = robot.roadRunner.trajectoryBuilder(startPos)
//                .back(25)
//                        .build();
//                Trajectory strafeForward = robot.roadRunner.trajectoryBuilder(strafeBack.end())
//                    .forward(10)
//                        .build();
//                TrajectorySequence turnLeft = robot.roadRunner.trajectorySequenceBuilder(strafeForward.end())
//                    .turn(Math.toRadians(90))
//                        .build();
//                TrajectorySequence splinetoStack = robot.roadRunner.trajectorySequenceBuilder(turnLeft.end())
//                    .splineToConstantHeading(new Vector2d(-60,24), Math.toRadians(180))
//                        .build();
//                TrajectorySequence splinetoBoard = robot.roadRunner.trajectorySequenceBuilder(splinetoStack.end())
//                    .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
//                    .splineToConstantHeading(new Vector2d(40,36), Math.toRadians(0))
//                        .build();
//
//                robot.roadRunner.followTrajectory(strafeBack);
//                sleep(200);
//                robot.roadRunner.followTrajectory(strafeForward);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(turnLeft);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(splinetoStack);
//                sleep(200);
//                robot.roadRunner.followTrajectorySequence(splinetoBoard);
//                robot.aprilTags.update(true);
//                distFromAprilTagX = robot.aprilTags.tagX;
//                distFromAprilTagForward = robot.aprilTags.tagDistance;
//
//                if(distFromAprilTagForward == -1){
//                    distFromAprilTagForward = 27;
//                    distFromAprilTagX = 0;
//                }
//
//                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
//                telemetry.addData("tagx ", robot.aprilTags.tagX);
//                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
//                telemetry.update();
//
//                sleep(500);
//                robot.roadRunner.followTrajectorySequence(splinetoBoard);
//
//
//                Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(splinetoBoard.end())
//                        .strafeTo(new Vector2d(20 + distFromAprilTagForward - 0, 28 - distFromAprilTagX - 2))
//                        .build();
//                robot.roadRunner.followTrajectory(alignAprilTag);
//                //robot.dumper.setPosition(SubsystemConstants.dumperTop);
//
//                Trajectory away = robot.roadRunner.trajectoryBuilder(alignAprilTag.end())
//                        .forward(4)
//                        .build();
//
//                Trajectory park = robot.roadRunner.trajectoryBuilder(away.end())
//                        .strafeLeft(24)
//                        .build();
//
//                sleep(3000);
//
//                robot.dumper.setPosition(0.5);
//
//                robot.roadRunner.followTrajectory(away);
//                robot.roadRunner.followTrajectory(park);
//                break;

            //end case middle



        



    }
}