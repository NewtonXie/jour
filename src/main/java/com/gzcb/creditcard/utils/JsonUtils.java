package com.gzcb.creditcard.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * @author ???
 */
public class JsonUtils {
    static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static JSONObject filterNull(JSONObject jsonObject) {
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(jsonObject,
                SerializerFeature.WriteMapNullValue));
        Iterator<String> iterator = jsonObject.keySet().iterator();
        JSONObject res = new JSONObject();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (null == value || "null".equals(value)
                    || "".equals(value)) {
                value = "";
            }
            if (value instanceof JSONObject) {
                value = filterNull((JSONObject) value);
            }
            if (value instanceof JSONArray) {
                value = filterNull((JSONArray) value);
            }
            res.put(key, value);
        }
        return res;
    }

    public static JSONArray filterNull(JSONArray jsonArray) {
        JSONArray resArray = new JSONArray();
        jsonArray = JSONArray.parseArray(JSONObject.toJSONString(jsonArray,
                SerializerFeature.WriteMapNullValue));
        for (Object o : jsonArray) {
            if (null == o || "null".equals(o)) {
                continue;
            }
            if (o instanceof JSONObject) {
                o = filterNull((JSONObject) o);
            } else if (o instanceof JSONArray) {
                o = filterNull((JSONArray) o);
            }
            resArray.add(o);
        }
        return resArray;
    }
    
}
