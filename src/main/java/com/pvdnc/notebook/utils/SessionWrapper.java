package com.pvdnc.notebook.utils;

import javax.servlet.http.HttpSession;

public class SessionWrapper {
    private static final String KEY_NAME="name";

    public static String getName(HttpSession session){
        return (String) session.getAttribute(KEY_NAME);
    }

    public static void setName(HttpSession session,String name){
        session.setAttribute(KEY_NAME,name);
    }
}
