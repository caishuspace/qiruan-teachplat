package team.qiruan.domain;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String password;
    Integer privilege;

    /**
     * 返回用户类型名称
     * @return
     */
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
}