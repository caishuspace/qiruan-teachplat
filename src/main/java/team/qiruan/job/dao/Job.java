package team.qiruan.job.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    Integer id;
    String time;
    Integer uid;
    String phone;
    String qq;
    String mail;
    String address;
    String salary; //工资
    String detail; //描述
    Integer check;
 
}
