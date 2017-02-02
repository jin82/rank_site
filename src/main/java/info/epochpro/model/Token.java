package info.epochpro.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Token 类
 * Created by jin on 2017/2/2.
 */
public class Token implements Serializable{

    private String token;

    private String type;

    private long expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("type", type)
                .add("expires", expires)
                .toString();
    }
}
