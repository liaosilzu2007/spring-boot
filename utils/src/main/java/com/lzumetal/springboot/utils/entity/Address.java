package com.lzumetal.springboot.utils.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2019-02-24
 */
@Getter
@Setter
@ToString
public class Address implements Serializable {


    private String province;

    private String city;

    private String district;

    private String streetAddr;

    private String phone;

    public Address() {
    }

    public Address(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }


}
