package team.qiruan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.EditUserInfo;
import team.qiruan.domain.UserInfo;
import team.qiruan.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserInfo getUserInfoByName(String name) {
        List<UserInfo> res=jdbcTemplate.query("select * from userinfo where uid=(select id from user where name=?)",new Object[]{name},new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);
    }

    @Override
    public Boolean updateUserInfo(String username,EditUserInfo userInfo) {
        //要求uid为userinfo表中的唯一索引
        jdbcTemplate.update("insert ignore into userinfo(uid) values((select id from user where name=?));", username);
        return jdbcTemplate.update("update userinfo set name=?,sex=?,birthday=STR_TO_DATE(?,'%m/%d/%Y'),cardid=?,avatar=?,introduce=?"
        +"where uid=(select id from user where name=?);",userInfo.getName(),userInfo.getSex(),userInfo.getBirthday(),
        userInfo.getCardid(),userInfo.getAvatar(),userInfo.getIntroduce(),username
        )>0;
	}

    @Override
    public Boolean updateAvatar(String username, String avatar) {
        jdbcTemplate.update("insert ignore into userinfo(uid) values((select id from user where name=?));", username);
        return jdbcTemplate.update("UPDATE userinfo SET avatar=? WHERE uid=(SELECT id FROM user WHERE name=?)", avatar,username)>0;
    }

}