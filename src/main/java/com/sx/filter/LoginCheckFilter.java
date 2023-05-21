package com.sx.filter;

import com.alibaba.fastjson.JSONObject;
import com.sx.utils.JwtUtils;
import com.sx.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@ServletComponentScan
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url {}",url);

        //判断请求的url是否包含login
        if(url.contains("login")){
            log.info("登录放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }

        //获取请求头中令牌（token）
        String jwt = request.getHeader("token");

        //判断令牌是否存在
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");

            //手动转换对象-》json allibaba fastjson工具包
            String notLogin = JSONObject.toJSONString(error);
            //将字符串直接响应给浏览器
            response.getWriter().write(notLogin);

            return ;

        }
        //解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失效,返回未登录的错误信息");
            Result error=Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            //将字符串直接响应给浏览器
            response.getWriter().write(notLogin);

            return ;
        }
        //放行
        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }


}
