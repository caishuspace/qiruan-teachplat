package team.qiruan.domain;

import java.sql.Time;

import lombok.Data;

/**
 * Message
 */
@Data
public class Message {

    Long id;
    Time time;
    String belong;
    Integer uid;
    String content;

}