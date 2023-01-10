package com.evanpatchouli.demo.util;

public class StringUtil {
    public static Boolean isNotBlank(String str){
        if(str == null || str.equals("")){
            return false;
        }
        return true;
    }
}
