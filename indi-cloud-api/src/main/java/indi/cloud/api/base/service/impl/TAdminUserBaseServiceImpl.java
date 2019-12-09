package indi.cloud.api.base.service.impl;

import indi.cloud.api.base.dao.TAdminUserBaseMapper;
import indi.cloud.api.base.model.TAdminUserBase;
import indi.cloud.api.base.service.TAdminUserBaseService;
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
public class TAdminUserBaseServiceImpl extends AbstractService<TAdminUserBase> implements TAdminUserBaseService {
    @Resource
    private TAdminUserBaseMapper tAdminUserBaseMapper;

}
