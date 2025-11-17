package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends Subsystems {
    private Telemetry telemetry;

    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    private static DcMotorEx intakeSpin;
//    long lastPressTime = 0;
//    final long debounceTime = 500;
//    private boolean justPressed;
//    private List<subSystems> subsystems;
//
//    public static Servo intake1;
//    public static Servo intake2;

    public Intake(LinearOpMode opMode, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.opMode = (LinearOpMode) opMode;
        HardwareMap map = opMode.hardwareMap;

        intakeSpin = map.get(DcMotorEx.class,"intakeSpin");
        intakeSpin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeSpin.setDirection(DcMotorSimple.Direction.REVERSE);
//        intake1 = map.get(Servo.class, "Intake1");
//        intake2 = map.get(Servo.class, "I2");
    }

    public static void intakeIn(){
        intakeSpin.setPower(-1);
    }

    public static void intakeOut(){
        intakeSpin.setPower(1);
    }

    public static void intakeHold(){
        intakeSpin.setPower(0);
    }

//    public static void pickUp() {
//        intake1.setPosition(0);
//        intake2.setPosition(1);
//    }
//
//    public static void getOut() {
//        intake1.setPosition(1);
//        intake2.setPosition(0);
//    }

//    public static void stopIntake() {
//        intake1.setPosition(0.5);
//        intake2.setPosition(0.5);
//    }

    public void update() {
        if(gamepad2.right_bumper) {
            intakeIn();
        } else if(gamepad2.left_bumper) {
            intakeOut();
        } else {
            intakeHold();
        }

    }
}
