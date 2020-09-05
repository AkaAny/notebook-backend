package com.pvdnc.notebook.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.pvdnc.notebook.aop.NeedLoginCheck;
import com.pvdnc.notebook.entity.User;
import com.pvdnc.notebook.entity.request.RegisterRequest;
import com.pvdnc.notebook.entity.rest.BaseResult;
import com.pvdnc.notebook.mapper.UserMapper;
import com.pvdnc.notebook.utils.SessionWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("User CRUD APIs")
@RestController
public class HelloController {
    private static final Logger LOG=LoggerFactory.getLogger(HelloController.class);

    private static final String KEY_NAME="name";

    @Resource
    private UserMapper mUserMapper;

    @ApiOperation(value ="Register a new User that does not exist",produces = "application/json;charset=UTF-8",httpMethod = "POST")
    @PostMapping(value = "/register",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResult register(@RequestBody RegisterRequest request){
        BaseResult result=new BaseResult();
        String name=request.name;
        String password=request.password;
        User user= mUserMapper.getUser(name);
        if(user!=null){
            result.code=403;
            result.msg="user:"+name+" has existed";
            return result;
        }
        user=new User();
        user.name=name;
        user.password=String.valueOf(password.hashCode());
        mUserMapper.addUser(user);
        result.code=200;
        result.msg="user:"+name+" registered";
        return result;
    }

    @ApiOperation(value = "Login by user name and password",httpMethod = "GET")
    @GetMapping("/login")
    public BaseResult login(@ApiParam(value = "user name",required = true) @RequestParam("name") String name,
                            @ApiParam(value = "password",required = true) @RequestParam("password") String password,
            HttpSession session){
        BaseResult result=new BaseResult();
        String validPasswordHash= mUserMapper.getPassword(name);
        String passwordHash=String.valueOf(password.hashCode());
        if(validPasswordHash==null){
            result.code=403;
            result.msg="user:"+name+" does not exist";
            return result;
        }
        if(validPasswordHash.equals(passwordHash)){
            result.code=200;
            result.msg="user:"+name+" login";
            session.setAttribute(KEY_NAME,name);
        }else{
            result.code=403;
            result.msg="invalid password";
        }
        return result;
    }

    @ApiOperation(value = "[Login Required] Logout current user in session",httpMethod = "GET")
    @NeedLoginCheck(action = "logout")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        String sessionId=session.getId();
        session.invalidate();
        return "session:"+sessionId+" logout";
    }

    @NeedLoginCheck(action = "hello")
    @GetMapping("/hello")
    @ResponseBody
    public String hello(HttpSession session){
        LOG.debug("session:"+session);
        String name= SessionWrapper.getName(session);
        return "hello "+name;
    }
}
