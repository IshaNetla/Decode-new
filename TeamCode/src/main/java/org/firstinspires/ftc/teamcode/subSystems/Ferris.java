
package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Ferris extends Subsystems {
    private Telemetry telemetry;

    private LinearOpMode opMode;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    private static DcMotorEx ferrisSpin;

    public Ferris(LinearOpMode opMode, Telemetry telemetry) {

        this.telemetry = telemetry;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.opMode = (LinearOpMode) opMode;
        HardwareMap map = opMode.hardwareMap;

        /*
        ferrisSpin = map.get(DcMotorEx.class,"ferris");
        ferrisSpin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ferrisSpin.setDirection(DcMotorSimple.Direction.REVERSE);
        */
    }

    public void update() {
        /*
        if (gamepad2.left_stick_y != 0) {
            ferrisSpin.setPower(.25);
        } else {
            ferrisSpin.setPower(0);
        }
         */
    }
}
