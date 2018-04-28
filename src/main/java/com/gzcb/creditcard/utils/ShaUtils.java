package com.gzcb.creditcard.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author liting
 */
public class ShaUtils {

    private static final String SHA = "SHA";
    private static Logger logger = LoggerFactory.getLogger(ShaUtils.class);

    public static String getSHACode(String originalString) {
        try {
            if (originalString == null || originalString.length() == 0) {
                throw new Exception("需要加密的明文为空");
            }
            byte[] originalByte = originalString.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance(SHA);
            md.update(originalByte);
            originalByte = md.digest();
            String rtv = "";
            for (int i = 0; i < originalByte.length; i++) {
                int temp = originalByte[i] & 0xFF;
                String s = Integer.toHexString(temp);
                if (s.length() == 1) {
                    s += "0";
                }
                rtv += s;
            }
            return rtv;
        } catch (Exception ex) {
            logger.error("密码加密失败", ex);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ShaUtils.getSHACode("05750123456"));
    }
}
