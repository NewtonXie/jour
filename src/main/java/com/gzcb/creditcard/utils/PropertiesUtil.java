package com.gzcb.creditcard.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 读取resource文件夹中配置文件工具类
 * @author lwj
 * @date 2017/9/19
 */
public class PropertiesUtil implements Serializable{
    public String fileName;
    Properties pps = new Properties();

    public PropertiesUtil(String fileName){
        this.fileName = fileName;
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = this.getClass().getResourceAsStream(fileName);
            pps.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (resourceAsStream != null){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getString(String key){
        return pps.getProperty(key);
    }

    public int getInt(String key){
        return Integer.parseInt(pps.getProperty(key));
    }

    public boolean getBoolean(String key){
        return Boolean.parseBoolean(pps.getProperty(key));
    }

    public String getProp(String key, String defaultValue) {
        String result = pps.getProperty(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }
}
