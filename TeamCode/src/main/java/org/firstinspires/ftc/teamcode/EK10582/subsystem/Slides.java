package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem{


    //not init
    public double slideControl;

    public double speed;

    private final int SLIDEMAX = SubsystemConstants.SLIDEMAX;

    private int slideTarget;
    public boolean freeControl = true;
    public double autoSlidePower = 0;


    //TODO: change vals to actual pos
    public final int[] slidepos = SubsystemConstants.slidepos;

    //why is boolean auton a parameter
    @Override
    public void init(boolean auton){
       speed = SubsystemConstants.speed;
    }

    @Override
    public void update(boolean auton){
        if (auton) {
            return;
        }

//        failsafes: only run in one direction if it hits a max
//        if(Robot.getInstance().slide1.getCurrentPosition() <= 0 || (slideUp - slideDown) < 0){
//            Robot.getInstance().slide1.setPower(0);
//            Robot.getInstance().slide2.setPower(0);
//        }
//        else if (Robot.getInstance().slide1.getCurrentPosition() >= SLIDEMAX || (slideUp - slideDown) >0){
//            Robot.getInstance().slide1.setPower(0);
//            Robot.getInstance().slide2.setPower(0);
//        }
//        else{
        double slidePower = slideControl * speed;
        if(slidePower == 0 && getSlidePosition() < -1400) {
            slidePower = 0.12;
        }
        if(freeControl) {
            Robot.getInstance().slide1.setPower(slidePower);
            Robot.getInstance().slide2.setPower(slidePower);
        } else {
            Robot.getInstance().slide1.setPower(autoSlidePower);
            Robot.getInstance().slide2.setPower(autoSlidePower);
        }
    }

    @Override
    public void stop(){
    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("target Position: ", slideTarget);
        telemetry.addData("slideControl", slideControl);
        telemetry.addData("autoSlidePower", autoSlidePower);
        telemetry.addData("freeControl", freeControl);
        telemetry.addData("slide position", getSlidePosition());
    }


    public void setSlidePreset(int position){
        slideTarget = slidepos[position];
        Robot.getInstance().slide1.setTargetPosition(slideTarget);
    }

    public double getSlidePosition() {
        return Robot.getInstance().slide1.getCurrentPosition();
    }

}