package team.qiruan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author 刘海鑫
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/auth/login")
                // .loginPage("/auth/require")
                .loginPage("/auth/page-login").defaultSuccessUrl("/auth/success").and().authorizeRequests()
                .antMatchers("/auth/**", "/webjars/**", "/css/**", "/js/**").permitAll()// 允许静态资源
                .antMatchers("/user/**","/letter/**").authenticated()// 用户信息需要认证
                .antMatchers("/jobseek/add**").authenticated()//发布求职信息需要认证
                .antMatchers("/jobseek/my**").authenticated()//查看自己求职信息需要认证
                .antMatchers("/jobseek/delete**").authenticated()//删除求职信息需要认证
                .antMatchers("/job/add**").authenticated()//发布招聘信息需要认证
                .antMatchers("/job/my**").authenticated()//查看自己招聘信息需要认证
                .antMatchers("/job/delete**").authenticated()//删除招聘信息需要认证
                .and().rememberMe().tokenRepository(persistentTokenRepository()) // 配置token持久化仓库
                .tokenValiditySeconds(3600 * 24 * 7) // remember 过期时间，单为秒
                .userDetailsService(userDetailService) // 处理自动登录逻辑
                .and().logout().logoutUrl("/auth/logout");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我相关功能
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //如果数据库中没有表persistent_logins，使用下面这行代码创建表。
        // jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}