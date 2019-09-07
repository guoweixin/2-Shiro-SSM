package com.qfjy.dto;

import lombok.Data;

/**
 * @Classname QuestionsDTO
 * @Author guoweixin
 * @Description TODO
 * @Date 2019/9/2 17:06
 * @Created by Administrator
 */
@Data
public class QuestionsDTO {

    private String question;

    private Short correctAnswer;

    private String[] answers;   // List[]
}
