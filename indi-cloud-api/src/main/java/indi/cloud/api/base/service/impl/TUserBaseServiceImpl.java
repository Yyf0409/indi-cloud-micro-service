package indi.cloud.api.base.service.impl;

import indi.cloud.api.base.dao.TUserBaseMapper;
import indi.cloud.api.base.model.TUserBase;
import indi.cloud.api.base.service.TUserBaseService;
import indi.cloud.api.base.core.AbstractService;
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
