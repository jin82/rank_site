package info.epochpro.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 得到真实IP地址类
 * Created by jin on 2016/12/10.
 */
public class IPUtils {
    /**
     * 获取客户端真实IP地址
     * @param request http请求
     * @return IP地址
     */
    public static String getClientIp(HttpServletRequest request) {

        String reqURI = request.getRequestURI();

        if (!reqURI.contains("j_security_check_client")) {

            if (reqURI.contains("j_security_check")) {	//HTTPS IP处理

                return (String)request.getSession().getAttribute("session_ip");

            }
        }
        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("L7-forwarded-for");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取mac地址
     *
     * @param ip
     * @return
     */
    public static String getMACAddress(String ip) {
        String line = "";
        String macAddress = "";
        final String MAC_ADDRESS_PREFIX = "MAC";
        final String LOOPBACK_ADDRESS = "127.0.0.1";

        try {
            //如果为127.0.0.1,则获取本地MAC地址。
            if (LOOPBACK_ADDRESS.equals(ip)) {
                InetAddress inetAddress = InetAddress.getLocalHost();
                //貌似此方法需要JDK1.6。
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
                byte[] mac = networkInterface.getHardwareAddress();
                if (networkInterface == null || mac == null) {
                    return "127.0.0.1";
                }

                //下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    //mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
                //把字符串所有小写字母改为大写成为正规的mac地址并返回
                macAddress = sb.toString().trim().toUpperCase();
                return macAddress;
            }
            //获取非本地IP的MAC地址
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);
                    if (index != -1) {
                        index = line.indexOf("=");
                        macAddress = line.substring(index + 1).trim().toUpperCase();
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            return "";
        }
        return macAddress;
    }

}
