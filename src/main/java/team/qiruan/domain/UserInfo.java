package team.qiruan.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class UserInfo {
    Integer id;
    Integer uid;
    String name;
    String sex;
    Date birthday;
    String cardid;
    String avatar;
    String introduce;
    Integer status;
}