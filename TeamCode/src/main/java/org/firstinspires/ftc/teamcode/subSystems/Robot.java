package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    public Intake intake;
    public Drivetrain drivetrain;
    public Outtake outtake;
    public Ferris ferris;

    private Telemetry telemetry;

    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    long lastPressTime = 0;
    final long debounceTime = 500;
    private boolean justPressed;
    private List<Subsystems> subsystems;

    public Robot(LinearOpMode opMode, Telemetry telemetry) {
        this.subsystems = new ArrayList<>();
        this.telemetry = telemetry;
        this.opMode = opMode;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;

        intake = new Intake(opMode, telemetry);
        drivetrain = new Drivetrain(opMode, telemetry);
        outtake = new Outtake(opMode, telemetry);
        ferris = new Ferris(opMode, telemetry);

        subsystems.add(intake);
        subsystems.add(drivetrain);
        subsystems.add(outtake);
        subsystems.add(ferris);

    }

    public void loop() {
        for (Subsystems s : subsystems) {
            s.update();
        }
    }
}