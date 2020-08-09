package com.lzumetal.springboot.utils.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.lzumetal.springboot.utils.entity.Address;
import com.lzumetal.springboot.utils.entity.User;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liaosi
 * @date 2019-02-24
 */
public class BigJsonTest {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setId(302919489L);
        user.setName("Paul");
        user.setCreateTime(new Date());
        user.setAvatar("https://guangzhou.myqcloud.com/avator.jpg");

        List<Address> addresses = new ArrayList<>();

        Address address1 = new Address("广东省", "深圳市", "南山区");
        address1.setStreetAddr("留仙大道5588号");
        address1.setPhone("15011112222");
        addresses.add(address1);

        Address address2 = new Address("北京市", "北京市", "海淀区");
        address2.setStreetAddr("中关村路");
        address2.setPhone("13800008888");
        addresses.add(address2);

        user.setReceiveAddrs(addresses);

        String jsonText = new Gson().toJson(user);
        User userFromJson = parse(jsonText);
        System.out.println(gson.toJson(userFromJson));
    }

    private static User parse(String jsonText) throws IOException {
        StringReader stringReader = new StringReader(jsonText);
        JsonReader jsonReader = new JsonReader(stringReader);
        User workerHistory = new User();
        jsonReader.beginObject();
        try {
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                if ("id".equals(key)) {
                    workerHistory.setId(jsonReader.nextLong());
                } else if ("name".equals(key)) {
                    workerHistory.setName(jsonReader.nextString());
                }  else if ("receiveAddrs".equals(key)) {
                    List<Address> addresses = readAddressList(jsonReader);
                    workerHistory.setReceiveAddrs(addresses);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            return workerHistory;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stringReader.close();
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static List<Address> readAddressList(JsonReader jsonReader) throws IOException {
        List<Address> registCouriers = new ArrayList<>(8);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            Address courier = new Address();
            registCouriers.add(courier);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                switch (key) {
                    case "province":
                        courier.setProvince(jsonReader.nextString());
                        break;
                    case "city":
                        courier.setCity(jsonReader.nextString());
                        break;
                    case "district":
                        courier.setDistrict(jsonReader.nextString());
                        break;
                    default:
                        jsonReader.skipValue();
                        break;

                }
            }
            jsonReader.endObject();
        }
        jsonReader.endArray();
        return registCouriers;
    }


}
