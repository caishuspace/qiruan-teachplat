package team.qiruan.job.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobImpl implements JobDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int insert(Job job) {
        return jdbcTemplate.update("INSERT INTO job(time,uid,phone,qq,mail,address,salary,detail,`check`) VALUES (?,?,?,?,?,?,?,?,?)",
                job.getTime(),job.getUid(),job.getPhone(),job.getQq(),job.getMail(),
               job.getAddress(),job.getSalary(),job.getDetail(),job.getCheck());
    }



    @Override
    public Job_more search(int id) {
        List<Job_more> list = jdbcTemplate.query("SELECT job.id,userinfo.avatar,user.name,userinfo.sex, job.address,job.salary,job.time,job.phone,job.qq,job.mail,job.detail FROM job,userinfo,user WHERE job.uid=userinfo.uid and userinfo.uid=user.id and job.id = ?", new Object[]{id}, new BeanPropertyRowMapper<Job_more>(Job_more.class));
        if(list!=null && list.size()>0){
            Job_more job_more = list.get(0);
            return job_more;
        }else{
            return null;
        }
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM job where id=?",id);
    }

    @Override
    public List<Job> getAllOneJobs( int uid ) {
        List<Job> res= jdbcTemplate.query("select * from job where uid =?", new Object[]{uid}, new BeanPropertyRowMapper<Job>(Job.class));
        if(res.isEmpty()){
            return null;
        }
        return res;
    }

    @Override
    public List<Job_less> getAllJobs_Less() {
        List<Job_less> res= jdbcTemplate.query("SELECT job.id,userinfo.avatar,user.name,userinfo.sex, job.address,job.salary,job.time  FROM job,userinfo,user WHERE userinfo.uid=job.uid and userinfo.uid=user.id", new Object[]{}, new BeanPropertyRowMapper<Job_less>(Job_less.class));
        if(res.isEmpty()){
            return null;
        }
        return res;
    }
}
