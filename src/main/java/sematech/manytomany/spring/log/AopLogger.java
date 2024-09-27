package sematech.manytomany.spring.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AopLogger {

//    @Around("execution(* sematech.manytomany.spring.*.*.*(..))")
//    public void userAdvice( ProceedingJoinPoint joinPoint) throws Throwable{
//        StopWatch watch = new StopWatch();
//        watch.start();
//        joinPoint.proceed();
//        watch.stop();
//        System.out.println("@Around: call took-"+ watch.getTotalTimeMillis()+" ms");
//    }
}
