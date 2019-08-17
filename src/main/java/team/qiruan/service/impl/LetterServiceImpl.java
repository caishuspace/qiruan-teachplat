package team.qiruan.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.domain.LetterDetail;
import team.qiruan.domain.LetterInBox;
import team.qiruan.service.LetterService;

@Service
/**
 * LetterServiceImpl
 */
public class LetterServiceImpl implements LetterService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean sendLetter(String send, String to, String title, String content) {
        try {
            return jdbcTemplate.update(
                    "insert into letter(time,senduid,recieveuid,title,content,state)values(?,(select id from user where name=?),(select id from user where name=?),?,?,2)",
                    new Date(System.currentTimeMillis()), send, to, title, content) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<LetterInBox> getAllLetters(String username) {
        return jdbcTemplate.query(
                "SELECT letter.id, letter.time, `user`.`name` AS send, ? AS recieve, letter.title , letter.content , letter.state FROM letter, `user` WHERE letter.recieveuid = ( SELECT id FROM `user` WHERE `name` = ? ) AND letter.senduid = `user`.id;",
                new Object[] { username, username }, new BeanPropertyRowMapper<>(LetterInBox.class));
    }

    @Override
    public List<LetterInBox> getUnreadLetters(String username) {
        return jdbcTemplate.query(
                "SELECT letter.id, letter.time, `user`.`name` AS send, ? AS recieve, letter.title , letter.content , letter.state FROM letter, `user` WHERE (letter.recieveuid = ( SELECT id FROM `user` WHERE `name` = ? ) AND letter.senduid = `user`.id AND letter.state = 2);",
                new Object[] { username, username }, new BeanPropertyRowMapper<>(LetterInBox.class));
    }

    @Override
    public List<LetterInBox> getAllSentLetters(String username) {
        return jdbcTemplate.query(
                "SELECT letter.id, letter.time, ? AS send, `user`.`name` AS recieve, letter.title , letter.content , letter.state FROM letter, `user` WHERE letter.senduid = ( SELECT id FROM `user` WHERE `name` = ? ) AND letter.recieveuid = `user`.id;",
                new Object[] { username, username }, new BeanPropertyRowMapper<>(LetterInBox.class));
    }

    @Override
    public LetterDetail getLetterById(String username, Integer id) {
        List<LetterDetail> res = jdbcTemplate.query(
                "SELECT id,title,content FROM letter where id=? and (senduid=(SELECT id FROM `user` WHERE `name`=?) OR recieveuid=(SELECT id FROM `user` WHERE `name`=?))",
                new Object[] { id, username, username }, new BeanPropertyRowMapper<>(LetterDetail.class));
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public Boolean read(String username, Integer id) {
        return jdbcTemplate.update(
                "UPDATE letter SET state=1 WHERE id=? AND recieveuid=(SELECT id FROM `user` WHERE `name`=?)", id,
                username) > 0;
    }

    @Override
    public Boolean del(String username, Integer id) {
        return jdbcTemplate.update(
                "DELETE FROM letter WHERE id=? AND recieveuid=(SELECT id FROM `user` WHERE `name`=?)", id,
                username) > 0;
    }

}