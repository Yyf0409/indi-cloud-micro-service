package indi.cloud.oauth.center.config;



import indi.cloud.oauth.center.model.SysAccount;
import indi.cloud.oauth.center.service.MyUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Collection;

@Log4j2
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = authentication.getName();// 这个获取表单输入中的用户名
        String password = (String) authentication.getCredentials();
        log.info("登陸用戶：" + userName);
        SysAccount userInfo = (SysAccount) myUserDetailsService.loadUserByUsername(userName);
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(userInfo.getPassword())) {
            throw new UsernameNotFoundException("用户名或者密码不正确");
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return new UsernamePasswordAuthenticationToken(userInfo, pwd, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}