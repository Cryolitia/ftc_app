package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FTC on 2017/3/9.
 */

@TeleOp(name = "alphaFix", group = "fix")
public class alphafix extends OpMode {

    DcMotor firemotor;

    public void init() {
        firemotor = hardwareMap.dcMotor.get("fire");
    }

    public void loop() {
        if (gamepad1.dpad_up) firemotor.setPower(1);
        else if (gamepad1.dpad_down) firemotor.setPower(-1);
        else firemotor.setPower(0);
    }

}
