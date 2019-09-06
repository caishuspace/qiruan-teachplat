package team.qiruan.job.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job_more{
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
    String avatar;
    String name;
    String sex;
}
