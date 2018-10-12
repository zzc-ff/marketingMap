package com.example.stringbootbo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
/**
 *
 *
 * @author
 * @date 2018/10/31
 */
public class IsAllFieldNull {
    public static Boolean isAllFieldNull(Object obj) throws Exception {
        Class stuCla = (Class) obj.getClass();
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        boolean r = false;
        for (Field f : fs) {
            f.setAccessible(true);
            Object val = f.get(obj);
            IsItNecessary t = (IsItNecessary) f.getAnnotation(IsItNecessary.class);
            if (t != null) {
                r = t.key();
                if (r == false) {
                    continue;
                }
                if (val == null || "".equals(val)) {
                    return false;
                }
            }
        }
        return true;
    }
}

