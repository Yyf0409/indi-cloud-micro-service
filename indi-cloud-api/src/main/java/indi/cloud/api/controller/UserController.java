package indi.cloud.api.controller;

import indi.cloud.api.base.core.Result;
import indi.cloud.api.base.core.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
//    TUserBaseMapper tUserBaseMapper;
//
//    /**
//     *
//     * @return
//     */
//    @GetMapping("getDetails")
//    public Result getUser(String username){
//
//        TUserBase condition = new TUserBase();
//        condition.setUsername(username);
//        TUserBase result =  tUserBaseMapper.selectOne(condition);
//
//        if(result != null){
//            return ResultGenerator.genSuccessResult(result);
//        }
//        return ResultGenerator.genFailResult("用户不存在");
//    }

}
