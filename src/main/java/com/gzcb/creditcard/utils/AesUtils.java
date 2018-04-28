package com.gzcb.creditcard.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.SecureRandom;

/**
 * @author wulinzhen
 */
public class AesUtils {

    private static Logger logger = LoggerFactory.getLogger(AesUtils.class);

    /**
     * 秘钥
     */
    public static String CRYPT_KEY = new PropertiesUtil("/service/ini.properties").getString("crypt_key");
    private static final String AES = "AES";
    private static final int LENGTH = 128;
    private static Charset UTF8 = Charset.forName("UTF-8");

    private static byte[] encryptAes(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(data);
        return result;
    }

    public static String encryptAesToHexStr(String src) throws Exception {
        byte[] key = getKey();
        byte[] bytes = encryptAes(src.getBytes(UTF8), key);
        return byteToHexString(bytes);
    }

    private static byte[] getKey() {
        KeyGenerator keyGenerator = null;
        byte[] key = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES);
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(CRYPT_KEY.getBytes(UTF8));
            keyGenerator.init(LENGTH, random);
            SecretKey secretKey = keyGenerator.generateKey();
            key = secretKey.getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("密钥key生产错误");
        }
        return key;
    }

    public static String decryptAesHexToStr(String hexStr) throws Exception {
        byte[] key = getKey();
        byte[] bytes = hexStringToByte(hexStr);
        byte[] result = decryptAes(bytes, key);
        return new String(result, UTF8);
    }

    private static byte[] decryptAes(byte[] src, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(src);
        return result;
    }

    private static String byteToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hv);
        }
        return stringBuffer.toString().toUpperCase();

    }

    private static byte[] hexStringToByte(String hexStr) {
        if (hexStr == null || "".equals(hexStr)) {
            return null;
        }
        hexStr = hexStr.toUpperCase();
        int length = hexStr.length() / 2;
        char[] hexChars = hexStr.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;

    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) throws Exception {
        String end = "Gzcb-098";
        String ens = encryptAesToHexStr(end);
        System.out.println(ens);
        logger.info(ens);
        String src = decryptAesHexToStr(ens);
        System.out.println(src);
        logger.info(src);
    }
}
