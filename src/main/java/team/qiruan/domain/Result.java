package team.qiruan.domain;

public class Result {
    Integer code;
    String message;
    Object content;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", content=" + content + ", message=" + message + "]";
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public Result(Integer code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}