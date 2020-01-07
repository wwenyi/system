package com.wwy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author wwy
 * @date 2019-11-21
 *允许跨域请求的过滤器
 */
public class CrossFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response= (HttpServletResponse) servletResponse;
//        String origin= servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Headers", "Authentication");
//     
//        filterChain.doFilter(servletRequest,servletResponse);
    }
    public void destroy() {
 
    }
}

