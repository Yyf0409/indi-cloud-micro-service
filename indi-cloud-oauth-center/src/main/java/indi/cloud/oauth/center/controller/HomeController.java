package indi.cloud.oauth.center.controller;

import indi.cloud.oauth.center.config.MyAuthenticationProvider;
import indi.cloud.oauth.center.service.MyUserDetailsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Author yangyifan
 * @date 2019/8/8 14:20
 * @Description
 */
@Controller
@RequestMapping("/")
public class HomeController{
    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Value("${ccx.default_redirect_uri}")
    private String defaultRedirectUri;

    @Resource
    private MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


//    /**
//     * 登陆
//     * @param
//     * @return
//     */
//    @PostMapping("login")
//    public String login(HttpServletRequest httpServletRequest,Authentication authentication,HttpServletResponse httpServletResponse) throws IOException {
//        String userName =httpServletRequest.getParameter("username");
//        String password =httpServletRequest.getParameter("password");
//
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
//
//            myAuthenticationProvider.authenticate(authenticationToken);
//        }catch (Exception e){
//            //抛错
//        }
//        return defaultRedirectUri;
//    }
}
