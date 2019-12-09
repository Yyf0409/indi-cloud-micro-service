package indi.cloud.view.filter;

import indi.cloud.view.config.Const;
import indi.cloud.view.model.LoginUser;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "UserInfoFilter", urlPatterns = "/*")
@Component
public class UserInfoFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

//    @Value("${credit.risk.servlet_path}")
//    private String servlet_path;

//    @Value("${credit.risk.api.query_user_amount_uri}")
//    private String queryUserAmountUri;

    @Resource
    RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {

            // Request类型不一致，不处理
            if (!(request instanceof HttpServletRequest)) {
                return;
            }

            HttpSession session = ((HttpServletRequest) request).getSession();
            if (session == null) {
                // session不存在
                return;
            }

            // 确认登录用户
            LoginUser loginUser = (LoginUser) session.getAttribute(Const.SESSION_KEY_LOGIN_USER);
            if (loginUser == null) {
                loginUser = getLoginUser(request);
                if (loginUser != null) {
                    session.setAttribute(Const.SESSION_KEY_LOGIN_USER, loginUser);
                }
            }


        } catch (Exception ex) {
            logger.error("发生异常", ex);
        }

        // 继续下一个过滤器
        //TODO 过滤未登录用户
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    private LoginUser getLoginUser(HttpServletRequest request) {

        try {
            if (request.getUserPrincipal() == null) {
                return null;
            }
            JSONObject principal = JSONObject.parseObject(JSONObject.toJSONString(request.getUserPrincipal()));
            JSONObject oAuthLoginInfo = principal.getJSONObject("principal").getJSONObject("attributes").getJSONObject("principal");
            LoginUser loginUser = new LoginUser();
            loginUser.setUserUid(oAuthLoginInfo.getString("uuid"));
            loginUser.setUsername(oAuthLoginInfo.getString("name"));   // 用户姓名，作为username
            loginUser.setLoginName(oAuthLoginInfo.getString("username")); // 登录账号名，作为LoginName
            loginUser.setAdmin(oAuthLoginInfo.getBooleanValue("admin")); // 是否是管理员
            loginUser.setPhone(oAuthLoginInfo.getString("phone")); // 登录账号名，作为LoginName
            loginUser.setInstitutionUid(oAuthLoginInfo.getString("institutionUid"));
            loginUser.setInstitutionName(oAuthLoginInfo.getString("institutionName"));
            loginUser.setCreateTime(oAuthLoginInfo.getDate("createTime"));
            loginUser.setExaminestate(oAuthLoginInfo.getInteger("examinestate"));



            JSONObject token = principal.getJSONObject("principal").getJSONObject("attributes").getJSONObject("details");
            loginUser.setAccessToken(token.getString("tokenValue"));

            return loginUser;
        }catch (Exception ex){
            return null;
        }
   }

//    private String getAmount(LoginUser loginUser) {
//
//        try {
//
//            if(loginUser != null) {
//
//                Map paramMap = new HashMap<>();
//                paramMap.put("userUid", loginUser.getUserUid());
//
//                JSONObject jsonUser = restTemplate.getForObject(queryUserAmountUri, JSONObject.class, paramMap);
//                if (StringUtils.equals("200", jsonUser.getString("code"))) {
//                    return jsonUser.getJSONObject("data").getString("amount");
//                }
//            }
//        } catch (Exception ex){
//            logger.error("无法获取用户余额", ex);
//        }
//
//        return "服务故障";
//    }
}
