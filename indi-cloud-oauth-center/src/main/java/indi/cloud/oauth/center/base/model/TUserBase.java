package indi.cloud.oauth.center.base.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class TUserBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 唯一UUID
     */
    private String uuid;

    /**
     * 所属机构UUID
     */
    @Column(name = "institution_uid")
    private String institutionUid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 区
     */
    private Integer area;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 过期时间
     */
    private Date expired;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 是否是管理员
     */
    @Column(name = "admin_flag")
    private Byte adminFlag;

    /**
     * 状态 0：正常，1：冻结
     */
    private Byte state;

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
     * 微信唯一识别openid
     */
    @Column(name = "open_id")
    private String openId;

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
     * 获取唯一UUID
     *
     * @return uuid - 唯一UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置唯一UUID
     *
     * @param uuid 唯一UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取所属机构UUID
     *
     * @return institution_uid - 所属机构UUID
     */
    public String getInstitutionUid() {
        return institutionUid;
    }

    /**
     * 设置所属机构UUID
     *
     * @param institutionUid 所属机构UUID
     */
    public void setInstitutionUid(String institutionUid) {
        this.institutionUid = institutionUid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public Integer getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return area - 区
     */
    public Integer getArea() {
        return area;
    }

    /**
     * 设置区
     *
     * @param area 区
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取是否是管理员
     *
     * @return admin_flag - 是否是管理员
     */
    public Byte getAdminFlag() {
        return adminFlag;
    }

    /**
     * 设置是否是管理员
     *
     * @param adminFlag 是否是管理员
     */
    public void setAdminFlag(Byte adminFlag) {
        this.adminFlag = adminFlag;
    }

    /**
     * 获取状态 0：正常，1：冻结
     *
     * @return state - 状态 0：正常，1：冻结
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置状态 0：正常，1：冻结
     *
     * @param state 状态 0：正常，1：冻结
     */
    public void setState(Byte state) {
        this.state = state;
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

    /**
     * 获取微信唯一识别openid
     *
     * @return open_id - 微信唯一识别openid
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信唯一识别openid
     *
     * @param openId 微信唯一识别openid
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}