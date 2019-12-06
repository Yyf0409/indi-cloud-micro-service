package indi.cloud.oauth.center.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@CrossOrigin
@RestController
public class OAuthController {

    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Resource
    private HttpServletRequest request;

    @Resource
    private TokenStore tokenStore;

    @Value("${ccx.default_redirect_uri}")
    private String defaultRedirectUri;




    @RequestMapping("/")
    @ResponseBody
    public String home(Principal principal) {
        String redirect_uri = request.getParameter("redirect_uri");
        if (StringUtils.isBlank(redirect_uri)) {
            redirect_uri = defaultRedirectUri;
        }
        return "<html><script>window.location.href=\"" + redirect_uri + "\";</script><body>登录成功，正在跳转......<br/><br/>如无法自动跳转，请手动打开站点。</body></html>";
    }

    /**
     * 查询登录用户信息
     * @param principal
     * @return
     */
    @GetMapping({  "api/user-info", "oauth/me"})
    public Principal me(Principal principal) {
        return principal;
    }

    /**
     * 自定义登陆页面
     *
     * @param request
     * @return
     */
    @GetMapping({"login_page"})
    public ModelAndView login_page(HttpServletRequest request) {
        Map model = new HashMap();
        model.put("csrf", UUID.randomUUID());
        if(request.getParameterMap().containsKey("error")){
            //登陆失败
            model.put("states","error");
        }
        Cookie[] cookies=request.getCookies();
        Cookie username=null;
        Cookie saveFlag=null;

        // cookie可能被禁用 fixed by ywm 2019/11/11
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if ("saveFlag".equals(cookie.getName())) {
                    saveFlag = cookie;
                } else if ("saveUsername".equals(cookie.getName())) {
                    username = cookie;
                }
            }
        }
        if(username!=null &&saveFlag!=null&&saveFlag.getValue().equals("1")){
            model.put("saveUsername",username.getValue());
            model.put("saveFlag","checked=\"checked\"");
        }else {
            model.put("saveUsername", "");
            model.put("saveFlag", "");
        }

        return new ModelAndView("login", model);
    }

    /**
     * 登出
     * @param request
     * @param response
     */
    @RequestMapping({"/logout"})
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            String referer = request.getHeader("referer");
            if(!StringUtils.isBlank(referer)){
                // 重定向回去
                response.sendRedirect(request.getHeader("referer"));
            } else {
                response.getWriter().write("logout success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
