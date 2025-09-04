package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private boolean isActive;
    private String userAccount;
    private String userName;
    private String userPwd;
    private String userAvatar;
    private String userEmail;
    private Integer userRole;
    private LocalDateTime registrationDate;
    private Boolean isLogin;
    private Boolean isWord;
    private String addresses;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 获取用户ID
     * @return 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户ID
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 检查用户是否激活
     * @return true表示激活，false表示未激活
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * 设置用户激活状态
     * @param active true表示激活，false表示未激活
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * 获取用户账号
     * @return 用户账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户账号
     * @param userAccount 用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户密码（注意：实际应用中密码不应以明文形式存储）
     * @return 用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置用户密码
     * @param userPwd 用户密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取用户头像URL
     * @return 用户头像URL
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置用户头像URL
     * @param userAvatar 用户头像URL
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * 获取用户邮箱
     * @return 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取用户角色ID
     * @return 用户角色ID
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置用户角色ID
     * @param userRole 用户角色ID
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    /**
     * 获取注册日期
     * @return 注册日期
     */
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    /**
     * 设置注册日期
     * @param registrationDate 注册日期
     */
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * 检查用户是否登录
     * @return true表示已登录，false/null表示未登录
     */
    public Boolean getLogin() {
        return isLogin;
    }

    /**
     * 设置用户登录状态
     * @param login true表示已登录，false表示未登录
     */
    public void setLogin(Boolean login) {
        isLogin = login;
    }

    /**
     * 获取用户是否已验证（isWord可能代表验证状态）
     * @return 验证状态
     */
    public Boolean getWord() {
        return isWord;
    }

    /**
     * 设置用户验证状态
     * @param word 验证状态
     */
    public void setWord(Boolean word) {
        isWord = word;
    }

    /**
     * 获取用户地址（JSON格式字符串）
     * @return 地址信息
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * 设置用户地址（JSON格式字符串）
     * @param addresses 地址信息
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
