package com.qfjy.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Resources  implements Serializable {
    private Integer id;

    private String key;

    private String val;

    private Integer sortnum;

    private Short status;


}