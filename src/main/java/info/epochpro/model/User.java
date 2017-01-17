package info.epochpro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 用户表实体
 * Created by jin on 2016/12/11.
 */
@Entity(name = "d_user")
public class User extends Base{


    @Column(name = "name",unique = true,nullable = false,length = 32)
    private String name;

    @Column(name = "nickname",nullable = false,length = 20)
    private String nickname;

    @Column(name = "password",nullable = false,length = 32)
    private String password;

    @Column(name = "reg_date",nullable = false)
    private Date regDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "open_id")
    private String openid;

    public User(String name, String nickname, String password, Date regDate, Date lastLoginDate, String openid) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.regDate = regDate;
        this.lastLoginDate = lastLoginDate;
        this.openid = openid;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
