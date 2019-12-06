package indi.cloud.oauth.center.base.service.impl;

import indi.cloud.oauth.center.base.dao.TUserBaseMapper;
import indi.cloud.oauth.center.base.model.TUserBase;
import indi.cloud.oauth.center.base.service.TUserBaseService;
import indi.cloud.oauth.center.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by CodeGenerator
* Version 1.0.0
*/
@Service
@Transactional
public class TUserBaseServiceImpl extends AbstractService<TUserBase> implements TUserBaseService {
    @Resource
    private TUserBaseMapper tUserBaseMapper;

}
