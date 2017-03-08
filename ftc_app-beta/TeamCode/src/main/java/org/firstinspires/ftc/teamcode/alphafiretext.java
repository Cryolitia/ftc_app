package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import fire.fire;

/**
 * Created by FTC on 2017/3/8.
 */

@TeleOp(name = "alphaFireText")
public class alphafiretext extends OpMode {

    TouchSensor touch;
    DcMotor firemotor;

    @Override
    public void init () {

        touch = hardwareMap.touchSensor.get("touch");
        firemotor = hardwareMap.dcMotor.get("fire");

    }

    @Override
    public void loop () {

        if (gamepad1.dpad_up) firemotor.setPower(-1);
        else if (gamepad1.dpad_down) firemotor.setPower(1);
        else firemotor.setPower(0);
        telemetry.addData("", touch.isPressed());
        if (gamepad1.x) fire.fire(touch, firemotor);
    }

}
