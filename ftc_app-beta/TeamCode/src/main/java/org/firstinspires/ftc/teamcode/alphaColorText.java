package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.LED;

@TeleOp (name = "colorText")
public class alphacolortext extends LinearOpMode {
    ColorSensor SensorRGB;
    DeviceInterfaceModule cdim;

    static final int LED_CHANNEL = 5;

    @Override
    public void runOpMode () throws InterruptedException {

        hardwareMap.logDevices();

        cdim = hardwareMap.deviceInterfaceModule.get("dim");

        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);

        SensorRGB = hardwareMap.colorSensor.get("color");

        boolean bEnabled = true;

        cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);

        waitOneFullHardwareCycle();

        waitForStart();

        float hsvValues[] = {0F,0F,0F};

        final float values[] = hsvValues;

        final View relativeLayout = ((Activity)
                hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        boolean bPrevState = false;
        boolean bCurrState = false;

        while (opModeIsActive()) {

            bCurrState = gamepad1.x || gamepad2.x;
            if (bCurrState == true && bCurrState != bPrevState) {

                DbgLog.msg("MY_DEUG - x button was pressed!");

                bPrevState = bCurrState;

                bEnabled = true;

                cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);

            } else  if (bCurrState == false && bCurrState != bPrevState) {

                DbgLog.msg("MY_DEUG - x button was released!");

                bPrevState = bCurrState;

                bEnabled = false;

                cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);

            }

            Color.RGBToHSV((SensorRGB.red()*255)/800, (SensorRGB.green()*255)/800, (SensorRGB.blue()*255)/800, hsvValues);

            telemetry.addData("Clear", SensorRGB.alpha());
            telemetry.addData("Red  ", SensorRGB.red());
            telemetry.addData("Green", SensorRGB.green());
            telemetry.addData("Blue ", SensorRGB.blue());
            telemetry.addData("Hue  ", hsvValues[0]);

            relativeLayout.post(new Runnable () {
                public void run () {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });
        waitOneFullHardwareCycle();
        }

    }
}