package indi.cloud.oauth.center.controller;

import indi.cloud.oauth.center.Utils.PasswordUtils;
import indi.cloud.oauth.center.base.core.Result;
import indi.cloud.oauth.center.base.core.ResultGenerator;
import indi.cloud.oauth.center.base.model.MInstitutionBase;
import indi.cloud.oauth.center.base.model.TUserBase;
import indi.cloud.oauth.center.base.service.MInstitutionBaseService;
import indi.cloud.oauth.center.base.service.TUserBaseService;
import indi.cloud.oauth.center.model.SysAccount;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 机构/用户管理
 */
@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private TUserBaseService userService;

    @Resource
    private MInstitutionBaseService institutionService;


    /**
     * 获取用户详情
     *
     * @param user_uid
     * @return
     */
    @GetMapping("get")
    @Transactional
    public Result get(@RequestParam(value = "user_uid", required = false) String user_uid) {
        if (StringUtils.isBlank(user_uid)) {
            return ResultGenerator.genFailResult("用户UID[user_uid]不能为空");
        }

        try {
            TUserBase selectData = userService.selectBy("uuid", user_uid);

            if (selectData != null && selectData.getDeleteFlag() == 1) {
                return ResultGenerator.genSuccessResult(selectData);
            }
        } catch (Exception ex) {
            logger.error("获取用户信息发生异常", ex);
        }
        return ResultGenerator.genFailResult("用户不存在");
    }

    /**
     * 注册用户
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("register")
    @Transactional
    public Result register(Principal principal, @RequestBody(required = false) JSONObject postData) {

        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            String username = postData.getString("username");
            if (StringUtils.isBlank(username)) {
                return ResultGenerator.genFailResult("登录名[username]不能为空");
            }

            String name = postData.getString("name");
            if (StringUtils.isBlank(name)) {
                return ResultGenerator.genFailResult("用户名[name]不能为空");
            }

            String institutionUid = postData.getString("institution_uid");
            String institutionName = postData.getString("institution_name");
            if (StringUtils.isBlank(institutionUid) && StringUtils.isBlank(institutionName)) {
                return ResultGenerator.genFailResult("机构UID[institution_uid]和机构名[institution_name]至少需要一个。同时存在时机构UID优先。");
            }

            // 抽取机构
            MInstitutionBase institutionBase = null;
            if (StringUtils.isNotBlank(institutionUid)) {
                institutionBase = institutionService.selectBy("uid", institutionUid);
            }
            if (institutionBase == null) {
                institutionBase = institutionService.selectBy("name", institutionName);
            }
            if (institutionBase == null) {
                return ResultGenerator.genFailResult("机构不存在");
            }

            TUserBase tUserBase = userService.selectBy("username", username);
            if (tUserBase != null) {
                return ResultGenerator.genFailResult(String.format("用户名[%s]已存在", username));
            }

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            TUserBase insertData = new TUserBase();
            insertData.setInstitutionUid(institutionBase.getUid());
            insertData.setUuid(uuid);
            insertData.setName(name);
            insertData.setUsername(username);
            insertData.setCreateUser(principal.getName());
            insertData.setUpdateUser(principal.getName());

            // 生成密码
            String password = PasswordUtils.randomPassword();
            String encryptPassword = DigestUtils.md5DigestAsHex(password.getBytes());
            insertData.setPassword(encryptPassword);

            userService.save(insertData);

            Map retData = new HashMap();
            retData.put("username", insertData.getUsername());
            retData.put("password", password);

            return ResultGenerator.genSuccessResult(retData);

        } catch (Exception ex) {
            logger.error("新建用户发生异常", ex);
        }

        return ResultGenerator.genFailResult("新建失败");
    }

    /**
     * 添加用户
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("add")
    @Transactional
    public Result add(Principal principal, @RequestBody(required = false) JSONObject postData) {

        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            String institutionUid = postData.getString("institution_uid");
            String institutionName = postData.getString("institution_name");
            if (StringUtils.isBlank(institutionUid) && StringUtils.isBlank(institutionName)) {
                return ResultGenerator.genFailResult("机构UID[institution_uid]和机构名[institution_name]至少需要一个。同时存在时机构UID优先。");
            }

            // 抽取机构
            MInstitutionBase institutionBase = null;
            if (StringUtils.isNotBlank(institutionUid)) {
                institutionBase = institutionService.selectBy("uid", institutionUid);
            }
            if (institutionBase == null) {
                institutionBase = institutionService.selectBy("name", institutionName);
            }
            if (institutionBase == null) {
                return ResultGenerator.genFailResult("机构不存在");
            }

            // 批量添加
            Integer count = postData.getInteger("count");
            if (count == null) {
                // 未指定时，添加一个用户
                count = 1;
            }

            // 获取当前机构的用户一览
            Condition condition = new Condition(TUserBase.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("institutionUid", institutionBase.getUid());
            List<TUserBase> userList = userService.selectByCondition(condition);

            SimpleDateFormat sdf = new SimpleDateFormat("yyMM");

            List<Map> retData = new ArrayList<>();
            for (int i = 0; i < count; i++) {

                // aic + 年2位 + 月2位 + 内部机构id 2-3位 + 机构内用户ID3位
                String username = String.format("aic%s%03d%03d", sdf.format(new Date()), institutionBase.getId(), userList.size() + 1 + i);

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                TUserBase insertData = new TUserBase();
                insertData.setUuid(uuid);
                insertData.setInstitutionUid(institutionBase.getUid());
                insertData.setUsername(username);
                insertData.setName(username);
                String password = PasswordUtils.randomPassword();
                insertData.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                insertData.setDeleteFlag((byte) 0);
                insertData.setCreateUser(principal.getName());
                insertData.setUpdateUser(principal.getName());

                userService.save(insertData);

                Map curUser = new HashMap();
                curUser.put("username", insertData.getUsername());
                curUser.put("password", password);
                retData.add(curUser);
            }
            return ResultGenerator.genSuccessResult(retData);

        } catch (Exception ex) {
            logger.error("添加用户发生异常", ex);
        }

        return ResultGenerator.genFailResult("添加用户失败");
    }

    /**
     * 更改密码
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("changePassword")
    @Transactional
    public Result changePassword(Principal principal, @RequestBody(required = false) JSONObject postData) {

        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            TUserBase me = userService.selectBy("username", principal.getName());

            if (me == null) {
                return ResultGenerator.genFailResult("登录信息丢失，请重新登录。");
            }

            String oldPassword = postData.getString("oldPassword");
            if (StringUtils.isBlank(oldPassword)) {
                return ResultGenerator.genFailResult("旧密码不能为空");
            }

            String newPassword = postData.getString("newPassword");
            if (StringUtils.isBlank(newPassword)) {
                return ResultGenerator.genFailResult("新密码不能为空");
            } else if (newPassword.length() < 6 || newPassword.length() > 20) {
                return ResultGenerator.genFailResult("密码长度必须6-20位");
            }

            // 检查旧密码
            if (!StringUtils.equals(me.getPassword(), DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
                return ResultGenerator.genFailResult("旧密码不正确");
            }

            // 更新密码
            me.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            me.setUpdateUser(principal.getName());

            userService.update(me);

            return ResultGenerator.genSuccessResult("密码更改成功");
        } catch (Exception ex) {
            logger.error("密码更改发生异常", ex);
        }

        return ResultGenerator.genFailResult("密码更改失败");
    }


    /**
     * 重置密码
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("resetPassword")
    @Transactional
    public Result resetPassword(Principal principal, @RequestBody(required = false) JSONObject postData) {

        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            String username = postData.getString("username");
            if (StringUtils.isBlank(username)) {
                return ResultGenerator.genFailResult("用户名[username]不能为空");
            }

            // 确认用户存在
            TUserBase updateData = userService.selectBy("username", username);
            if (updateData == null || updateData.getDeleteFlag() == 1) {
                return ResultGenerator.genFailResult("用户不存在");
            }

            String newPassword = PasswordUtils.randomPassword();

            // 更新密码
            updateData.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            updateData.setUpdateUser(principal.getName());

            userService.update(updateData);

            return ResultGenerator.genSuccessResult("密码重置成功");
        } catch (Exception ex) {
            logger.error("密码重置发生异常", ex);
        }

        return ResultGenerator.genFailResult("密码重置失败");
    }

    /**
     * 更新用户
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("update")
    @Transactional
    public Result update(Principal principal, @RequestBody(required = false) JSONObject postData) {
        try {
            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            // 确认用户存在
            TUserBase updateData = userService.selectBy("username", principal.getName());
            if (updateData == null ||
                    (updateData.getDeleteFlag() != null && updateData.getDeleteFlag() == 1) ||
                    updateData.getState() != null && updateData.getState() == 2) {
                return ResultGenerator.genFailResult("用户已被删除或者冻结。");
            }

            String name = postData.getString("name");
            if (StringUtils.isNotBlank(name)) {
                // 更新名字
                updateData.setName(name);
            }

            String phone = postData.getString("phone");
            if (StringUtils.isNotBlank(phone)) {
                // 更新电话
                updateData.setPhone(phone);
            }

            String email = postData.getString("email");
            if (StringUtils.isNotBlank(email)) {
                // 更新email
                updateData.setEmail(email);
            }

            String openId = postData.getString("openId");
            if (StringUtils.isNotBlank(openId)) {
                // 更新微信OpenID
                updateData.setOpenId(openId);
            }

            updateData.setUpdateUser(principal.getName());

            // 更新
            userService.update(updateData);
            return ResultGenerator.genSuccessResult("更新成功");

        } catch (Exception ex) {
            logger.error("更新用户发生异常", ex);
        }

        return ResultGenerator.genFailResult("更新失败");
    }

    /**
     * 删除用户
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("delete")
    @Transactional
    public Result delete(Principal principal, @RequestBody(required = false) JSONObject postData) {
        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            String username = postData.getString("username");
            if (StringUtils.isBlank(username)) {
                return ResultGenerator.genFailResult("用户名[username]不能为空");
            }

            // 确认用户存在
            TUserBase updateData = userService.selectBy("username", username);
            if (updateData == null || updateData.getDeleteFlag() == 1) {
                return ResultGenerator.genFailResult("用户不存在");
            }

            // 逻辑删除
            updateData.setDeleteFlag((byte) 1);
            updateData.setUpdateUser(principal.getName());

            userService.update(updateData);

            return ResultGenerator.genSuccessResult("删除成功");

        } catch (Exception ex) {
            logger.error("删除用户发生异常", ex);
        }

        return ResultGenerator.genFailResult("删除失败");
    }

    /**
     * 冻结用户
     *
     * @param principal
     * @param postData
     * @return
     */
    @PostMapping("frozen")
    @Transactional
    public Result frozen(Principal principal, @RequestBody(required = false) JSONObject postData) {
        try {

            if (postData == null) {
                return ResultGenerator.genFailResult("参数不正确");
            }

            String username = postData.getString("username");
            if (StringUtils.isBlank(username)) {
                return ResultGenerator.genFailResult("用户名[username]不能为空");
            }

            // 确认用户存在
            TUserBase updateData = userService.selectBy("username", username);
            if (updateData == null || updateData.getDeleteFlag() == 1) {
                return ResultGenerator.genFailResult("用户不存在");
            }

            Byte state = postData.getByte("state");
            if (state == null || state != 0) {
                // 未指定，冻结
                state = 1;
            } else {
                state = 0;
            }

            // 冻结
            updateData.setState(state);
            updateData.setUpdateUser(principal.getName());
            userService.update(updateData);

            if (state == 1) {
                return ResultGenerator.genSuccessResult("冻结成功");
            } else {
                return ResultGenerator.genSuccessResult("解冻成功");
            }
        } catch (Exception ex) {
            logger.error("冻结/解冻用户发生异常", ex);
        }

        return ResultGenerator.genFailResult("冻结/解冻失败");
    }

    /**
     * 生成随机密码
     *
     * @return
     */
    @RequestMapping("createPassword")
    public Result createPassword() {

        return ResultGenerator.genSuccessResult(PasswordUtils.randomPassword());
    }

    /**
     * 查询当前机构下的所有用户
     *
     * @param auth
     * @return
     */
    @RequestMapping("queryInstitutionUsers")
    public Result queryInstitutionUsers(OAuth2Authentication auth) {
        try {
            SysAccount account = (SysAccount) auth.getPrincipal();
            Condition condition = new Condition(TUserBase.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("institutionUid", account.getInstitutionUid());
            criteria.andEqualTo("deleteFlag",0);
            List<TUserBase> userList = userService.selectByCondition(condition);

            List<Map> retList = new ArrayList<>();
            // 仅返回部分需要的数据
            for(TUserBase user : userList){
                Map map = new HashMap();
                map.put("uuid", user.getUuid());
                map.put("username", user.getUsername());
                map.put("name", user.getName());
                retList.add(map);
            }

            return ResultGenerator.genSuccessResult(retList);
        } catch (Exception ex) {
            logger.error("查询当前机构下的所有用户异常", ex);
        }

        return ResultGenerator.genFailResult("查询当前机构下的所有用户失败");
    }
}
