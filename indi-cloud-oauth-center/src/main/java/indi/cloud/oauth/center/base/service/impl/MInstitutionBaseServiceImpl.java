package indi.cloud.oauth.center.base.service.impl;

import indi.cloud.oauth.center.base.dao.MInstitutionBaseMapper;
import indi.cloud.oauth.center.base.model.MInstitutionBase;
import indi.cloud.oauth.center.base.service.MInstitutionBaseService;
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
public class MInstitutionBaseServiceImpl extends AbstractService<MInstitutionBase> implements MInstitutionBaseService {
    @Resource
    private MInstitutionBaseMapper mInstitutionBaseMapper;

}
