package org.firstinspires.ftc.teamcode.opModes.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
@Configurable // Panels
public class v1 extends OpMode {

    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState = 0; // Current autonomous path state (state machine)

    private Timer pathTimer, actionTimer, opmodeTimer;

    private PathChain shooting1;
    public PathChain pickup1a;
    public PathChain pickup1b;
    public PathChain gate;
    public PathChain shooting2;
    public PathChain pickup2a, pickup2b;
    public PathChain shooting3;

    private final Pose startPose = new Pose(54, 8, Math.toRadians(-90));
    private final Pose shootingPose = new Pose(57, 9, Math.toRadians(-75));
    private final Pose shootingClosePose = new Pose(57, 55, Math.toRadians(-45));

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(54, 8, Math.toRadians(-90)));

        buildPaths(); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public void buildPaths() {
        shooting1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(54, 8), new Pose(57.000, 9))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-75))
                    .build();

            pickup1a = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(shootingPose, new Pose(40.000, 40))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-75), Math.toRadians(-180))
                    .build();

            pickup1b = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(40.000, 40), new Pose(30, 40))
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            gate = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(30, 40), new Pose(24, 47))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .build();

            shooting2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(new Pose(24, 47), new Pose(49, 33), new Pose(57, 55))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-45))
                    .build();

            pickup2a = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(57.000, 55), new Pose(40, 57))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(-180))
                    .build();

              pickup2b = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(40, 57), new Pose(30, 57))
                     )
                    .setTangentHeadingInterpolation()
                    .build();

            shooting3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(14.000, 25.000), new Pose(57.000, 25.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(115))
                    .build();
        }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(shooting1);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(pickup1a);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(pickup1b);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(gate);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(shooting2);
                    setPathState(5);
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2a);
                    setPathState(6);
                }
                break;
            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(pickup2b);
                    setPathState(-1);
                }
                break;
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
}