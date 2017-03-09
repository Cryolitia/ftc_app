package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import fire.startfire;

/**
 * Created by cxs on 2017/2/18.
 */

@TeleOp(name = "autoText")
public class autotext extends LinearOpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor fire;

    Servo leftservo;
    Servo rightservo;

    @Override
    public void runOpMode() throws InterruptedException {
        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        fire = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        waitForStart();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        leftmotor.setPower(-1);
        rightmotor.setPower(1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        leftmotor.setPower(0);
        rightmotor.setPower(0);
        fire.setPower(1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fire.setPower(0);

        for (double i = 0.01; i <= 0.5; i += 0.01) {
            leftservo.setPosition(i);
            rightservo.setPosition(1.0 - i);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        fire.setPower(1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fire.setPower(0);
        leftmotor.setPower(-1);
        rightmotor.setPower(1);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        leftmotor.setPower(0);
        rightmotor.setPower(0);


    }
}
