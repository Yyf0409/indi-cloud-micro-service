package indi.cloud.oauth.center.config;

import indi.cloud.oauth.center.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
/***
 *  身份授权认证服务配置
 *  配置客户端、token存储方式等
 */
@Configuration
@EnableAuthorizationServer  //  注解开启验证服务器 提供
// /oauth/authorize,
// /oauth/token,
// /oauth/check_token,
// /oauth/confirm_access
// /oauth/error
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;   //认证方式

    @Resource(name = "MyUserDetailsService")
    private MyUserDetailsService myUserDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(myUserDetailsService) //必须注入userDetailsService否则根据refresh_token无法加载用户信息
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)  //支持GET  POST  请求获取token
                .reuseRefreshTokens(true); //开启刷新token
    }

    /**
     * 认证服务器的安全配置
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override

    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
    }


    /**
     * jdbc token 配置
     */
    @Bean
    public TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }
 }