package org.firstinspires.ftc.teamcode;

import java.util.Date;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by cxs on 2017/2/3.
 */

@TeleOp(name="text", group="OpMode")
public class text extends OpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor lift;
    DcMotor biglift;
    DcMotor hold;
    DcMotor fire;

    Servo leftservo;
    Servo rightservo;

    @Override
    public void init () {

        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        lift = hardwareMap.dcMotor.get("lift");
        biglift = hardwareMap.dcMotor.get("biglift");
        hold = hardwareMap.dcMotor.get("hold");
        fire = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        leftservo.setPosition(0);
        rightservo.setPosition(1);
    }

    @Override
    public void loop () {

        leftmotor.setPower((gamepad1.left_stick_y-gamepad1.left_stick_x)/2);
        rightmotor.setPower(0-((gamepad1.left_stick_y+gamepad1.left_stick_x)/2));

        if (gamepad1.y) {
            fire.setPower(1);
            try {
                Thread.currentThread().sleep(200);
            }
            catch (Exception e) {fire.setPower(0);}
        }

        else if (gamepad1.x) {
            lift.setPower(-1);
        }
        else if (gamepad1.b) {
            lift.setPower(1);
        }
        else if (gamepad1.a) {
            lift.setPower(0);
        }

        else if (gamepad2.dpad_left) {
            if (leftservo.getPosition()<0.4) {
                leftservo.setPosition(leftservo.getPosition()+0.01);
                rightservo.setPosition(rightservo.getPosition()-0.01);
            }
        }
        else if (gamepad2.dpad_right) {
            if (leftservo.getPosition()>0) {
                leftservo.setPosition(leftservo.getPosition()-0.01);
                rightservo.setPosition(rightservo.getPosition()+0.01);
            }
        }

        else if (gamepad2.y) {
            biglift.setPower(-1);
        }
        else if (gamepad2.a) {
            biglift.setPower(1);
        }
        else if (gamepad2.b) {
            biglift.setPower(0);
        }

        else if (gamepad2.dpad_up) {
            hold.setPower(0.3);
        }
        else if (gamepad2.dpad_down) {
            hold.setPower(-0.3);
        }

        else {
            fire.setPower(0);
            hold.setPower(0);
        }

    }

}
