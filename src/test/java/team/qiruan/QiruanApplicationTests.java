package team.qiruan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiruanApplicationTests {
	@Autowired
	private PasswordEncoder passwordEncoder;

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
}
