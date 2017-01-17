package info.epochpro.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jin on 2016/12/11.
 */

public class EncryptUtils {

    private static final Log log = LogFactory.getLog(EncryptUtils.class);


    /**
     * 对字符串md5加密
     *
     * @param str 字符串
     * @return 加密结果
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.error("MD5加密"+str+"错误",e);
            throw new RuntimeException();
        }
    }

    /**
     * AES算法加密文本
     *
     * @param secretKey
     *            密钥
     * @param originalString
     *            需要加密的文本
     * @return 加密的文本(Base64格式)
     *
     * 123456789012345678
     **/
    public static String aesEncrypt(String secretKey, String originalString) {
        if (StringUtils.isEmpty(secretKey))
            throw new IllegalArgumentException("密钥不能为空");
        if (StringUtils.isEmpty(originalString))
            throw new IllegalArgumentException("原始字符串不能为空");

        final byte[] byteKey = toByteArray(secretKey);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] originalBytes = originalString.trim().getBytes("UTF-8");
            if (originalBytes.length % 16 != 0) {
                byte[] newOriginalBytes = new byte[(originalBytes.length / 16 + 1) * 16];
                System.arraycopy(originalBytes, 0, newOriginalBytes, 0, originalBytes.length);
                originalBytes = newOriginalBytes;
            }

            final byte[] encryptedBytes = cipher.doFinal(originalBytes);
            return encoder.encode(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(secretKey + " 是非法的," + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new IllegalArgumentException("加密失败 '" + originalString + "'," + e.getMessage());
        }
        return null;
    }

    /**
     * AES算法解密文本
     *
     * @param secretKey
     *            密钥
     * @param encryptedString
     *            被加密的文本(Base64格式)
     *
     * @return 还原回原先的文本
     **/
    public static String aesDecrypt(String secretKey, String encryptedString) {
        if (StringUtils.isEmpty(secretKey))
            throw new IllegalArgumentException("密钥不能为空");
        if (StringUtils.isEmpty(encryptedString))
            throw new IllegalArgumentException("原始字符串不能为空");

        encryptedString = encryptedString.trim();
        final byte[] byteKey = toByteArray(secretKey);
        BASE64Decoder decoder = new BASE64Decoder();
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            final byte[] encryptBytes = decoder.decodeBuffer(encryptedString);
            final byte[] originalBytes = cipher.doFinal(encryptBytes);
            return new String(originalBytes, "UTF-8").trim();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(secretKey + " 是非法的," + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new IllegalArgumentException("解密失败 '" + encryptedString + "'," + e.getMessage());
        } catch (BadPaddingException e) {
            throw new IllegalArgumentException(encryptedString + " 是非法的数据," + e.getMessage());
        }
        return null;
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString
     *            16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("16进制的字符串不能为空");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * SHA 加密
     * @param decript 原文
     * @return 加密文
     */
    public static String SHA(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
