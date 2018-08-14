package com.lzumetal.springboot.zxing.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZxingTool {
    
    private static final int IMAGE_WIDTH = 80;  
    private static final int IMAGE_HEIGHT = 80;  
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    private static final int FRAME_WIDTH = 2;  
    /**
     * 生成条形码
     * @param contents 条形码内容
     * @param width 条形码宽度
     * @param height 条形码高度
     * @return 
     */
    public static BufferedImage encodeBarcode(String contents, int width, int height, String code) {
        BarcodeFormat bf = BarcodeFormat.CODE_128;
        if (code != null && code.equals("QRCode")) {
            bf = BarcodeFormat.QR_CODE;
        }
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        BufferedImage barcode = null;
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, bf, codeWidth, height, hints);
            barcode = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barcode;
    }
    
    /**
     * 生成条形码
     * @param contents 条形码内容
     * @param width 条形码宽度
     * @param height 条形码高度
     * @return 
     */
    public static void encodeBarcodeForFile(String contents, String code, File file) {
        BarcodeFormat bf = BarcodeFormat.CODE_128;
        if (code != null && code.equals("QRCode")) {
            bf = BarcodeFormat.QR_CODE;
        }
//        int codeWidth = 3 + // start guard
//                (7 * 6) + // left bars
//                5 + // middle guard
//                (7 * 6) + // right bars
//                3; // end guard
//        codeWidth = Math.max(codeWidth, width);
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, bf, 1000, 1000, hints);
            FileOutputStream os = new FileOutputStream(file);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 生成条形码
     * @param contents 条形码内容 默认
     * @param width 条形码宽度
     * @param height 条形码高度
     * @return 
     */
    public static void encodeBarcodeForFileYilabao(String contents, String code, File file, int width) {
        BarcodeFormat bf = BarcodeFormat.CODE_128;
        if (code != null && code.equals("QRCode")) {
            bf = BarcodeFormat.QR_CODE;
        }
//        int codeWidth = 3 + // start guard
//                (7 * 6) + // left bars
//                5 + // middle guard
//                (7 * 6) + // right bars
//                3; // end guard
//        codeWidth = Math.max(codeWidth, width);
        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, bf, width, width, hints);
            FileOutputStream os = new FileOutputStream(file);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /** 
     * 二维码绘制logo 
     * @param twodimensioncodeImg 二维码图片文件 
     * @param logoImg logo图片文件 
     * */  
public static boolean createQrCodeLogo(String path, String content){
    BufferedImage twodimensioncode = null;
    OutputStream outputStream = null;
    try{  
        outputStream = new FileOutputStream(new File(path));
         //设置二维码纠错级别
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串  
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 614, 614, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();  
        twodimensioncode = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = twodimensioncode.createGraphics();
        Graphics2D graphics = (Graphics2D) twodimensioncode.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);  
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i-100, j-100, 1, 1);  
                }
            }
        }
