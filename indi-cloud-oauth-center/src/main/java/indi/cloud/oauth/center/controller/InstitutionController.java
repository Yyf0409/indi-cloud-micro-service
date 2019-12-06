package indi.cloud.oauth.center.controller;

import indi.cloud.oauth.center.base.core.Result;
import indi.cloud.oauth.center.base.core.ResultGenerator;
import indi.cloud.oauth.center.base.model.MInstitutionBase;
import indi.cloud.oauth.center.base.service.MInstitutionBaseService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 机构/用户管理
 */
@CrossOrigin
@RestController
@RequestMapping("api/institution")
public class InstitutionController {

    private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);

    @Resource
    MInstitutionBaseService service;

    @GetMapping("get")
    @Transactional
    public Result get(@RequestParam(value = "institution_uid", required = false) String institution_uid) {
        if (StringUtils.isBlank(institution_uid)) {
            return ResultGenerator.genFailResult("机构UID[institution_uid]不能为空");
        }

        try {
            MInstitutionBase selectData = service.selectBy("uid", institution_uid);

            if (selectData != null && selectData.getDeleteFlag() != 1 && selectData.getState() != 2) {
                return ResultGenerator.genSuccessResult(selectData);
            }
        } catch (Exception ex) {
            logger.error("获取机构信息发生异常", ex);
        }
        return ResultGenerator.genFailResult("机构不存在");
    }

    @PostMapping("add")
    @Transactional
    public Result add(@RequestBody(required = false)JSONObject postData) {

        try {

            if(postData == null){
                return ResultGenerator.genFailResult("参数不正确");
            }
            
            String name = postData.getString("name");
            if(StringUtils.isBlank(name)){
                return ResultGenerator.genFailResult("机构名称[name]不能为空");
            }

            String creditCode = postData.getString("credit_code");
            if(StringUtils.isBlank(creditCode)){
                return ResultGenerator.genFailResult("统一社会信用代码[credit_code]不能为空");
            }

            MInstitutionBase mInstitutionBase = service.selectBy("name", name);
            if(mInstitutionBase != null){
                return  ResultGenerator.genFailResult(String.format("机构[%s]已存在", name));
            }

            String uid = UUID.randomUUID().toString().replaceAll("-", "");

            MInstitutionBase insertData = new MInstitutionBase();
            insertData.setName(name);
            insertData.setUid(uid);
            insertData.setOrganizationCode(creditCode);
            service.save(insertData);
            return ResultGenerator.genSuccessResult(insertData.getUid());

        } catch (Exception ex) {
            logger.error("新建机构失败发生异常",ex);
        }

        return ResultGenerator.genFailResult("新建机构失败");
    }

//    @PostMapping("update")
//    @Transactional
//    public Result update(){
//        return ResultGenerator.genFailResult("机构不允许更新");
//    }

//    @PostMapping("delete")
//    @Transactional
//    public Result delete(@RequestBody(required = false)JSONObject postData){
//        return ResultGenerator.genFailResult("机构不允许删除");
//    }
}
