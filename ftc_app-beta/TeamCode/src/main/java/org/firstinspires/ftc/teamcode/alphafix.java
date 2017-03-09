package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by cxs on 2017/3/9.
 */

@TeleOp (name = "alphaFix")
public class alphafix extends OpMode {

    DcMotor firemotor;

    @Override
    public void init () {

        firemotor = hardwareMap.dcMotor.get("fire");

    }

    @Override
    public void loop () {

        if (gamepad1.dpad_up) firemotor.setPower(1);
        else if (gamepad1.dpad_down) firemotor.setPower(-1);
        else firemotor.setPower(0);

    }

}
