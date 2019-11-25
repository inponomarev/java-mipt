package edu.phystech.robotlecturer.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BenchmarkAspect {

    public BenchmarkAspect(){
        System.out.println("hello");
    }

    @Around(value = "@annotation(Benchmark)")
    public Object execEntryPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.printf("[[[BENCHMARK method %s%n", joinPoint.getSignature().getName());
        long start = System.nanoTime();
        Object retVal = joinPoint.proceed();
        long end = System.nanoTime();
        System.out.printf("Time: %dns]]]%n", end - start);
        return retVal;
    }
}
