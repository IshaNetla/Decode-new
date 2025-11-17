package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Drivetrain extends Subsystems {
    private Telemetry telemetry;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;


    //I added this so that the claw would work and the slide would rotate

    private LinearOpMode opMode;
    private double drivePower = 0.8;


    public Drivetrain(LinearOpMode opMode, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;

        HardwareMap map = opMode.hardwareMap;


        frontLeft = map.get(DcMotorEx.class, "BR");
        backLeft = map.get(DcMotorEx.class, "FR");
        backRight = map.get(DcMotorEx.class, "FL");
        frontRight = map.get(DcMotorEx.class, "BL");


        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void mecanumDrive(double power){
//I changed x and y which allowed it to switch the left adn right joysticks
        double y = Math.signum(gamepad1.left_stick_y) * Math.pow(gamepad1.left_stick_y, 2);
        double x = Math.signum(-gamepad1.left_stick_x) * Math.pow(-gamepad1.left_stick_x, 2);
        double rx = Math.signum(gamepad1.right_stick_x) * Math.pow(gamepad1.right_stick_x, 2);

        if (gamepad1.dpad_down) {
            y = 0.5;
            x = 0;
            rx = 0;
        } else if (gamepad1.dpad_up) {
            y = -0.5;
            x = 0;
            rx = 0;
        } else if (gamepad1.dpad_left) {
            y = 0;
            x = 0.5;
            rx = 0;
        } else if (gamepad1.dpad_right) {
            y = 0;
            x = -0.5;
            rx = 0;
        }

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeft.setPower(frontLeftPower * power);
        backLeft.setPower(backLeftPower * power);
        frontRight.setPower(frontRightPower * power);
        backRight.setPower(backRightPower * power);

    }



    public void update() {
        mecanumDrive(drivePower);
        if (gamepad1.left_bumper){
            drivePower = 0.3;
        } else {
            drivePower = 1.0;//.9 before for isha changed for anjali
        }
    }

}