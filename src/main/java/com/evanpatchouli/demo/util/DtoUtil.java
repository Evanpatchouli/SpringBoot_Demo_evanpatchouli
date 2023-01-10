package com.evanpatchouli.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class DtoUtil {
    public static Boolean hasNoBlank(Object obj, Set<String> ign){
        if(obj==null){	// obj是你要检查的dto
            return false;
        }
        if(ign==null){	// ign是一个哈希Set，调用方法前把你要忽略的字段（比如类型为int的、即使为空也无所谓的等）丢进ign，检查的时候跳过它们
            ign = new HashSet<>();
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        Boolean rs = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = "";
            try {
                fieldValue = field.get(obj); //得到属性值
                //Type fieldType = field.getGenericType();//得到属性类型
                fieldName = field.getName(); // 得到属性名
                //System.out.println("属性类型：" + fieldType + ",属性名：" + fieldName + ",属性值：" + fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(ign.contains(fieldName)){
                System.out.println(fieldName+" 属性忽略检测");
                continue;
            }
            if (fieldValue == null || fieldValue.equals("")) {  //只要有一个属性值不为null 就返回false 表示对象不为null
                System.out.println("对象有空值");
                rs = false;
                break;
            }
        }
        return rs;
    }
}
