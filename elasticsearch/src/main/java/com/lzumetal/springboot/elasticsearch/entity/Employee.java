package com.lzumetal.springboot.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2021-03-24
 */
@Getter
@Setter
@Document(indexName = "employee", type = "EmployeeBean")        //声明一个文档，指定其所在的索引库和type
public class Employee implements Serializable {

    /**
     * 主键id
     */
    @Id
    private Long id;

    /* 姓名 */
    @Field(type = FieldType.Text)
    private String name;

    /* 电话 */
    @Field(type = FieldType.Keyword)
    private String mobile;

    /* 性别 */
    @Field(type = FieldType.Integer)
    private Integer gender;

    /* 部门id */
    @Field(type = FieldType.Long)
    private Long departmentId;

    /* 状态 */
    @Field(type = FieldType.Integer, index = false)
    private Integer status;

    /* 入职时间 */
    @Field(type = FieldType.Long)
    private Long createtime;


}
