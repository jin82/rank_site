package info.epochpro.common;

/**
 * 微信token验证类
 * Created by jin on 2016/12/13.
 */
public class WxSign {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WxSign{");
        sb.append("signature='").append(signature).append('\'');
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", nonce='").append(nonce).append('\'');
        sb.append(", echostr='").append(echostr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
