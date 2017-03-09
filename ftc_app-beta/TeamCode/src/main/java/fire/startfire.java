package fire;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by FTC on 2017/3/8.
 */

public class startfire extends Thread {
    private TouchSensor touch;
    private DcMotor firemotor;

    public void run () {

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.init();

    }
    public void SetHardware (TouchSensor settouch, DcMotor setfire) {

        this.touch = settouch;
        this.firemotor = setfire;

    }

    public void init () {
        firemotor.setPower(-1);
        while (!touch.isPressed()) {
            firemotor.setPower(-1);
        }
        if (!touch.isPressed()) {
            firemotor.setPower(0);
        }
    }

}