//        //读取logo图片  
//        BufferedImage logo = ImageIO.read(new URL(logoImg));
//        int logoWidth = logo.getWidth(null) > twodimensioncode.getWidth()*25 /100 ? (twodimensioncode.getWidth()*25 /100) : logo.getWidth(null);  
//        int logoHeight = logo.getHeight(null) > twodimensioncode.getHeight()*25 /100 ? (twodimensioncode.getHeight()*25 /100) : logo.getHeight(null);  
//        int x = (twodimensioncode.getWidth() - logoWidth) / 2;  
//        int y = (twodimensioncode.getHeight() - logoHeight) / 2;  
//        graphics.drawImage(logo, x, y, logoWidth, logoHeight, null); 
//        graphics.dispose();  
//        logo.flush();  
//        twodimensioncode.flush();  
        return ImageIO.write(twodimensioncode, "png", outputStream);
    }catch(Exception e){
        System.out.println("二维码绘制logo失败");
    } finally {
        if (outputStream!=null) {
            try {
                outputStream.close();
                outputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
    return false;  
}  
    
    /**
     * 解析读取条形码
     * @param barcodePath 条形码文件路径
     * @return 
     */
    public static String decodeBarcode(String barcodePath){
        BufferedImage image;
        Result result = null;
        try {
            image = ImageIO.read(new File(barcodePath));
            if (image != null) {
                 LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = new MultiFormatReader().decode(bitmap, null);
            }
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 生成二维码
     * @param context 二维码内容
     * @param width 二维码图片宽度
     * @param height 二维码图片高度
     * @return 
     */
    public static BufferedImage encodeQRcode(String context, int width, int height){
        BufferedImage qrCode=null;
        try {
            Map hints= new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, height,hints);
            qrCode = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException ex) {
            Logger.getLogger(ZxingTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qrCode;
    }
    
    /**
     * 生成带有logo标志的二维码
     * @param context 二维码存储内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param logoPath  二维码logo路径
     * @return 
     */
    public static BufferedImage encodeLogoQRcode(String context, int width, int height, String logoPath){
         BufferedImage logoQRcode=null;
        try {
             // 读取Logo图像  
            BufferedImage logoImage = scale(logoPath, IMAGE_WIDTH,IMAGE_HEIGHT, true);
            int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];  
            for (int i = 0; i < logoImage.getWidth(); i++) {  
                for (int j = 0; j < logoImage.getHeight(); j++) {  
                    srcPixels[i][j] = logoImage.getRGB(i, j);  
                }  
            }
            
            Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
            hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
           
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, height,hint);
            
             // 二维矩阵转为一维像素数组  
             int halfW = bitMatrix.getWidth() / 2;  
             int halfH = bitMatrix.getHeight() / 2;
             
             int[] pixels = new int[width * height];  
  
             for (int y = 0; y < bitMatrix.getHeight(); y++) {  
                    for (int x = 0; x < bitMatrix.getWidth(); x++) {  
                        // 读取图片  
                        if (x > halfW - IMAGE_HALF_WIDTH  
                                && x < halfW + IMAGE_HALF_WIDTH  
                                && y > halfH - IMAGE_HALF_WIDTH  
                                && y < halfH + IMAGE_HALF_WIDTH) {  
                            pixels[y * width + x] = srcPixels[x - halfW  
                                    + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];  
                        }   
                        // 在图片四周形成边框  
                        else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
                                && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH  
                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
                                || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH  
                                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
                                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
                                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
                                || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
                                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
                                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
                                        - IMAGE_HALF_WIDTH + FRAME_WIDTH)  
                                || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
                                        && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
                                        && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
                                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {  
                            pixels[y * width + x] = 0xfffffff;  
                        } else {  
                            // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；  
                            pixels[y * width + x] = bitMatrix.get(x, y) ? 0xff000000 : 0xfffffff;  
                        }
                }  
            }
            logoQRcode = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            logoQRcode.getRaster().setDataElements(0, 0, width, height, pixels);              
        } catch (WriterException ex) {
            Logger.getLogger(ZxingTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logoQRcode;
    }
    
    /**
     * 解析读取二维码
     * @param qrCodePath 二维码图片路径
     * @return 
     */
    public static String decodeQRcode(String qrCodePath){
     BufferedImage image;
     String qrCodeText = null;
        try {  
            image = ImageIO.read(new File(qrCodePath));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            // 对图像进行解码
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            qrCodeText = result.getText();
        } catch (IOException e) {
            e.printStackTrace();  
        } catch (NotFoundException e) {
            e.printStackTrace();  
        }  
        return qrCodeText;
    }
    
    /**
     * 对图片进行缩放
     * @param imgPath 图片路径
     * @param width 图片缩放后的宽度
     * @param height 图片缩放后的高度
     * @param hasFiller 是否补白
     * @return 
     */
    public static BufferedImage scale(String imgPath, int width, int height, boolean hasFiller){
        double ratio = 0.0; // 缩放比例  
        File file = new File(imgPath);
        BufferedImage srcImage = null;
        try {
            srcImage = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(ZxingTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image finalImage;
        finalImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例  
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {  
            if (srcImage.getHeight() > srcImage.getWidth()) {  
                ratio = (new Integer(height)).doubleValue()/ srcImage.getHeight();
            } else {  
                ratio = (new Integer(width)).doubleValue()/ srcImage.getWidth();
            }  
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            finalImage = op.filter(srcImage, null);  
        }  
        if (hasFiller) {// 补白  
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);  
            if (width == finalImage.getWidth(null)){  
                graphic.drawImage(finalImage, 0,(height - finalImage.getHeight(null))/2,finalImage.getWidth(null), finalImage.getHeight(null), Color.white, null);
            }else{  
                graphic.drawImage(finalImage,(width - finalImage.getWidth(null))/2,0,finalImage.getWidth(null), finalImage.getHeight(null), Color.white, null);
                graphic.dispose();  
                finalImage = image;  
            }
        }  
        return (BufferedImage) finalImage;
    }
    
    public static void main(String[] args) {
        String s = "fdaf\r\nfdasf";
        s = s.replace("\r\n", System.getProperty("line.separator", "\n"));
        System.out.println(s);
        String content= "http://m.kuaidi100.com/order/market/eb.jsp?sign=TdWugHtMarket";
        encodeBarcodeForFileYilabao(content, "QRCode", new File("D:\\tiangui_qr.png"), 2800);
        
//        BufferedImage image = new BufferedImage(184, 39, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics = (Graphics2D) image.getGraphics();
//        graphics.setBackground(Color.WHITE);
//        graphics.clearRect(0, 0, 184, 39);
//        graphics.setColor(Color.black);
//        
//        BufferedImage img = ZxingTool.encodeBarcode("889448057688", 215, 23, null);
//        graphics.drawImage(img, 0, 0, 184, 39, null);
//        try {
//            ImageIO.write(image, "bmp", new File("d:\\qp.jpg"));
//        } catch (IOException e) {
//        }
    }
}