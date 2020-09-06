package com.pvdnc.notebook.aop;

import com.pvdnc.notebook.entity.rest.BaseResult;
import com.pvdnc.notebook.entity.rest.NoteResult;
import com.pvdnc.notebook.entity.rest.ResultFactory;
import com.pvdnc.notebook.utils.SessionWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class CheckLoginImpl {
    @Resource
    HttpServletResponse response;

    @Pointcut("execution(* com.pvdnc.notebook.controller..*.*(..,javax.servlet.http.HttpSession)) && @annotation(com.pvdnc.notebook.aop.NeedLoginCheck)")
    public void aspect(){

    }

    @Around(value = "aspect() &&@annotation(action)",argNames = "jp,action")
    public Object beforeExecution(ProceedingJoinPoint jp, NeedLoginCheck action) throws Throwable {
        Object[] args= jp.getArgs();
        HttpSession session= SessionWrapper.getFromAspectArgs(args);
        String name= SessionWrapper.getName(session);
        if(name==null){//未登录
            BaseResult result= ResultFactory.getAsBase(action.resultType(),
                    403,
                    "since did not logon,"+action.action()+" are not available");
            return result;
        }
        return jp.proceed(args);
    }
}
