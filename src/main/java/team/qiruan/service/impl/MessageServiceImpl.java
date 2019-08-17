package team.qiruan.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.Message;
import team.qiruan.service.MessageService;

/**
 * MessageServiceImpl
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getAllMessage(String belong) {
        return jdbcTemplate.query("select * from message where belong=?", new Object[] { belong },
                new BeanPropertyRowMapper<>(Message.class));
    }

    @Override
    public Boolean addMessage(String belong, String username, String content) {
        return jdbcTemplate.update(
                "insert into message(time,belong,uid,content) values(?,?,(select id from user where name=?),?)",
                new Date(System.currentTimeMillis()), belong, username, content) > 0;
    }

}