package team.qiruan.domain;

import lombok.Data;

@Data
/**
 * UserBind
 */
public class UserBind {

    Integer id;
    Integer uid;
    String email;
    String qq;
    String wechat;
    String alipay;
    String phone;
    String valicode;
}