package team.qiruan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标准返回结果实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    Integer code;
    String message;
    Object content;

    /**
     * 标准返回结果
     * @param code 状态代码
     * @param message 提示信息
     */
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}