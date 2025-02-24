package com.lzumetal.springboot.image.entity.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UploadImageDTO {

    private String url;

    private Integer errorCode;

    private int reqIndex;

}
