package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import fireandcolor.startfire;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

/**
 * Created by cxs on 2017/2/3.
 */

@TeleOp(name = "Manual")
public class manual extends LinearOpMode {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor lift;
    DcMotor biglift;
    DcMotor hold;
    DcMotor firemotor;

    Servo leftservo;
    Servo rightservo;


    TouchSensor touch;

    DeviceInterfaceModule cdim;

    double xa, ya, xb, yb, xc, yc, xd, yd, sign, r, s, axc, ayc, axd, ayd;

    startfire f = new startfire();

    @Override
    public void runOpMode() {

        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        lift = hardwareMap.dcMotor.get("lift");
        biglift = hardwareMap.dcMotor.get("biglift");
        hold = hardwareMap.dcMotor.get("hold");
        firemotor = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        touch = hardwareMap.touchSensor.get("touch");

        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        f.SetHardware(touch, firemotor);

        waitForStart();

        leftservo.setPosition(0);
        rightservo.setPosition(1);


        while (opModeIsActive()) {

            xa = gamepad1.left_stick_x;
            ya = gamepad1.left_stick_y;
            xb = gamepad2.left_stick_x;
            yb = gamepad2.left_stick_y;

            xc = gamepad1.right_stick_x;
            yc = gamepad1.right_stick_y;
            xd = gamepad2.right_stick_x;
            yd = gamepad2.right_stick_y;

            if (xa != 0 || ya != 0) {
            leftmotor.setPower((ya - xa) / 2);
                rightmotor.setPower(0 - ((ya + xa) / 2));
            }
            else if (xb != 0 || yb != 0) {
                rightmotor.setPower((yb - xb) / 2);
                leftmotor.setPower(0 - ((yb + xb) / 2));
            }
            else if (xc != 0 || yc != 0) {
                if (xc != 0 && yc != 0) {
                    axc = abs(xc);
                    ayc = abs(yc);
                    sign = yc / ayc;
                    r = sign * (toDegrees(atan2(ayc, axc)) - 45) / 45;
                    s = sign * (axc >= ayc ? (sqrt(2) * ayc) : (sqrt(2) * axc));
                    if (xc * yc > 0) {
                        leftmotor.setPower(r);
                        rightmotor.setPower(-s);
                    } else if (xc * yc < 0) {
                        leftmotor.setPower(s);
                        rightmotor.setPower(-r);
                    }
                } else if (xc == 0) {
                    leftmotor.setPower(yc);
                    rightmotor.setPower(-yc);
                } else {
                    leftmotor.setPower(-xc);
                    rightmotor.setPower(-xc);
                }
            }
            else if (xd != 0 || yd != 0) {
                if (xd != 0 && yd != 0) {
                    axd = abs(xd);
                    ayd = abs(yd);
                    sign = yd / ayd;
                    r = sign * (toDegrees(atan2(ayd, axd)) - 45) / 45;
                    s = sign * (axd >= ayd ? (sqrt(2) * ayd) : (sqrt(2) * axd));
                    if (xd * yd > 0) {
                    leftmotor.setPower(-s);
                        rightmotor.setPower(r);
                    } else if (xd * yd < 0) {
                        leftmotor.setPower(-r);
                        rightmotor.setPower(s);
                    }
                } else if (xd == 0) {
                    leftmotor.setPower(-yd);
                    rightmotor.setPower(yd);
                } else {
                leftmotor.setPower(-xd);
                    rightmotor.setPower(-xd);
                }
            }
            else {
                leftmotor.setPower(0);
                rightmotor.setPower(0);
            }


            if (gamepad1.y) {
                new Thread(f).start();
                sleep(500);
            } else if (gamepad1.x) {
                lift.setPower(-1);
            } else if (gamepad1.b) {
                lift.setPower(1);
            } else if (gamepad1.a) {
                while (gamepad1.a) {
                    if (leftservo.getPosition() < 0.5) {
                        leftservo.setPosition(leftservo.getPosition() + 0.02);
                        rightservo.setPosition(rightservo.getPosition() - 0.02);
                        sleep(10);
                    }
                    telemetry.addData("left servo", leftservo.getPosition());
                    telemetry.addData("right servo", rightservo.getPosition());
                }
                leftservo.setPosition(0);
                rightservo.setPosition(1);
            } else if (gamepad2.y) {
                biglift.setPower(-1);
            } else if (gamepad2.a) {
                biglift.setPower(1);
            } else if (gamepad2.dpad_up) {
                hold.setPower(0.3);
            } else if (gamepad2.dpad_down) {
                hold.setPower(-0.3);
            } else {
                hold.setPower(0);
                lift.setPower(0);
                biglift.setPower(0);
            }


            telemetry.addData("left motor", leftmotor.getPower());
            telemetry.addData("right motor", rightmotor.getPower());
            telemetry.addData("left servo", leftservo.getPosition());
            telemetry.addData("right servo", rightservo.getPosition());
            telemetry.addData("leftDistance", cdim.getAnalogInputVoltage(0));
            telemetry.addData("leftDistance", cdim.getAnalogInputVoltage(1));

        }

        f.release();
        firemotor.setPower(0);
        Thread.interrupted();
        return;
    }


}
