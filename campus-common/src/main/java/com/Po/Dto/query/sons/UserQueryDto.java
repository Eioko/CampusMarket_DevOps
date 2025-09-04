package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends QueryDto {

    private String userAccount;
    private String userName;
    private String userEmail;
    private Boolean role;
    private Boolean isLogin;
    private Boolean isWord;

    // Getter Methods
    public String getUserAccount() {
        return userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Boolean getRole() {
        return role;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public Boolean getWord() {
        return isWord;
    }

    // Setter Methods
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public void setWord(Boolean word) {
        isWord = word;
    }
}

