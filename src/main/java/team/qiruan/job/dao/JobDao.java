package team.qiruan.job.dao;


import java.util.List;

public interface JobDao {
    int insert(Job job);
    Job_more search(int id);
    int delete(int id);
    List<Job> getAllOneJobs(int uid);
    List<Job_less> getAllJobs_Less();
}
