package team.qiruan.jobseek.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jobseek {
    Integer id;
    String time;
    Integer uid;
    String school;
    Integer year;
    String major;
    String phone;
    String qq;
    String mail;
    String sub1;
    String sub2;
    String sub3;
    Integer money;
    String introduce;
    Integer check;
    String vcr;
}
