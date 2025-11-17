package org.firstinspires.ftc.teamcode.opModes.teleOp;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subSystems.Robot;

/** improving
 * teleop, make a button to make lift come back and reset
 *
 *
 *
 *
 *
 */


@TeleOp(name="TeleOp", group="Opmode")
public class teleOpRealMaybe extends LinearOpMode {

    private Robot robot;
    private Telemetry telemetry;
    private Limelight3A limelight;
    HardwareMap map = hardwareMap;
    private IMU imu;

    @Override
    public void runOpMode() {
        Robot robot;
        robot = new Robot(this, this.telemetry);
        waitForStart();

        while (opModeIsActive()) {
            robot.loop();
        }
    }

//    @Override
//    public void init() {
//        limelight = hardwareMap.get(Limelight3A.class, "limelight");
//        limelight.pipelineSwitch(9);
//        imu = hardwareMap.get(IMU.class, "imu");
//        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
//                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
//        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
//    }
//
//    @Override
//    public void start() {
//        limelight.start();
//    }
//
//    @Override
//    public void loop() {
//        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
//        limelight.updateRobotOrientation(orientation.getYaw());
//        LLResult llResult = limelight.getLatestResult();
//        if (llResult != null && llResult.isValid()) {
//            Pose3D botPose = llResult.getBotpose_MT2();
//            telemetry.addData("Tx", llResult.getTx());
//            telemetry.addData("Ty", llResult.getTy());
//            telemetry.addData("Ta", llResult.getTa());
//        }
//
//    }

    }
