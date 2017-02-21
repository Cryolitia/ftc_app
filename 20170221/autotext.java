package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by cxs on 2017/2/18.
 */

@TeleOp(name="autoText", group="OpMode")
public class autotext extends OpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor fire;

    Servo leftservo;
    Servo rightservo;

    @Override
    public void init () {
        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        fire = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        leftservo.setPosition(0);
        rightservo.setPosition(1);
    }

    @Override
    public void start () {


    }

    @Override
    public void loop () {

    }
}
