package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import fireandcolor.startfire;

/**
 * Created by cxs on 2017/2/18.
 */

@TeleOp(name = "Auto")
public class auto extends LinearOpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor firemotor;

    Servo leftservo;
    Servo rightservo;

    TouchSensor touch;

    DeviceInterfaceModule cdim;

    startfire f = new startfire();

    int ds2 = 2;

    @Override
    public void runOpMode() throws InterruptedException {
        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        firemotor = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        touch = hardwareMap.touchSensor.get("touch");

        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        f.SetHardware(touch, firemotor);

        cdim.setLED(0, true);
        cdim.setLED(1, true);

        waitForStart();

        sleep(500);

        leftmotor.setPower(-1);
        rightmotor.setPower(1);

        sleep(1000);

        leftmotor.setPower(0);
        rightmotor.setPower(0);

        f.run();


        for (double i = 0.01; i <= 0.5; i += 0.01) {
            leftservo.setPosition(i);
            rightservo.setPosition(1.0 - i);

            sleep(25);

        }

        sleep(500);

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        f.run();

        leftmotor.setPower(-1);
        rightmotor.setPower(1);

        sleep(1400);

        leftmotor.setPower(0);
        rightmotor.setPower(0);


    }
}
