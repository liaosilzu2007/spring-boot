package com.lzumetal.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Slf4j
public class ImageUitl {


    public static void main(String[] args) throws IOException {
        String imagePath = "C:\\Users\\Administrator\\Desktop\\20200805170546_2.9m.png";
        File file = new File(imagePath);
        System.out.println(file.length() / 1024);
        System.out.println(file.getName());
        byte[] bytes = compressImageFile(file);
        byteToImage(bytes, "C:\\Users\\Administrator\\Desktop\\111.png");
//        Thumbnails.of(new FileInputStream(file)).scale(0.9).toFile("C:\\Users\\Administrator\\Desktop\\111.png");
    }


    /**
     * 将图片字节码转换为图片格式存储
     *
     * @param bytes      图片的字节码
     * @param targerPath 目标图片的地址（全路径：带图片的格式）
     * @throws Exception
     */
    public static void byteToImage(byte[] bytes, String targerPath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(targerPath));) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        }
    }


    public static byte[] compressMultipartImageFile(MultipartFile originalFile) throws IOException {
        long size = originalFile.getSize();
        double decompressRate = getDecompressRate(size);
        ByteArrayInputStream intputStream = new ByteArrayInputStream(originalFile.getBytes());
        String filename = originalFile.getOriginalFilename();
        byte[] bytes = compress(filename, intputStream, decompressRate);
        log.info("图片压缩|filename={},{}kb|压缩后大小：{}kb", filename, size >> 10, bytes.length >> 10);
        return bytes;
    }

    public static byte[] compressImageFile(File originalFile) throws IOException {
        long length = originalFile.length();
        double decompressRate = getDecompressRate(length);
        FileInputStream fileInputStream = new FileInputStream(originalFile);
        String filename = originalFile.getName();
        byte[] bytes = compress(filename, fileInputStream, decompressRate);
        log.info("图片压缩|filename={},{}kb|压缩后大小：{}kb", filename, length >> 10, bytes.length >> 10);
        return bytes;
    }


    private static byte[] compress(String filename, InputStream inputStream, double decompressRate) throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(inputStream).scale(decompressRate).asBufferedImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String imageSuffix = filename.substring(filename.lastIndexOf(".") + 1);
        ImageIO.write(bufferedImage, imageSuffix, baos);
        return baos.toByteArray();
    }


    private static void compressToFile(InputStream inputStream, double decompressRate, String targetPath) throws IOException {
        Thumbnails.of(inputStream).scale(decompressRate).toFile(targetPath);
    }


    private static double getDecompressRate(long byteLength) {
        long mb = byteLength / (1024 * 1024);
        if (mb < 1) {
            return 1;
        } else if (mb == 1) {
            return 0.6;
        } else if (mb == 2) {
            return 0.5;
        } else if (mb == 3) {
            return 0.4;
        } else {
            return 0.2;
        }
    }


}
