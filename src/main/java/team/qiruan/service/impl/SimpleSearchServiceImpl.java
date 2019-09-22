package team.qiruan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import team.qiruan.jobseek.dao.Jobseek_less;
import team.qiruan.service.SimpleSearchService;

/**
 * SimpleJobseekServiceImpl
 */
@Service
public class SimpleSearchServiceImpl implements SimpleSearchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Jobseek_less> searchJobSeek(String word) {
        List<Jobseek_less> res = jdbcTemplate.query(
                "SELECT jobseek.id,userinfo.avatar,user.name,userinfo.sex, jobseek.sub1,jobseek.sub2,jobseek.sub3,jobseek.year,jobseek.major,jobseek.time  FROM jobseek,userinfo,user WHERE userinfo.uid=jobseek.uid and userinfo.uid=user.id and jobseek.introduce like ?",
                new Object[] {"%"+word+"%"}, new BeanPropertyRowMapper<Jobseek_less>(Jobseek_less.class));
        if (res.isEmpty()) {
            return null;
        }
        return res;
    }

}