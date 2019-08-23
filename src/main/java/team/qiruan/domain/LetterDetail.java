package team.qiruan.domain;

import lombok.Data;

/**
 * 用以获取打开信件时的数据
 */
@Data
public class LetterDetail{
    Integer id;
    String title;
    String content;
}