package com.evanpatchouli.demo.util;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public static Map error() {
        Map error = new HashMap();
        error.put("code",null);
        error.put("type",null);
        error.put("info",null);
        return error;
    }

    public static Map result(){
        Map result = new HashMap();
        result.put("status",null);
        result.put("error",error());
        result.put("data",null);
        result.put("msg",null);
        return result;
    }

    public static Map ok(){
        Map result = result();
        result.replace("status",HttpStatus.OK.code);
        result.replace("error",null);
        result.replace("data",null);
        result.replace("msg","success");

        return result;
    }

    public static Map ok(String msg){
        Map result = result();
        result.replace("status",HttpStatus.OK.code);
        result.replace("error",null);
        result.replace("msg",msg);
        result.replace("data",null);

        return result;
    }

    public static Map ok(Object o, String msg){
        Map result = result();
        result.replace("status",HttpStatus.OK.code);
        result.replace("error",null);
        result.replace("msg",msg);
        result.replace("data",o);

        return result;
    }

    public static Map ResetContent(){
        Map result = result();
        result.replace("status",HttpStatus.ResetContent.code);
        Map error = error();
        error.replace("type","ResetContent");
        error.replace("info","ResetContent");
        error.replace("code",null);
        result.replace("error",error);
        result.replace("msg","error");

        return result;
    }

    public static Map ResetContent(int code, String msg){
        Map result = result();
        result.replace("status",HttpStatus.ResetContent.code);
        Map error = error();
        error.replace("type","ResetContent");
        error.replace("info","ResetContent");
        error.replace("code",code);
        result.replace("error",error);
        result.replace("msg",msg);

        return result;
    }

    public static Map Unauthorized(){
        Map result = result();
        result.replace("status",HttpStatus.Unauthorized.code);
        Map error = error();
        error.replace("type","Unauthorized");
        error.replace("info","Unauthorized");
        error.replace("code",null);
        result.replace("error",error);
        result.replace("msg","error");

        return result;
    }

    public static Map Unauthorized(int code, String msg){
        Map result = result();
        result.replace("status",HttpStatus.Unauthorized.code);
        Map error = error();
        error.replace("type","Unauthorized");
        error.replace("info","Unauthorized");
        error.replace("code",code);
        result.replace("error",error);
        result.replace("msg",msg);

        return result;
    }

    public static Map NotFound(){
        Map result = result();
        result.replace("status",HttpStatus.NotFound.code);
        Map error = error();
        error.replace("type","NotFound");
        error.replace("info","NotFound");
        error.replace("code",null);
        result.replace("error",error);
        result.replace("msg","error");

        return result;
    }

    public static Map NotFound(int code, String msg){
        Map result = result();
        result.replace("status",HttpStatus.NotFound.code);
        Map error = error();
        error.replace("type","NotFound");
        error.replace("info","NotFound");
        error.replace("code",code);
        result.replace("error",error);
        result.replace("msg",msg);

        return result;
    }

    public static Map Forbidden(){
        Map result = result();
        result.replace("status",HttpStatus.Forbidden.code);
        Map error = error();
        error.replace("type","Forbidden");
        error.replace("info","Forbidden");
        error.replace("code",null);
        result.replace("error",error);
        result.replace("msg","error");

        return result;
    }

    public static Map Forbidden(int code, String msg){
        Map result = result();
        result.replace("status",HttpStatus.Forbidden.code);
        Map error = error();
        error.replace("type","Forbidden");
        error.replace("info","Forbidden");
        error.replace("code",code);
        result.replace("error",error);
        result.replace("msg",msg);

        return result;
    }

    public static Map error(String typename, int code, String msg){
        Map result = result();
        result.replace("status",HttpStatus.valueOf(typename));
        Map error = error();
        error.replace("type",typename);
        error.replace("info",typename);
        error.replace("code",code);
        result.replace("error",error);
        result.replace("msg",msg);

        return result;
    }

    public static Map error(String typename, int code, String msg, Object o){
        Map result = error(typename, code, msg);
        result.replace("data",o);

        return result;
    }

}
