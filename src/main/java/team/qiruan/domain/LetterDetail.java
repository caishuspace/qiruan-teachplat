package team.qiruan.domain;

/**
 * 用以获取打开信件时的数据
 */
public class LetterDetail{
    Integer id;
    String title;
    String content;

    @Override
    public String toString() {
        return "LetterDetail [content=" + content + ", id=" + id + ", title=" + title + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}