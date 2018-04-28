package com.gzcb.creditcard.utils;

import com.gzcb.creditcard.dao.entities.User;

import java.io.*;

/**
 * 序列化
 */
public class SerializableUtil {
    /**
     * 序列化
     * @param o
     * @throws Exception
     */
    public static byte[] serialize(Object o)throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(o);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        byteArrayOutputStream.close();
        objectOutputStream.close();
        return bytes;
    }

    /**
     * 反序列化
     * @return
     * @throws Exception
     */
    public static Object deserialize(byte[] bytes)throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object o =  objectInputStream.readObject();

        byteArrayInputStream.close();
        objectInputStream.close();
        return o;
    }

    public static void main(String[] args)throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("xys");
        System.out.println(SerializableUtil.serialize(user).toString());
        User user1 = (User)deserialize(SerializableUtil.serialize(user));
        System.out.println(user1.getName());
    }
}
