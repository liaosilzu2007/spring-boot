package com.lzumetal.springboot.utils.web;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author liaosi
 * @date 2020-03-27
 */
public class BodyReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 请求体
     */
    private final byte[] body;


    public BodyReadHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = InputStreamToByte(request.getInputStream());
    }


    /**
     * 读取输入流中的字节数组
     */
    private byte[] InputStreamToByte(InputStream is) throws IOException {
        try (ByteArrayOutputStream bytestream = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[1024];
            int ch;
            while ((ch = is.read(buffer)) != -1) {
                bytestream.write(buffer, 0, ch);
            }
            return bytestream.toByteArray();
        }

    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        try (final ByteArrayInputStream bais = new ByteArrayInputStream(body)) {
            return new ServletInputStream() {

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() {
                    return bais.read();
                }

            };
        }
    }
}
