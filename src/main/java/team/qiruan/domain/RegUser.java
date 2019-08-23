package team.qiruan.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegUser {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @NotEmpty
    String password2;

    @Range(min = 1, max = 2, message = "用户类型不存在！")
    @NotNull
    Integer usertype;

    /**
     * 获取当前用户的类型名称
     * @return
     */
    public String getPrivilegeName() {
        switch (usertype) {
        case 1:
            return "student";
        case 2:
            return "agency";
        case 3:
            return "admin";
        default:
            return "guest";
        }
    }
}