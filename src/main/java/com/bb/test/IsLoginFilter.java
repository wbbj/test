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

        if(null != httpSession){
            if(httpSession.getAttribute("success")!=null){
                chain.doFilter(req, resp);
            }else httpServletResponse.sendRedirect("login.html");
        }else httpServletResponse.sendRedirect("login.html");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
