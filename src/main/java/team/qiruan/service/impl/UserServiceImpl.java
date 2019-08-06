package team.qiruan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.User;
import team.qiruan.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByName(String name) {
        List<User> res= jdbcTemplate.query("select * from user where name=?",new Object[]{name},new BeanPropertyRowMapper<User>(User.class));
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);
	}

    @Override
    public Boolean regist(String name, String password, Integer privilege) {
        return jdbcTemplate.update("insert into user(name,password,privilege) values(?,?,?)", name,password,privilege)>0;
    }

}