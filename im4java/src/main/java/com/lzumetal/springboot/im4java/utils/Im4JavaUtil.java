package com.lzumetal.springboot.im4java.utils;

import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 * @date 2018-12-29
 */
public class Im4JavaUtil {

    private static final String IMAGE_MAGICK_PATH = "C:\\Program Files\\ImageMagick-7.0.8-Q16";


    enum CommandType {

        convert("转换处理"), composite("图片合成"), identify("图片信息"),;
        private String name;

        CommandType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


    private static ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd = null;
        switch (command) {
            case convert:
                cmd = new ConvertCmd();
                break;
            case composite:
                cmd = new CompositeCmd();
                break;
            case identify:
                cmd = new IdentifyCmd();
                break;
        }
        if (cmd != null) {
            cmd.setSearchPath(IMAGE_MAGICK_PATH);
        }
        return cmd;
    }


    /**
     * 获取图片信息
     *
     * @param imagePath 图片地址
     * @return
     */
    public static Map<String, String> getImageInfo(String imagePath) {
        Map<String, String> imageInfo = new HashMap<>();
        try {
            IMOperation op = new IMOperation();
            op.format("%w,%h,%d/%f,%Q,%b,%e");
            op.addImage();
            ImageCommand identifyCmd = getImageCommand(CommandType.identify);//注意这里，用的是identify
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            String[] result = cmdOutput.get(0).split(",");
            if (result.length == 6) {
                imageInfo.put("宽度", result[0]);
                imageInfo.put("高度", result[1]);
                imageInfo.put("路径", result[2]);
                imageInfo.put("质量", result[3]);
                imageInfo.put("大小", result[4]);
                imageInfo.put("格式", result[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageInfo;
    }
}
