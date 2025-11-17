package org.firstinspires.ftc.teamcode.opModes.auto;//package org.firstinspires.ftc.teamcode.opModes.auto;
//
//import com.bylazar.configurables.annotations.Configurable;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierCurve;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//@Autonomous(name = "Pedro Pathing Autonomous", group = "Autonomous")
//@Configurable // Panels
//public class v1 extends OpMode {
//
//    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
//    public Follower follower; // Pedro Pathing follower instance
//    private int pathState; // Current autonomous path state (state machine)
//    private Paths paths; // Paths defined in the Paths class
//
//    @Override
//    public void init() {
//        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
//
//        follower = Constants.createFollower(hardwareMap);
//        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));
//
//        paths = new Paths(follower); // Build paths
//
//        panelsTelemetry.debug("Status", "Initialized");
//        panelsTelemetry.update(telemetry);
//    }
//
//    @Override
//    public void loop() {
//        follower.update(); // Update Pedro Pathing
//        pathState = autonomousPathUpdate(); // Update autonomous state machine
//
//        // Log values to Panels and Driver Station
//        panelsTelemetry.debug("Path State", pathState);
//        panelsTelemetry.debug("X", follower.getPose().getX());
//        panelsTelemetry.debug("Y", follower.getPose().getY());
//        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
//        panelsTelemetry.update(telemetry);
//    }
//
//    public static class Paths {
//
//        public PathChain shooting1;
//        public PathChain pickup1a;
//        public PathChain pickup1b;
//        public PathChain gate;
//        public PathChain shooting2;
//        public PathChain pickup2;
//        public PathChain shooting3;
//
//        public Paths(Follower follower) {
//            shooting1 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(54.500, 13.500), new Pose(57.000, 25.000))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(115))
//                    .build();
//
//            pickup1a = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(57.000, 25.000), new Pose(40.000, 63.000))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(115), Math.toRadians(180))
//                    .build();
//
//            pickup1b = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(40.000, 63.000), new Pose(23.000, 63.000))
//                    )
//                    .setTangentHeadingInterpolation()
//                    .build();
//
//            gate = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(23.000, 63.000), new Pose(14.000, 70.000))
//                    )
//                    .setConstantHeadingInterpolation(Math.toRadians(180))
//                    .build();
//
//            shooting2 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierCurve(
//                                    new Pose(14.000, 70.000),
//                                    new Pose(43.676, 53.838),
//                                    new Pose(57.000, 25.000)
//                            )
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(115))
//                    .build();
//
//            pickup2 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(57.000, 25.000), new Pose(14.000, 25.000))
//                    )
//                    .setTangentHeadingInterpolation()
//                    .build();
//
//            shooting3 = follower
//                    .pathBuilder()
//                    .addPath(
//                            new BezierLine(new Pose(14.000, 25.000), new Pose(57.000, 25.000))
//                    )
//                    .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(115))
//                    .build();
//        }
//    }
//
//    public int autonomousPathUpdate() {
////        switch(pathState) {
////            case 0:
////        }
////    }
//}