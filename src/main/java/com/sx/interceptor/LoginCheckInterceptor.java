package com.sx.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sx.pojo.Result;
import com.sx.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //请求url
        String url = request.getRequestURI().toString();
        log.info("请求的url {}",url);

        //判断请求的url是否包含login
        if(url.contains("login")){
            log.info("登录放行...");

            return true;
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

            return false;

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

            return false;
        }
        //放行
        log.info("令牌合法,放行");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
