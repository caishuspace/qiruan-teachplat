package team.qiruan.domain;

/**
 * 标准返回结果实体类
 */
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

    /**
     * 标准返回结果
     * @param code 状态代码
     * @param message 提示信息
     */
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    /**
     * 标准返回结果
     * @param code 状态代码
     * @param message 提示信息
     * @param content 附加内容
     */
    public Result(Integer code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}