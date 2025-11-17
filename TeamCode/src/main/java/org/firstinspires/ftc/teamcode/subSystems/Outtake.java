package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outtake extends Subsystems {
    private Telemetry telemetry;

    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;


    private static DcMotorEx outtakeSpinBottom;
    private static DcMotorEx outtakeSpinTop;

    private static Servo move;

//    double y = -gamepad2.left_stick_y; // invert if needed
//    double deadband = 0.1;

    public Outtake(LinearOpMode opMode, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.opMode = (LinearOpMode) opMode;
        HardwareMap map = opMode.hardwareMap;

        outtakeSpinBottom = map.get(DcMotorEx.class,"bottom");
        outtakeSpinBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeSpinBottom.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeSpinTop = map.get(DcMotorEx.class,"top");
        outtakeSpinTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeSpinTop.setDirection(DcMotorSimple.Direction.REVERSE);
        move = map.get(Servo.class, "move");
    }

    public static void outtakeOutRPM(double rpm){
        double TICKS_PER_REV = 27;
        outtakeSpinBottom.setVelocity(rpm * TICKS_PER_REV / 60);
        outtakeSpinTop.setVelocity(rpm * TICKS_PER_REV / 60);
    }

//ticks per rev = 27
    public static void outtakeHold(){
        outtakeSpinBottom.setPower(0);
        outtakeSpinTop.setPower(0);
    }

    public void update() {
//        if(gamepad2.left_stick_y > 0.1) {
//            outtakeIn();
//        } else if(gamepad2.left_stick_y < -0.1) {
//            outtakeOut();
//        } else {
//            outtakeHold();
//        }
        if (gamepad2.dpad_up) {
            outtakeOutRPM(2000); //far
        } else if (gamepad2.dpad_left) {
            outtakeOutRPM(2100); //close
        } else if (gamepad2.dpad_down) {
            outtakeOutRPM(2250); //close
        } else if (gamepad2.dpad_right) {
            outtakeOutRPM(2500); //close
        } else {
            outtakeHold();
        }

        if (gamepad2.y) {
            move.setPosition(0);
        } else if (gamepad2.b){
            move.setPosition(1);
        } else{
            move.setPosition(0.5);
        }


    }
}
