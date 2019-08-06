package team.qiruan.service;

import team.qiruan.domain.EditUserInfo;
import team.qiruan.domain.UserInfo;
/**
 * 用于获取用户信息
 */
public interface UserInfoService {
    /**
     * 通过用户名获取用户信息
     * @param name 用户名
     * @return
     */
    UserInfo getUserInfoByName(String name);

    /**
     * 更新用户信息
     * @param username 用户名
     * @param userInfo
     * @return 是否更新成功
     */
    Boolean updateUserInfo(String username,EditUserInfo userInfo);
}