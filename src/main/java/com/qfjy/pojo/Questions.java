package com.qfjy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Questions  implements Serializable {
    /**
     * 空接口又称为标记接口。
     * 序列化作用：
     *    为了方便传输和存储。
     * 序列化：将对象转化为字节序列的过程[二进制]
     * 反序列化：将字节序列恢复为对象的过程
     *
     */
    private Integer id;

    private String question;

    private Short correctanswer;

    private Integer sortnum;

    private Short status;


}