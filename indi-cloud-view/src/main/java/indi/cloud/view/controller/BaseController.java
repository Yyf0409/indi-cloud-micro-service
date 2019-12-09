package indi.cloud.view.controller;

import indi.cloud.view.config.Const;
import indi.cloud.view.model.LoginUser;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 */
public class BaseController {

    @Resource
    HttpServletRequest request;

    protected LoginUser getUser() {

        HttpSession session = request.getSession();
        if(session != null ){
            LoginUser loginUser = (LoginUser) session.getAttribute(Const.SESSION_KEY_LOGIN_USER);
            if(loginUser != null){
                return loginUser;
            }
        }

        throw new UnauthorizedUserException("用户登录信息丢失。");
    }
}
