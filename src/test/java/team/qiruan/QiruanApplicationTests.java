package team.qiruan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import team.qiruan.jobseek.dao.Jobseek;
import team.qiruan.jobseek.dao.JobseekDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiruanApplicationTests {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JobseekDao jobseekDao;

	@Test
	public void contextLoads() {
	}

	/**
	 * 手动往数据库里面插入用户记录计算密码用的
	 */
	@Test
	public void encodePassword(){
		String password="admin888";
		System.out.println(password+"加密后是："+passwordEncoder.encode(password));
	}

	@Test
	public void testSQL(){
		System.out.println(jobseekDao.insert(Jobseek.builder().time("2019-08-08 00:00:00").uid(3).build()));
	}
}
