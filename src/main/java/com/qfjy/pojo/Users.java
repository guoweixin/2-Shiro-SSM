package com.qfjy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users  implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private Short status;

    private String remark;


}