package team.qiruan.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "RegUser [name=" + name + ", password=" + password + ", usertype=" + usertype + "]";
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}