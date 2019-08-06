package team.qiruan.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 用来检查用户进入的是不是自己的用户中心
 */
@Component
@Aspect
public class UserAspect {
     @Around("execution(* team.qiruan.controller.UserController.*(..))")//execution 任何返回值都可以 执行类名 .* 这个类任何方法 (..) 包含任何参数
     public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {//参数是包含你当前拦截方法的信息的对象

        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(pjp.getArgs()[0])){
            return null;
        }

        Object object = pjp.proceed(); //调用被拦截的方法

        return object;
     }
}
