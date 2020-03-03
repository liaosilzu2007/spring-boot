package com.lzumetal.springboot.zxing.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ZxingUtil {

    private static final int IMAGE_WIDTH = 80;
    private static final int IMAGE_HEIGHT = 80;
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    private static final int FRAME_WIDTH = 2;



    public static void encodeQrcodeForFile(String contents, File file) {
        encodeBarcodeForFile(contents, "QRCode", file);
    }


    public static void encodeBarcodeForFile(String contents, String code, File file) {
        BarcodeFormat bf = BarcodeFormat.CODE_128;
        if (code != null && code.equals("QRCode")) {
            bf = BarcodeFormat.QR_CODE;
        }
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.MARGIN, 1);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, bf, 1940, 1940, hints);
            FileOutputStream os = new FileOutputStream(file);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
