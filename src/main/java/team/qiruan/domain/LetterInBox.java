package team.qiruan.domain;

import java.sql.Date;

import lombok.Data;

/**
 * 用以获取mailbox用的letter信息
 */
@Data
public class LetterInBox {

    Integer id;
    Date time;
    String send;
    String recieve;
    String title;
    String content;
    Integer state;

}