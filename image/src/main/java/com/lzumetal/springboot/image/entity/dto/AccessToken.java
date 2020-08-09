package com.lzumetal.springboot.image.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Getter
@Setter
@ToString
public class AccessToken {

    private String token;
    private int expiresIn;
    private long lastUpdateTime;

    public AccessToken() {

    }

    public AccessToken(String token,int expiresIn,long lastUpdateTime) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.lastUpdateTime = lastUpdateTime;
    }


}
