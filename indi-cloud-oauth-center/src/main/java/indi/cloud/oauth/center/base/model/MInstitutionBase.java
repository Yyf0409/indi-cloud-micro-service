package indi.cloud.oauth.center.base.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "m_institution")
public class MInstitutionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * UUID
     */
    private String uid;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构性质
     */
    @Column(name = "organization_nature")
    private String organizationNature;

    /**
     * 组织机构代码标识 1:统一社会信用代码 2：组织机构代码 3：事证号 4:其他
     */
    @Column(name = "organization_code_flag")
    private Byte organizationCodeFlag;

    /**
     * 组织机构代码
     */
    @Column(name = "organization_code")
    private String organizationCode;

    /**
     * 机构规模
     */
    @Column(name = "organization_scale")
    private String organizationScale;

    /**
     * 所属行业 一级行业
     */
    @Column(name = "industry_first")
    private String industryFirst;

    /**
     * 所属行业 二级行业
     */
    @Column(name = "industry_second")
    private String industrySecond;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 办公地址
     */
    @Column(name = "office_address")
    private String officeAddress;

    /**
     * 成立时间
     */
    private Date foundtime;

    /**
     * 法人代表姓名
     */
    @Column(name = "legal_name")
    private String legalName;

    /**
     * 办公电话区号
     */
    @Column(name = "office_phone_areacode")
    private String officePhoneAreacode;

    /**
     * 办公电话
     */
    @Column(name = "office_phone")
    private String officePhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 传真号码
     */
    private String fax;

    /**
     * 公司官网
     */
    @Column(name = "web_site")
    private String webSite;

    /**
     * 联系人姓名
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系人所在部门
     */
    @Column(name = "contact_dep")
    private String contactDep;

    /**
     * 联系人职务
     */
    @Column(name = "contact_job")
    private String contactJob;

    /**
     * 联系人电话区号
     */
    @Column(name = "contact_phone_areacode")
    private String contactPhoneAreacode;

    /**
     * 联系人电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 联系人手机号码
     */
    @Column(name = "contact_phone_num")
    private String contactPhoneNum;

    /**
     * 联系人电子邮箱
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 联系人微信号
     */
    @Column(name = "contact_wechat")
    private String contactWechat;

    /**
     * 联系人QQ号
     */
    @Column(name = "contact_qq")
    private String contactQq;

    /**
     * 审核原因
     */
    @Column(name = "examine_reason")
    private String examineReason;

    /**
     * 审核状态 1：待审核；2：拒绝；3:使用中（即审核通过）；4：启用；99:停用。
     */
    private Byte examinestate;

    /**
     * 使用状态 1：启用；2：停用 
     */
    private Byte state;

    /**
     * 过期时间
     */
    private Date expired;

    /**
     * 删除标志
     */
    @Column(name = "delete_flag")
    private Byte deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新者
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取UUID
     *
     * @return uid - UUID
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置UUID
     *
     * @param uid UUID
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取机构名称
     *
     * @return name - 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机构名称
     *
     * @param name 机构名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取机构性质
     *
     * @return organization_nature - 机构性质
     */
    public String getOrganizationNature() {
        return organizationNature;
    }

    /**
     * 设置机构性质
     *
     * @param organizationNature 机构性质
     */
    public void setOrganizationNature(String organizationNature) {
        this.organizationNature = organizationNature;
    }

    /**
     * 获取组织机构代码标识 1:统一社会信用代码 2：组织机构代码 3：事证号 4:其他
     *
     * @return organization_code_flag - 组织机构代码标识 1:统一社会信用代码 2：组织机构代码 3：事证号 4:其他
     */
    public Byte getOrganizationCodeFlag() {
        return organizationCodeFlag;
    }

    /**
     * 设置组织机构代码标识 1:统一社会信用代码 2：组织机构代码 3：事证号 4:其他
     *
     * @param organizationCodeFlag 组织机构代码标识 1:统一社会信用代码 2：组织机构代码 3：事证号 4:其他
     */
    public void setOrganizationCodeFlag(Byte organizationCodeFlag) {
        this.organizationCodeFlag = organizationCodeFlag;
    }

    /**
     * 获取组织机构代码
     *
     * @return organization_code - 组织机构代码
     */
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**
     * 设置组织机构代码
     *
     * @param organizationCode 组织机构代码
     */
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     * 获取机构规模
     *
     * @return organization_scale - 机构规模
     */
    public String getOrganizationScale() {
        return organizationScale;
    }

    /**
     * 设置机构规模
     *
     * @param organizationScale 机构规模
     */
    public void setOrganizationScale(String organizationScale) {
        this.organizationScale = organizationScale;
    }

    /**
     * 获取所属行业 一级行业
     *
     * @return industry_first - 所属行业 一级行业
     */
    public String getIndustryFirst() {
        return industryFirst;
    }

    /**
     * 设置所属行业 一级行业
     *
     * @param industryFirst 所属行业 一级行业
     */
    public void setIndustryFirst(String industryFirst) {
        this.industryFirst = industryFirst;
    }

    /**
     * 获取所属行业 二级行业
     *
     * @return industry_second - 所属行业 二级行业
     */
    public String getIndustrySecond() {
        return industrySecond;
    }

    /**
     * 设置所属行业 二级行业
     *
     * @param industrySecond 所属行业 二级行业
     */
    public void setIndustrySecond(String industrySecond) {
        this.industrySecond = industrySecond;
    }

    /**
     * 获取注册地址
     *
     * @return address - 注册地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置注册地址
     *
     * @param address 注册地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取办公地址
     *
     * @return office_address - 办公地址
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * 设置办公地址
     *
     * @param officeAddress 办公地址
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /**
     * 获取成立时间
     *
     * @return foundtime - 成立时间
     */
    public Date getFoundtime() {
        return foundtime;
    }

    /**
     * 设置成立时间
     *
     * @param foundtime 成立时间
     */
    public void setFoundtime(Date foundtime) {
        this.foundtime = foundtime;
    }

    /**
     * 获取法人代表姓名
     *
     * @return legal_name - 法人代表姓名
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置法人代表姓名
     *
     * @param legalName 法人代表姓名
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取办公电话区号
     *
     * @return office_phone_areacode - 办公电话区号
     */
    public String getOfficePhoneAreacode() {
        return officePhoneAreacode;
    }

    /**
     * 设置办公电话区号
     *
     * @param officePhoneAreacode 办公电话区号
     */
    public void setOfficePhoneAreacode(String officePhoneAreacode) {
        this.officePhoneAreacode = officePhoneAreacode;
    }

    /**
     * 获取办公电话
     *
     * @return office_phone - 办公电话
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * 设置办公电话
     *
     * @param officePhone 办公电话
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取传真号码
     *
     * @return fax - 传真号码
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真号码
     *
     * @param fax 传真号码
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取公司官网
     *
     * @return web_site - 公司官网
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * 设置公司官网
     *
     * @param webSite 公司官网
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    /**
     * 获取联系人姓名
     *
     * @return contact_name - 联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系人姓名
     *
     * @param contactName 联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取联系人所在部门
     *
     * @return contact_dep - 联系人所在部门
     */
    public String getContactDep() {
        return contactDep;
    }

    /**
     * 设置联系人所在部门
     *
     * @param contactDep 联系人所在部门
     */
    public void setContactDep(String contactDep) {
        this.contactDep = contactDep;
    }

    /**
     * 获取联系人职务
     *
     * @return contact_job - 联系人职务
     */
    public String getContactJob() {
        return contactJob;
    }

    /**
     * 设置联系人职务
     *
     * @param contactJob 联系人职务
     */
    public void setContactJob(String contactJob) {
        this.contactJob = contactJob;
    }

    /**
     * 获取联系人电话区号
     *
     * @return contact_phone_areacode - 联系人电话区号
     */
    public String getContactPhoneAreacode() {
        return contactPhoneAreacode;
    }

    /**
     * 设置联系人电话区号
     *
     * @param contactPhoneAreacode 联系人电话区号
     */
    public void setContactPhoneAreacode(String contactPhoneAreacode) {
        this.contactPhoneAreacode = contactPhoneAreacode;
    }

    /**
     * 获取联系人电话
     *
     * @return contact_phone - 联系人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系人电话
     *
     * @param contactPhone 联系人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取联系人手机号码
     *
     * @return contact_phone_num - 联系人手机号码
     */
    public String getContactPhoneNum() {
        return contactPhoneNum;
    }

    /**
     * 设置联系人手机号码
     *
     * @param contactPhoneNum 联系人手机号码
     */
    public void setContactPhoneNum(String contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    /**
     * 获取联系人电子邮箱
     *
     * @return contact_email - 联系人电子邮箱
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * 设置联系人电子邮箱
     *
     * @param contactEmail 联系人电子邮箱
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * 获取联系人微信号
     *
     * @return contact_wechat - 联系人微信号
     */
    public String getContactWechat() {
        return contactWechat;
    }

    /**
     * 设置联系人微信号
     *
     * @param contactWechat 联系人微信号
     */
    public void setContactWechat(String contactWechat) {
        this.contactWechat = contactWechat;
    }

    /**
     * 获取联系人QQ号
     *
     * @return contact_qq - 联系人QQ号
     */
    public String getContactQq() {
        return contactQq;
    }

    /**
     * 设置联系人QQ号
     *
     * @param contactQq 联系人QQ号
     */
    public void setContactQq(String contactQq) {
        this.contactQq = contactQq;
    }

    /**
     * 获取审核原因
     *
     * @return examine_reason - 审核原因
     */
    public String getExamineReason() {
        return examineReason;
    }

    /**
     * 设置审核原因
     *
     * @param examineReason 审核原因
     */
    public void setExamineReason(String examineReason) {
        this.examineReason = examineReason;
    }

    /**
     * 获取审核状态 1：待审核；2：拒绝；3:使用中（即审核通过）；4：启用；99:停用。
     *
     * @return examinestate - 审核状态 1：待审核；2：拒绝；3:使用中（即审核通过）；4：启用；99:停用。
     */
    public Byte getExaminestate() {
        return examinestate;
    }

    /**
     * 设置审核状态 1：待审核；2：拒绝；3:使用中（即审核通过）；4：启用；99:停用。
     *
     * @param examinestate 审核状态 1：待审核；2：拒绝；3:使用中（即审核通过）；4：启用；99:停用。
     */
    public void setExaminestate(Byte examinestate) {
        this.examinestate = examinestate;
    }

    /**
     * 获取使用状态 1：启用；2：停用 
     *
     * @return state - 使用状态 1：启用；2：停用 
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置使用状态 1：启用；2：停用 
     *
     * @param state 使用状态 1：启用；2：停用 
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取过期时间
     *
     * @return expired - 过期时间
     */
    public Date getExpired() {
        return expired;
    }

    /**
     * 设置过期时间
     *
     * @param expired 过期时间
     */
    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /**
     * 获取删除标志
     *
     * @return delete_flag - 删除标志
     */
    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标志
     *
     * @param deleteFlag 删除标志
     */
    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建者
     *
     * @return create_user - 创建者
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建者
     *
     * @param createUser 创建者
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新者
     *
     * @return update_user - 更新者
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新者
     *
     * @param updateUser 更新者
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}