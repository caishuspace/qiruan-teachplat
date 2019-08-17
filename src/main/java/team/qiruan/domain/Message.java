package team.qiruan.domain;

import java.sql.Time;

/**
 * Message
 */
public class Message {

    Long id;
    Time time;
    String belong;
    Integer uid;
    String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message [belong=" + belong + ", content=" + content + ", id=" + id + ", time=" + time + ", uid=" + uid
                + "]";
    }

}