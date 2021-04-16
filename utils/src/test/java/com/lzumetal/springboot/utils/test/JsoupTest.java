package com.lzumetal.springboot.utils.test;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-04-16
 */
public class JsoupTest {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\temp.txt");
        StringBuilder html = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    html.append(line).append("\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(html);
        Document document = Jsoup.parse(html.toString());
        Elements elements = document.select("a");
        for (Element element : elements) {
            String href = element.attr("href");
            String code = null;
            if (href != null) {
                String[] split = href.split(".shtml")[0].split("/");
                code = split[split.length - 1];
            }
            System.out.println(element.ownText() + "\t" + code);
        }

    }


}
