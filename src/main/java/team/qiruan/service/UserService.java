package team.qiruan.service;

import team.qiruan.domain.User;

public interface UserService {
    /**
     * 通过用户名获取用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 用户注册
     * @param name
     * @param password
     * @param privilege
     * @return
     */
    Boolean regist(String name,String password,Integer privilege);

    /**
     * 修改密码
     * @param username
     * @param encodedNewPassWord 加密后的新密码
     * @return
     */
    Boolean changePWD(String username,String encodedNewPassWord);
}