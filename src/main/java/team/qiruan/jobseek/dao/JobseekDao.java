package team.qiruan.jobseek.dao;


import java.util.List;

public interface JobseekDao {
    int insert(Jobseek jobseek);
    int update(Jobseek jobseek);
    Jobseek_more search(int id);
    int delete(int id);
    List<Jobseek> getAllOneJobseeks(int uid);
    List<Jobseek_less> getAllJobseeks_Less();
}
