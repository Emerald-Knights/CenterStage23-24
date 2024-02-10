package org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class Dump extends Action {

    private int lastAction = 0;
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void start() {
        timer.reset();
        Robot.getInstance().housing.dumperState = SubsystemConstants.DumperStates.MIDDLE;
    }

    @Override
    public void update() {
        if(timer.milliseconds() >= SubsystemConstants.timingsForDump[2]) {
            Robot.getInstance().housing.pixelHolderState = SubsystemConstants.PixelHolderStates.RIGHT;
        }
        else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[1]) {
            Robot.getInstance().housing.dumperState = SubsystemConstants.DumperStates.LOW;
        }
        else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[0]) {
            Robot.getInstance().housing.pixelHolderState = SubsystemConstants.PixelHolderStates.LEFT;
        }
    }

    @Override
    public void end() {

    }
}