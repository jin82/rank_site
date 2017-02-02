package info.epochpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表实体
 * Created by jin on 2016/12/11.
 */
@Document(collection = "users")
public class User implements Serializable{

    @Id
    private String uuid;

    @Indexed(unique = true)
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 3,max = 16,message = "用户名长度必须在3-16位之间")
    private String name;

    @NotEmpty(message = "昵称不能为空")
    @Length(min = 3,max = 16,message = "昵称长度必须在3-16位之间")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 32,max = 32,message = "密码格式有误")
    private String password;

    private Date regDate;

    @JsonIgnore
    private String secret;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uuid", uuid)
                .add("name", name)
                .add("nickname", nickname)
                .add("password", password)
                .add("regDate", regDate)
                .add("secret", secret)
                .toString();
    }
}


