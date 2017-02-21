package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by cxs on 2017/2/3.
 */

@TeleOp(name = "smartXmjManualText", group = "OpMode")
public class manualtext extends OpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor lift;
    DcMotor biglift;
    DcMotor hold;
    DcMotor fire;

    Servo leftservo;
    Servo rightservo;

    double x, y;

    @Override
    public void init() {

        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        lift = hardwareMap.dcMotor.get("lift");
        biglift = hardwareMap.dcMotor.get("biglift");
        hold = hardwareMap.dcMotor.get("hold");
        fire = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

    }

    @Override
    public void start() {

        leftservo.setPosition(0);
        rightservo.setPosition(1);

    }

    @Override
    public void loop() {

        x = Math.pow(gamepad1.left_stick_x, 3);
        y = Math.pow(gamepad1.left_stick_y, 3);

        if (gamepad1.dpad_up == false && gamepad1.dpad_down == false && gamepad1.dpad_left == false && gamepad1.dpad_right == false) {
            leftmotor.setPower((y - x) / 2);
            rightmotor.setPower(0 - ((y + x) / 2));
        }


        if (gamepad1.y) {
            fire.setPower(1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fire.setPower(0);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (gamepad1.b) {
            lift.setPower(-1);
        } else if (gamepad1.x) {
            lift.setPower(1);
        } else if (gamepad1.a) {
            lift.setPower(0);
        } else if (gamepad2.y) {
            biglift.setPower(-1);
        } else if (gamepad2.a) {
            biglift.setPower(1);
        } else if (gamepad2.b) {
            biglift.setPower(0);
        } else if (gamepad2.dpad_up) {
            hold.setPower(0.3);
        } else if (gamepad2.dpad_down) {
            hold.setPower(-0.3);
        } else if (gamepad2.dpad_left) {
            if (leftservo.getPosition() < 0.5) {
                leftservo.setPosition(leftservo.getPosition() + 0.02);
                rightservo.setPosition(rightservo.getPosition() - 0.02);
            }
        } else if (gamepad2.dpad_right) {
            if (leftservo.getPosition() > 0) {
                leftservo.setPosition(leftservo.getPosition() - 0.02);
                rightservo.setPosition(rightservo.getPosition() + 0.02);
            }
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
            fire.setPower(0);
            hold.setPower(0);
        }

        telemetry.addData("left motor", leftmotor.getPower());
        telemetry.addData("right motor", rightmotor.getPower());
        telemetry.addData("left servo", leftservo.getPosition());
        telemetry.addData("right servo", rightservo.getPosition());

    }

}
