package team.qiruan.jobseek.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobseekImpl implements JobseekDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int insert(Jobseek jobseek) {
        return jdbcTemplate.update("INSERT INTO jobseek(time,uid,school,year,major,phone,qq,mail,sub1,sub2,sub3,money,introduce,`check`,vcr) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                jobseek.getTime(),jobseek.getUid(),jobseek.getSchool(),jobseek.getYear(),jobseek.getMajor(),jobseek.getPhone(),jobseek.getQq(),jobseek.getMail(),
                jobseek.getSub1(),jobseek.getSub2(),jobseek.getSub3(),jobseek.getMoney(),jobseek.getIntroduce(),jobseek.getCheck(),jobseek.getVcr());
    }

    @Override
    public int update(Jobseek jobseek) {
        return jdbcTemplate.update("UPDATE jobseek set time=? ,uid=? ,school=? ,year=? ,major=? ,phone=? ,qq=? ,mail=? ,sub1=? ,sub2=? ,sub3=? ,money=? ,introduce=? ,`check`=?,vcr=?  where id=?",
                jobseek.getTime(),jobseek.getUid(),jobseek.getSchool(),jobseek.getYear(),jobseek.getMajor(),jobseek.getPhone(),jobseek.getQq(),jobseek.getMail(),
                jobseek.getSub1(),jobseek.getSub2(),jobseek.getSub3(),jobseek.getMoney(),jobseek.getIntroduce(),jobseek.getCheck(),jobseek.getVcr(), jobseek.getId());

    }

    @Override
    public Jobseek_more search(int id) {
        List<Jobseek_more> list = jdbcTemplate.query("SELECT jobseek.id,jobseek.time,jobseek.uid,jobseek.school,jobseek.year,jobseek.major,jobseek.phone, jobseek.qq,jobseek.mail,jobseek.sub1,jobseek.sub2,jobseek.sub3,jobseek.money,jobseek.introduce,jobseek.check,jobseek.vcr,userinfo.avatar,user.name,userinfo.sex FROM jobseek,userinfo,user WHERE jobseek.uid=userinfo.uid and userinfo.uid=user.id and jobseek.id = ?", new Object[]{id}, new BeanPropertyRowMapper<Jobseek_more>(Jobseek_more.class));
        if(list!=null && list.size()>0){
            Jobseek_more jobseek_more = list.get(0);
            return jobseek_more;
        }else{
            return null;
        }
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM jobseek where id=?",id);
    }

    @Override
    public List<Jobseek> getAllOneJobseeks( int uid ) {
        List<Jobseek> res= jdbcTemplate.query("select * from jobseek where uid =?", new Object[]{uid}, new BeanPropertyRowMapper<Jobseek>(Jobseek.class));
        if(res.isEmpty()){
            return null;
        }
        return res;
    }

    @Override
    public List<Jobseek_less> getAllJobseeks_Less() {
        List<Jobseek_less> res= jdbcTemplate.query("SELECT jobseek.id,userinfo.avatar,user.name,userinfo.sex, jobseek.sub1,jobseek.sub2,jobseek.sub3,jobseek.year,jobseek.major,jobseek.time  FROM jobseek,userinfo,user WHERE userinfo.uid=jobseek.uid and userinfo.uid=user.id", new Object[]{}, new BeanPropertyRowMapper<Jobseek_less>(Jobseek_less.class));
        if(res.isEmpty()){
            return null;
        }
        return res;
    }
}
