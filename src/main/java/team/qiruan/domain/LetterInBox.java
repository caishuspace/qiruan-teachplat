package team.qiruan.domain;

import java.sql.Date;

/**
 * 用以获取mailbox用的letter信息
 */
public class LetterInBox {

    Integer id;
    Date time;
    String send;
    String recieve;
    String title;
    String content;
    Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRecieve() {
        return recieve;
    }

    public void setRecieve(String recieve) {
        this.recieve = recieve;
    }

    @Override
    public String toString() {
        return "LetterInBox [content=" + content + ", id=" + id + ", recieve=" + recieve + ", send=" + send + ", state="
                + state + ", time=" + time + ", title=" + title + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}