package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedTopMeepMeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        RoadRunnerBotEntity myBot = null;

        myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(-90)))
                                    //Red Top Right Case
//                                .lineToLinearHeading(new Pose2d(23,-34, Math.toRadians(-90)))
//                                 .forward(15)
//                                .turn(Math.toRadians(-90))
//
//                                .lineToSplineHeading(new Pose2d(48, -30, Math.toRadians(180)))
//
//                                .back(4)
//                                .forward(4)
//
//                                .lineTo(new Vector2d(48, -60))
//                                .lineTo(new Vector2d(60, -60))

                                //Red Top Left Case
//                                .lineToLinearHeading(new Pose2d(0,-34, Math.toRadians(-90)))
//                                 .forward(15)
//                                .turn(Math.toRadians(-90))
//
//                                .lineToSplineHeading(new Pose2d(48, -30, Math.toRadians(180)))
//
//                                .back(4)
//                                .forward(4)
//
//                                .lineTo(new Vector2d(48, -60))
//                                .lineTo(new Vector2d(60, -60))
//
//
//
//                                .build());

                                //Red Top Straight Case
                                .lineToLinearHeading(new Pose2d(12,-32, Math.toRadians(-90)))
                                .forward(7)
                                .turn(Math.toRadians(-90))

                                .lineToSplineHeading(new Pose2d(48, -30, Math.toRadians(180)))

                                .back(4)
                                .forward(4)

                                .lineTo(new Vector2d(48, -60))
                                .lineTo(new Vector2d(60, -60))



                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
}

}
