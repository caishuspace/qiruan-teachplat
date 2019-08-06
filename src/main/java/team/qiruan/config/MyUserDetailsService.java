package team.qiruan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import team.qiruan.domain.User;
import team.qiruan.service.UserService;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.getUserByName(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(), true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getPrivilegeName()));
	}
}