package com.pvdnc.notebook.entity.rest;

import java.util.Map;

public class ResultFactory<T extends BaseResult> {

    public static <T extends BaseResult> BaseResult getAsBase(Class<T> typeOfResult,int code,String msg)
    throws Throwable {
        BaseResult result= typeOfResult.getConstructor().newInstance();
        result.code=code;
        result.msg=msg;
        return result;
    }
}
