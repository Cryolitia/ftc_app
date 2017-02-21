package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.io.ByteArrayOutputStream;

@Disabled
public class getcamera {

        ByteArrayOutputStream baos;
        byte[] rawImage;
        Bitmap bitmap;
        long R=0,G=0,B=0,i=0;

        public void runInPreviewFrame(byte[] data, Camera camera) {
            camera.setOneShotPreviewCallback(null);
            Camera.Size previewSize = camera.getParameters().getPreviewSize();
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;
            YuvImage yuvimage = new YuvImage(
                    data,
                    ImageFormat.NV21,
                    previewSize.width,
                    previewSize.height,
                    null);
            baos = new ByteArrayOutputStream();
            yuvimage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, baos);
            rawImage = baos.toByteArray();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);

            R=0;
            G=0;
            B=0;
            int pixelColor;
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            for (int y=0;y<height;y++) {
                for (int x = 0; x < width; x++) {
                    pixelColor = bitmap.getPixel(x, y);
                    R += Color.red(pixelColor);
                    G += Color.green(pixelColor);
                    B += Color.blue(pixelColor);
                    i++;
                }
            }
            R /= i;
            G /= i;
            B /= i;
        }
    }
