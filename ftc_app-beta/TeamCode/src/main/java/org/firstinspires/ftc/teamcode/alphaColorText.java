package org.firstinspires.ftc.teamcode;

import android.view.View;
import android.graphics.Color;
import android.app.Activity;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

/**
 * Created by FTC on 2017/2/28.
 */

@TeleOp(name = "alphaColorText")
public class alphacolortext extends OpMode {

    ColorSensor SensorRGB;

    @Override
    public void init () {

        hardwareMap.logDevices();

        SensorRGB = hardwareMap.colorSensor.get("color");

    }

    public void loop () {

        float hsvValues[] = {0F,0F,0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity)
                hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        Color.RGBToHSV((SensorRGB.red() * 255) / 800, (SensorRGB.green() * 255) / 800, (SensorRGB.blue() * 255) / 800, hsvValues);
        telemetry.addData("Clear", SensorRGB.alpha());
        telemetry.addData("Red  ", SensorRGB.red());
        telemetry.addData("Green", SensorRGB.green());
        telemetry.addData("Blue ", SensorRGB.blue());
        telemetry.addData("Hue", hsvValues[0]);

        relativeLayout.post(new Runnable() {
            @Override
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0XFF, values));
            }
        });

        try {
            Thread.sleep(500);
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
