package com.ailearn.note.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fulai on 2018/6/20.
 */

public class JsonUtil {
    /**
     * 对象转为json
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toJson(T t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static <T> T getBean(String json, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }

    /**
     * 将json转为list集合
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static <T> List<T> parse(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
}
