package indi.cloud.api.base.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

public class BaseController {

    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    /**
     * 登录信息Key
     */
    public static final String LOGIN_INFO = "loginInfo";

    private final Logger logger = LoggerFactory.getLogger(BaseController.class);
    protected LoginInfo getLoginInfo() {
       try {
           if ("dev".equals(env)) { //开发环境
               LoginInfo loginInfo = new LoginInfo();
               loginInfo.setUserId(9999);
               loginInfo.setUserName("admin");
               loginInfo.setInstitutionId(1);
               loginInfo.setAdminFlag(1);
               loginInfo.setLoginTime(new Date());
               loginInfo.setInstitutionName("测试用机构");
               return loginInfo;
           }

            return (LoginInfo) RequestContextHolder.currentRequestAttributes().getAttribute(LOGIN_INFO, RequestAttributes.SCOPE_REQUEST);
        } catch (Exception e) {
            logger.error("获取LoginInfo发生异常", e);
            throw e;
        }
    }
}
