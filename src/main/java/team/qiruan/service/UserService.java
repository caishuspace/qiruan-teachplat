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
}