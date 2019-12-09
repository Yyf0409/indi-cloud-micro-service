package ccx.cloud.credit.risk.view.controller;

import ccx.cloud.credit.risk.view.config.Const;
import ccx.cloud.credit.risk.view.model.LoginUser;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
