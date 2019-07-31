package com.bb.test;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "IsLoginFilter")
public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) req;
        HttpServletResponse httpServletResponse= (HttpServletResponse) resp;
        HttpSession httpSession=httpServletRequest.getSession(false);

        //判断请求
        if(null != httpSession){
            //判断Session是否存在success标记
            if(httpSession.getAttribute("success")!=null){//存在则不需要继续登录
                chain.doFilter(req, resp);
            }else httpServletResponse.sendRedirect("login.html");//不存在则跳转登录
        }else httpServletResponse.sendRedirect("login.html");//用户未登录就请求页面直接拦截

    }
    public void init(FilterConfig config) throws ServletException {
    }
}
