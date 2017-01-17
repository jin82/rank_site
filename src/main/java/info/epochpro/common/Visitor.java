package info.epochpro.common;

import info.epochpro.common.util.EncryptUtils;
import info.epochpro.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * 当前的用户
 * Created by jin on 2016/12/11.
 */
public class Visitor implements Serializable{

    private static final Log log = LogFactory.getLog(Visitor.class);

    private Long id;

    private String name;

    private String key;

    private String msg;

    private boolean success;

    private String mac;

    private String ip;

    private Visitor(String key) {
        this.key = key;
    }

    public static Visitor getInstance(String key) {
        return new Visitor(key);
    }

    public User createUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public String createValue() {
        String str = id + ","+name+","+ip+","+mac;
        return EncryptUtils.aesEncrypt(key, str);
    }

    public Visitor createVisitor(String value) {
        String errorMsg = "登陆用户cookie值错误";

        String str = EncryptUtils.aesDecrypt(key,value);
        if (str == null) {
            return Visitor.fail(errorMsg);
        }
        String params[] = str.split(",");
        if (params.length != 4) {
            return Visitor.fail(errorMsg);
        }

        Long id;
        try {
            id = Long.parseLong(params[0]);
        } catch (Exception e) {
            return Visitor.fail(errorMsg);
        }

        String name = params[1];
        String ip = params[2];
        String mac = params[3];

        this.id = id;
        this.name = name;
        this.ip = ip;
        this.mac = mac;
        return this;
    }

    public Visitor createVisitor(User user) {
        this.id = user.getId();
        this.name = user.getName();
        return this;
    }

    public Visitor(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public static Visitor fail(String msg) {
        log.error(msg);
        return new Visitor(msg, false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
