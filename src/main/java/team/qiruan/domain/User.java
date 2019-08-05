package team.qiruan.domain;

public class User {
    Integer id;
    String name;
    String password;
    Integer privilege;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", privilege=" + privilege + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}