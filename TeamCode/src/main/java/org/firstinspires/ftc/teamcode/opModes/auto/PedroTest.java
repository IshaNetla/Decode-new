package org.firstinspires.ftc.teamcode.opModes.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.subSystems.Intake;
import org.firstinspires.ftc.teamcode.subSystems.Outtake;

@Autonomous(name = "PedroTest", group = "")
public class PedroTest extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private Intake intake;
    private Outtake outtake;

    private final Pose pose1 = new Pose(0, 0, Math.toRadians(90));
    private final Pose pose2 = new Pose(0, 1.5, Math.toRadians(90));
    private final Pose pose3 = new Pose(1.5, 1.5, Math.toRadians(90));
    private final Pose pose4 = new Pose(1.5, 0, Math.toRadians(90));

    private PathChain point1, point2, point3;

    public void buildPaths() {
        point1 = follower.pathBuilder()
                .addPath(new BezierLine(pose1, pose2))
                .setConstantHeadingInterpolation(pose1.getHeading())
                .build();
        point2 = follower.pathBuilder()
                .addPath(new BezierLine(pose2, pose3))
                .setConstantHeadingInterpolation(pose2.getHeading())
                .build();
        point3 = follower.pathBuilder()
                .addPath(new BezierLine(pose3, pose4))
                .setConstantHeadingInterpolation(pose3.getHeading())
                .build();
    }

    //hi
    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(point1);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(point2);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(point3);
                    setPathState(-1);
                }
                break;
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void loop() {
        // These loop the movements of the robot, these must be called continuously in order to work
        follower.update();
        autonomousPathUpdate();
        // Feedback to Driver Hub for debugging
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        follower.setMaxPower(.4);
        follower.setStartingPose(pose1);
        buildPaths();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void stop() {}
}