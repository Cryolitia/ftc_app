package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by cxs on 2017/4/11.
 */

@TeleOp(name = "alpha", group = "fix")
public class alpha extends OpMode {

    DcMotor biglift;
    DcMotor hold;
    Servo leftservo;
    Servo rightservo;

    @Override
    public void init() {

        biglift = hardwareMap.dcMotor.get("biglift");
        hold = hardwareMap.dcMotor.get("hold");
        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up) {
            biglift.setPower(1);
            hold.setPower(1);
            leftservo.setPosition(0.5);
            rightservo.setPosition(0.5);
        } else if (gamepad1.dpad_down) {
            biglift.setPower(-1);
            hold.setPower(-1);
            leftservo.setPosition(0);
            rightservo.setPosition(1);
        } else if (gamepad1.b) {
            biglift.setPower(0);
            hold.setPower(0);
        }

    }

}
