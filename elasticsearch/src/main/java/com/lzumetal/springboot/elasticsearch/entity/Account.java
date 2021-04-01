package com.lzumetal.springboot.elasticsearch.entity;

import com.lzumetal.springboot.elasticsearch.config.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString
@Document(indexName = Constants.INDEX_NAME, type = Constants.TYPE)        //声明一个文档，指定其所在的索引库和type
public class Account implements Serializable {

    /**
     * account_number : 0
     * firstname : Bradshaw
     * address : 244 Columbus Place
     * balance : 16623
     * gender : F
     * city : Hobucken
     * employer : Euron
     * state : CO
     * age : 29
     * email : bradshawmckenzie@euron.com
     * lastname : Mckenzie
     */
    @Id
    private Long account_number;

    @Field(type = FieldType.Keyword)
    private String firstname;

    @Field(type = FieldType.Keyword)
    private String lastname;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Long)
    private Long balance;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String employer;

    @Field(type = FieldType.Keyword)
    private String state;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Text)
    private String email;


}
