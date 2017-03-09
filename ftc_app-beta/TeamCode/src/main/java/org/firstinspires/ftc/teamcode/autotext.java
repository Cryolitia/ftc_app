package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Servo;

import for_camera_opmodes.LinearOpModeCamera;

/**
 * Created by cxs on 2017/2/18.
 */

@TeleOp(name = "autoText")
public class autotext extends LinearOpModeCamera {

    DcMotor leftmotor;
    DcMotor rightmotor;
    DcMotor firemotor;

    Servo leftservo;
    Servo rightservo;

    DeviceInterfaceModule cdim;

    int ds2 = 2;

    @Override
    public void runOpMode() throws InterruptedException {
        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        firemotor = hardwareMap.dcMotor.get("fire");

        leftservo = hardwareMap.servo.get("leftservo");
        rightservo = hardwareMap.servo.get("rightservo");

        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        leftservo.setPosition(0);
        rightservo.setPosition(1);

        String colorString = "NONE";

        // linear OpMode, so could do stuff like this too.
        /*
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        */

        if (isCameraAvailable()) {

            setCameraDownsampling(1);
            // parameter determines how downsampled you want your images
            // 8, 4, 2, or 1.
            // higher number is more downsampled, so less resolution but faster
            // 1 is original resolution, which is detailed but slow
            // must be called before super.init sets up the camera

            telemetry.addLine("Wait for camera to finish initializing!");
            telemetry.update();
            startCamera();  // can take a while.
            // best started before waitForStart
            telemetry.addLine("Camera ready!");
            telemetry.update();

            waitForStart();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            leftmotor.setPower(-1);
            rightmotor.setPower(1);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            leftmotor.setPower(0);
            rightmotor.setPower(0);


            for (double i = 0.01; i <= 0.5; i += 0.01) {
                leftservo.setPosition(i);
                rightservo.setPosition(1.0 - i);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            leftservo.setPosition(0);
            rightservo.setPosition(1);


            leftmotor.setPower(-1);
            rightmotor.setPower(1);

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            leftmotor.setPower(0);
            rightmotor.setPower(0);

            if (imageReady()) { // only do this if an image has been returned from the camera
                int redValue = 0;
                int blueValue = 0;
                int greenValue = 0;

                // get image, rotated so (0,0) is in the bottom left of the preview window
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);

                for (int x = 0; x < rgbImage.getWidth(); x++) {
                    for (int y = 0; y < rgbImage.getHeight(); y++) {
                        int pixel = rgbImage.getPixel(x, y);
                        redValue += red(pixel);
                        blueValue += blue(pixel);
                        greenValue += green(pixel);
                    }
                }
                int color = highestColor(redValue, greenValue, blueValue);

                switch (color) {
                    case 0:
                        colorString = "RED";
                        break;
                    case 1:
                        colorString = "GREEN";
                        break;
                    case 2:
                        colorString = "BLUE";
                }

            } else {
                colorString = "NONE";
            }

            telemetry.addData("Color:", "Color detected is: " + colorString);
            telemetry.update();
            sleep(10);

            stopCamera();
        }
    }
}
