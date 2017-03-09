package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import fire.startfire;

/**
 * Created by cxs on 2017/2/3.
 */

@TeleOp(name = "manualText")
public class manualtext extends OpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor lift;
    DcMotor biglift;
    DcMotor hold;
    DcMotor firemotor;

    Servo leftservo;
    Servo rightservo;

    TouchSensor touch;

    double xa, ya, xb, yb;

    startfire f = new startfire();

    @Override
    public void init() {

        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        lift = hardwareMap.dcMotor.get("lift");
        biglift = hardwareMap.dcMotor.get("biglift");
        hold = hardwareMap.dcMotor.get("hold");
        firemotor = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        touch = hardwareMap.touchSensor.get("touch");

        f.SetHardware(touch, firemotor);

    }

    @Override
    public void start() {

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        f.init();

    }

    @Override
    public void loop() {

        xa = gamepad1.left_stick_x;
        ya = gamepad1.left_stick_y;
        xb = gamepad2.left_stick_x;
        yb = gamepad2.left_stick_y;

        if (gamepad1.dpad_up == false && gamepad1.dpad_down == false && gamepad1.dpad_left == false && gamepad1.dpad_right == false) {

            if (xa != 0 || ya != 0) {
                leftmotor.setPower((ya - xa) / 2);
                rightmotor.setPower(0 - ((ya + xa) / 2));
            }

            if (xb != 0 || yb != 0) {
                rightmotor.setPower((yb - xb) / 2);
                leftmotor.setPower(0 - ((yb + xb) / 2));
            }

            if (xa == 0 && xb == 0 && ya == 0 && yb == 0) {
                leftmotor.setPower(0);
                rightmotor.setPower(0);
            }
        }

        if (gamepad1.y) {
            f.start();
        }
        else if (gamepad1.x) {
            lift.setPower(-1);
        } else if (gamepad1.b) {
            lift.setPower(1);
        } else if (gamepad1.a) {
            while (gamepad1.a) {
                if (leftservo.getPosition() < 0.5) {
                    leftservo.setPosition(leftservo.getPosition() + 0.02);
                    rightservo.setPosition(rightservo.getPosition() - 0.02);
                }
                telemetry.addData("left servo", leftservo.getPosition());
                telemetry.addData("right servo", rightservo.getPosition());
            }
            leftservo.setPosition(0);
            rightservo.setPosition(1);
        } else if (gamepad2.y) {
            biglift.setPower(-1);
        } else if (gamepad2.a) {
            biglift.setPower(1);
        } else if (gamepad2.dpad_up) {
            hold.setPower(0.3);
        } else if (gamepad2.dpad_down) {
            hold.setPower(-0.3);
        } else if (gamepad1.dpad_up) {
            leftmotor.setPower(-1);
            rightmotor.setPower(1);
        } else if (gamepad1.dpad_down) {
            leftmotor.setPower(1);
            rightmotor.setPower(-1);
        } else if (gamepad1.dpad_left) {
            leftmotor.setPower(1);
            rightmotor.setPower(1);
        } else if (gamepad1.dpad_right) {
            leftmotor.setPower(-1);
            rightmotor.setPower(-1);
        } else {
            hold.setPower(0);
            lift.setPower(0);
            biglift.setPower(0);
        }

        telemetry.addData("left motor", leftmotor.getPower());
        telemetry.addData("right motor", rightmotor.getPower());
        telemetry.addData("left servo", leftservo.getPosition());
        telemetry.addData("right servo", rightservo.getPosition());

    }

}
