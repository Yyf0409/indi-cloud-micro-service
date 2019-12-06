package indi.cloud.oauth.center.service;

import indi.cloud.oauth.center.base.dao.MInstitutionBaseMapper;
import indi.cloud.oauth.center.base.dao.TUserBaseMapper;
import indi.cloud.oauth.center.base.model.MInstitutionBase;
import indi.cloud.oauth.center.base.model.TUserBase;
import indi.cloud.oauth.center.config.Message;
import indi.cloud.oauth.center.model.SysAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service(value = "MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private TUserBaseMapper tUserBaseMapper;

    @Resource
    private MInstitutionBaseMapper mInstitutionBaseMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TUserBase condition = new TUserBase();
        condition.setUsername(username);
        TUserBase tUserBase = tUserBaseMapper.selectOne(condition);

        // 用户存在判定
        if (tUserBase == null || (tUserBase.getDeleteFlag() != null && tUserBase.getDeleteFlag() == 1)) {
            log.info("登录用户【" + username + "】不存在.");
            throw new UsernameNotFoundException(Message.getUserNotFound());
        }

        // 用户状态
        if (tUserBase.getState() != null && tUserBase.getState() == 1) {
            log.info("登录用户【" + username + "】已停用.");
            throw new UsernameNotFoundException(Message.getUserDisabled());
        }

        // 用户过期
        if(tUserBase.getExpired() != null && tUserBase.getExpired().getTime() <  new Date().getTime()){
            log.info("登录用户【" + username + "】已过期.");
            throw new UsernameNotFoundException(Message.getUserExpired());
        }

        MInstitutionBase institutionBase = getInstitution(tUserBase.getInstitutionUid());

        // 机构不存在
        if(institutionBase == null ||  (institutionBase.getDeleteFlag() != null && institutionBase.getDeleteFlag() == 1)){
            log.info("登录用户所属机构【institution_uid = " + tUserBase.getInstitutionUid() + "】不存在.");
            throw new UsernameNotFoundException(Message.getUserNotFound());
        }

        // 机构被锁定
         if(institutionBase.getState() != null && institutionBase.getState() == 2){
             log.info("登录用户所属机构【institution_uid = " + tUserBase.getInstitutionUid() + "】已停用.");
             throw new UsernameNotFoundException(Message.getInstitutionDisabled());
         }

        // 机构已过期
        if(institutionBase.getExpired() != null && institutionBase.getExpired().getTime() < new Date().getTime()){
            log.info("登录用户所属机构【institution_uid = " + tUserBase.getInstitutionUid() + "】已过期.");
            throw new UsernameNotFoundException(Message.getInstitutionExpired());
        }

        SysAccount sysAccount = new SysAccount();
        BeanUtils.copyProperties(tUserBase, sysAccount);
        sysAccount.setAdmin(tUserBase.getAdminFlag() == null ? false : tUserBase.getAdminFlag() == 1);
        sysAccount.setInstitutionName(institutionBase.getName());
        sysAccount.setExaminestate(institutionBase.getExaminestate());

        return sysAccount;
    }

    /**
     * 获取机构名称
     * @param institutionUid
     * @return
     */
    private MInstitutionBase getInstitution(String institutionUid) {
        try {
            MInstitutionBase condition = new MInstitutionBase();
            condition.setUid(institutionUid);
            return mInstitutionBaseMapper.selectOne(condition);
        } catch (Exception ex) {
            log.error("登录用户所属机构不存在", ex);
        }
        return null;
    }
}