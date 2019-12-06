package indi.cloud.oauth.center.handler;

import indi.cloud.oauth.center.Utils.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    //cookie 过期时间
    private int  max_age=2419200;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getName()  + " Login success");
        try {
            if(request.getParameterMap().containsKey("saveFlag")){
                //记住密码
                Cookie saveFlag=new Cookie("saveFlag",String.valueOf(request.getParameter("saveFlag")));
                saveFlag.setMaxAge(max_age);
                Cookie saveUsername=new Cookie("saveUsername",String.valueOf(request.getParameter("username")));
                saveUsername.setMaxAge(max_age);

                response.addCookie(saveUsername);
                response.addCookie(saveFlag);
            }else{
                CookieUtils.removeCookie(response,"saveUsername");
                CookieUtils.removeCookie(response,"saveFlag");
            }
            //判定spring security 在未登录的情况下访问某些页面被拦截，跳转到登录页面，然后现在需要登录成功之后跳转到登录之前的页面
            RequestCache cache = new HttpSessionRequestCache();
            SavedRequest savedRequest = cache.getRequest(request, response);
            String url;
            if(savedRequest!=null){
               url=savedRequest.getRedirectUrl();
            }else{
               url="/";
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
