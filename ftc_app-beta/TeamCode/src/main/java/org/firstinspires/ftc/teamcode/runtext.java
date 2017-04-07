package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Math.*;

/**
 * Created by FTC on 2017/4/7.
 */

@TeleOp(name = "runText", group = "alpha")
public class runtext extends OpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;

    double x, y, sign, r, s, ax, ay;

    @Override
    public void init () {

        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");

    }

    @Override
    public void loop () {

        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;

        if (x!=0&&y!=0) {
            ax = abs(x);
            ay = abs(y);
            sign = y / ay;

            r = sign * (toDegrees(atan2(ay, ax)) - 45) / 45;
            s = sign * (ax >= ay ? (sqrt(2) * ay) : (sqrt(2) * ax));

            if (x * y > 0) {
                leftmotor.setPower(0-s);
                rightmotor.setPower(r);
            } else if (x * y < 0) {
                leftmotor.setPower(0-r);
                rightmotor.setPower(s);
            }
        }else if (x==0&&y!=0) {
            leftmotor.setPower(-y);
            rightmotor.setPower(y);
        }else if (x!=0&&y==0) {
            leftmotor.setPower(-x);
            rightmotor.setPower(-x);
        }else if (x==0&&y==0) {
            leftmotor.setPower(0);
            rightmotor.setPower(0);
        }

        telemetry.addData("left", x);
        telemetry.addData("right", y);
        telemetry.addData("lmotor", leftmotor.getPower());
        telemetry.addData("rmotor", rightmotor.getPower());

    }

}
