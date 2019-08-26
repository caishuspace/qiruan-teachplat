package team.qiruan.service;

import team.qiruan.domain.UserBind;

/**
 * UserBindService
 */
public interface UserBindService {

    /**
     * 获取用户绑定信息
     * @param name
     * @return
     */
    UserBind getUserBindByName(String name);

    /**
     * 用户绑定邮箱
     * @param name
     * @param email
     * @return
     */
    Boolean addUserBindEmail(String name,String email,String valiString);

    /**
     * 绑定验证
     * @param item
     * @param valiString
     * @return
     */
    Boolean validateUserBind(String item,String valiString);
}