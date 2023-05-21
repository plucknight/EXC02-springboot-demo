package com.sx.aop;

import com.alibaba.fastjson.JSONObject;
import com.sx.mapper.OperateLogMapper;
import com.sx.utils.JwtUtils;
import com.sx.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect //切面类
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private HttpServletRequest request;//当前这次的请求对象

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //写入操作日志
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer)claims.get("id");

        LocalDateTime operateTime = LocalDateTime.now();
        //拿到目标对象-类对象-类名
        String className=joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args=joinPoint.getArgs();
        String methodParams= Arrays.toString(args);

        long begin=System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();
        long end=System.currentTimeMillis();

        String returnValue = JSONObject.toJSONString(result);
        Long constTime=end-begin;

        //记录操作日志
        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,constTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志:{}",operateLog);
        return result;

    }
}
