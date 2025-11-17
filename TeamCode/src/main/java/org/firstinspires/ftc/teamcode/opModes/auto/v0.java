package org.firstinspires.ftc.teamcode.opModes.auto;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@Autonomous
public class v0 extends OpMode{
    private Limelight3A limelight3A;
    private int pipeline;
    int[] pipelines = {7, 8, 9};     // Your pipelines, each for a different tag
    int currentPipelineIndex = 0;   // Index into the array
    long lastSwitchTime = 0;
    long switchInterval = 500;      // Minimum time between pipeline switches (ms)
    boolean tagLocked = false;

    @Override
    public void init() {
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.start();
        pipeline = 9;
        limelight3A.pipelineSwitch(pipeline);
    }

    @Override
    public void init_loop() {
        LLResult llResult = limelight3A.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            tagLocked = true;

            Pose3D botPose = llResult.getBotpose();
            //telemetry.addData("Target X", llResult.getTx());
            //telemetry.addData("Target y", llResult.getTy());
            //telemetry.addData("Target Area", llResult.getTa());
            //telemetry.addData("BotPose", botPose.toString());
            //telemetry.addData("Yaw", botPose.getOrientation().getYaw());
            telemetry.addData("pipeline", llResult.getPipelineIndex());
            telemetry.addData("tag number", llResult.getPipelineIndex()+14);
            if (llResult.getPipelineIndex() == 7) {
                telemetry.addLine("pattern: GPP");
            } else if (llResult.getPipelineIndex() == 8) {
                telemetry.addLine("pattern: PGP");
            } else if (llResult.getPipelineIndex() == 9) {
                telemetry.addLine("pattern: PPG");
            }
        } else {
            telemetry.addLine("haha not this pipeline!");
            telemetry.addData("current pipeline", llResult.getPipelineIndex());

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSwitchTime > switchInterval) {
                currentPipelineIndex = (currentPipelineIndex + 1) % pipelines.length;
                limelight3A.pipelineSwitch(pipelines[currentPipelineIndex]);
                lastSwitchTime = currentTime;
                telemetry.addData("Switched to pipeline", pipelines[currentPipelineIndex]);
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void loop (){
        LLResult llResult = limelight3A.getLatestResult();

        telemetry.addData("tag", llResult.getPipelineIndex());
    }
}
