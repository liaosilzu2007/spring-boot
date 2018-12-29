package com.lzumetal.springboot.demodatabase.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.springboot.demodatabase.StartupApplication;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UrlRequestTest {


    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * GET请求+@PathVariable
     */
    @Test
    public void getRequest() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/getBook/{id}", String.class, 1);
        System.err.println(entity.getBody());
    }


    /**
     * GET请求
     * getForEntity 和 getForObject 的区别：
     * getForObject返回结果Controller中的返回类型。
     * getForEntity返回结果里包含了请求头信息等，同时entity.getBody()的结果已经被转换成了json字符串
     */
    @Test
    public void getRequest2() {
        String result = testRestTemplate.getForObject("/getBookInfo2?id={id}&name={2}", String.class, 100, "解忧杂货店");
        System.err.println(result);
    }


    /**
     * GET请求除了使用占位符的方式按次序注入，也可以通过一个map通过名字注入
     */
    @Test
    public void getRequest3() {
        Map<String, Object> param = new HashMap<>();
        param.put("bookid", 20);
        param.put("name", "呼啸山庄");
        String result = testRestTemplate.getForObject("/getBookInfo2?id={bookid}&name={name}", String.class, param);
        System.err.println(result);
    }


    /**
     * POST请求
     */
    @Test
    public void postRequest() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("id", 2);
        String result = testRestTemplate.postForObject("/postBookInfo", param, String.class);
        System.err.println(result);
    }

    /**
     * POST请求，并带请求头
     */
    @Test
    public void postRequest2() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "aaaaaaabbbbbbdcccc");

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("id", 2);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(param, headers);

        ResponseEntity<String> resultEntity = testRestTemplate.postForEntity("/postBookInfo", entity, String.class);
        System.err.println("reuslt:" + resultEntity.getBody());
        System.err.println("headers:" + resultEntity.getHeaders());
    }


    /**
     * POST请求，入参是json格式字符串：{"id":2,"name":"Effective Java","author":"Joshua Bloch","price":39.0}
     */
    @Test
    public void postRequest3() {
        String jsonStr = "{\"id\":2,\"name\":\"Effective Java\",\"author\":\"Joshua Bloch\",\"price\":39.0}";
        HttpHeaders headers = new HttpHeaders();

        //设置contentType
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));

        HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);
        String result = testRestTemplate.postForObject("/postJson", entity, String.class);
        System.err.println(result);
    }


    /**
     * 上传文件
     *
     * @throws Exception
     */
    @Test
    public void upload() throws Exception {
        Resource resource = new FileSystemResource("d:/123.jpg");
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("files", resource);
        String result = testRestTemplate.postForObject("/uploadFile", param, String.class);
        System.out.println(result);
    }


    /**
     * 下载文件
     *
     * @throws Exception
     */
    @Test
    public void download() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "xxxxxx");
        HttpEntity formEntity = new HttpEntity(headers);

        ResponseEntity<byte[]> response = testRestTemplate.exchange("/download?file={1}", HttpMethod.GET, formEntity, byte[].class, "d:/aaa.png");
        if (response.getStatusCode() == HttpStatus.OK) {
            FileUtils.writeByteArrayToFile(new File("d:/123.jpg"), response.getBody());
        }
    }

    @Test
    public void test() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("method", "gotWaitCoupons");
        param.add("token", "pSDA-0osrMdgq_jQbMJTmBmgDw7BxKs4EmHmAMYFWcg");
        String result = testRestTemplate.postForObject("http://m.kuaidi100.com/mkt/courier/open/shop/coupon.do", param, String.class);
        System.err.println(result);
    }
}
