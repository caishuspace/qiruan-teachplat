package team.qiruan.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class RegUser {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @Range(min = 1, max = 2, message = "用户类型不存在！")
    @NotNull
    Integer privilege;

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

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public String getPrivilegeName() {
        switch (privilege) {
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
        return "RegUser [name=" + name + ", password=" + password + ", privilege=" + privilege + "]";
    }
}