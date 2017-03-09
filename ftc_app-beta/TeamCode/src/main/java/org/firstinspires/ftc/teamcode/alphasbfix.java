package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by cxs on 2017/3/9.
 */

@TeleOp(name = "alphaTextForSB")
public class alphasbfix extends LinearOpMode {

    DcMotor firemotor;
    TouchSensor touch;

    @Override
    public void runOpMode () throws InterruptedException {

        firemotor = hardwareMap.dcMotor.get("fire");
        touch = hardwareMap.touchSensor.get("touch");

        waitForStart();

        while (!touch.isPressed()) {
            firemotor.setPower(-1);
        }
        firemotor.setPower(0);

    }

}
