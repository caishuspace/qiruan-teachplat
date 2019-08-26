package team.qiruan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.UserBind;
import team.qiruan.service.UserBindService;

@Service
/**
 * UserBindServiceImpl
 */
public class UserBindServiceImpl implements UserBindService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserBind getUserBindByName(String name) {
        List<UserBind> res = jdbcTemplate.query("SELECT * FROM userbind WHERE uid=(SELECT id FROM user WHERE name=?)",
                new Object[] { name }, new BeanPropertyRowMapper<>(UserBind.class));
        if(res.isEmpty()){
            jdbcTemplate.update("INSERT INTO userbind(uid) VALUES ((SELECT id FROM user WHERE name=?))",name);
            return new UserBind();
        }
        return res.get(0);
    }

    @Override
    public Boolean addUserBindEmail(String name, String email,String valiString) {
        return jdbcTemplate.update("UPDATE userbind SET email=?,valicode=? WHERE uid=(SELECT id FROM user WHERE name=?)", "[unchecked]:"+email,"[email]:"+valiString,name)>0;
    }

    @Override
    public Boolean validateUserBind(String item, String valiString) {
        switch(item){
            case "email":
            return jdbcTemplate.update("UPDATE userbind SET email=REPLACE(email,'[unchecked]:',''),valicode='' WHERE valicode=?","[email]:"+valiString)>0;
            default:
        }
        return false;
    }

}