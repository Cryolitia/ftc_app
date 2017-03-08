package fire;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by FTC on 2017/3/8.
 */

public class fire {
    public static void fire (TouchSensor touch, DcMotor fire) {

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
