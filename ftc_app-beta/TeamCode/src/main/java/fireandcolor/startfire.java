package fireandcolor;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by FTC on 2017/3/8.
 */

@Disabled
public class startfire {
    private TouchSensor touch;
    private DcMotor firemotor;

    private boolean haveinit = false;

    public void run() {

        if (!haveinit) this.init();
        while (touch.isPressed()) {
            firemotor.setPower(1);
        }
        firemotor.setPower(0);
        haveinit = false;
        this.init();
    }

    public void SetHardware(TouchSensor settouch, DcMotor setfire) {

        this.touch = settouch;
        this.firemotor = setfire;

    }

    public void init() {
        while (!touch.isPressed()) {
            firemotor.setPower(-0.5);
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firemotor.setPower(1);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firemotor.setPower(0);
        haveinit = true;
    }

    public void release() {

        if (haveinit) {
            while (touch.isPressed()) {
                firemotor.setPower(1);
            }
            firemotor.setPower(0);
        }

    }

}
